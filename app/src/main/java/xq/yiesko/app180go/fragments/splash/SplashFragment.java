package xq.yiesko.app180go.fragments.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.platform.MaterialSharedAxis;

import xq.yiesko.app180go.R;
import xq.yiesko.app180go.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding fragmentSplashBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        fragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false);
        return fragmentSplashBinding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        new Handler(Looper.getMainLooper()).postDelayed(() -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.splash_warning_about_app_title)
                .setMessage(R.string.splash_warning_about_app_message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    Navigation.findNavController(
                            fragmentSplashBinding.getRoot()
                    ).navigate(R.id.action_splash_to_home);
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    dialog.cancel();
                    requireActivity().finishAffinity();
                })
                .setCancelable(false)
                .show(), 2000);
    }
}