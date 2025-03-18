package xq.yiesko.app180go.fragments.home.pages.calc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.TransitionManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialFade;

import java.util.Locale;

import xq.yiesko.app180go.R;
import xq.yiesko.app180go.databinding.FragmentCalcBinding;
import xq.yiesko.app180go.fragments.home.pages.calc.viewmodel.CalcViewModel;

public class CalcFragment extends Fragment {
    private FragmentCalcBinding binding;
    private CalcViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalcBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CalcViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupDefaults();
        setupValidation();
        setupCalculateButton();
        observeResults();
    }

    private void setupDefaults() {
        binding.fragmentCalcEditKm.setText(getString(R.string.calc_fragment_default_km));
        binding.editBase.setText(getString(R.string.calc_fragment_default_base));
        binding.fragmentCalcEditTime.setText(getString(R.string.calc_fragment_default_time));

        viewModel.setBaseFare(5.0);
        viewModel.setKmRate(2.0);
        viewModel.setTimeRate(0.5);
    }

    private void setupValidation() {
        var validator = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*
                 * There's no need to use this.
                 */
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*
                 * There's no need to use this.
                 */
            }
        };

        binding.fragmentCalcEditKm.addTextChangedListener(validator);
        binding.fragmentCalcEditTime.addTextChangedListener(validator);
        binding.editBase.addTextChangedListener(validator);
    }

    private void validateFields() {
        var isValid = isFieldValid(binding.fragmentCalcEditKm) &&
                isFieldValid(binding.fragmentCalcEditTime) &&
                isFieldValid(binding.editBase);

        binding.fragmentCalcBtnCalculate.setEnabled(isValid);
    }

    private boolean isFieldValid(
            TextInputEditText field
    ) {
        try {
            var text = field.getText() != null ? field.getText().toString() : "";
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return !TextUtils.isEmpty(field.getText());
        }
    }

    private void setupCalculateButton() {
        binding.fragmentCalcBtnCalculate.setOnClickListener(v -> {
            try {
                var kmText = binding.fragmentCalcEditKm.getText()
                        != null
                        ? binding.fragmentCalcEditKm.getText().toString() : "0";
                var timeText = binding.fragmentCalcEditTime.getText()
                        != null
                        ? binding.fragmentCalcEditTime.getText().toString() : "0";
                var baseText = binding.editBase.getText()
                        != null
                        ? binding.editBase.getText().toString() : "0";

                var km = Double.parseDouble(kmText);
                var time = Double.parseDouble(timeText);
                var base = Double.parseDouble(baseText);

                viewModel.setBaseFare(base);
                viewModel.calculate(km, time);
            } catch (NumberFormatException e) {
                Snackbar.make(binding.getRoot(),
                        getString(R.string.calc_fragment_input_error),
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void observeResults() {
        viewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (binding.fragmentCalcCardResult.getVisibility() != View.VISIBLE) {
                    TransitionManager.beginDelayedTransition((ViewGroup) binding.fragmentCalcCardResult.getParent(), new MaterialFade());
                    binding.fragmentCalcCardResult.setVisibility(View.VISIBLE);
                }

                binding.fragmentCalcResultText.setText(
                        String.format(Locale.getDefault(),
                                getString(R.string.calc_fragment_currency_format),
                                result)
                );
            }
        });
    }
}