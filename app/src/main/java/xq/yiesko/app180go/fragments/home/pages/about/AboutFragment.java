package xq.yiesko.app180go.fragments.home.pages.about;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import xq.yiesko.app180go.R;
import xq.yiesko.app180go.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding fragmentAboutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAboutBinding = FragmentAboutBinding.inflate(inflater, container, false);
        return fragmentAboutBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            var packageInfo = requireContext().getPackageManager().getPackageInfo(requireContext().getPackageName(), 0);
            var versionName = packageInfo.versionName;
            var versionText = getString(R.string.about_fragment_version, versionName);

            fragmentAboutBinding.aboutFragmentVersionText.setText(versionText);
        } catch (PackageManager.NameNotFoundException e) {
            fragmentAboutBinding.aboutFragmentVersionText.setText(R.string.about_fragment_version_unknown);
        }
    }
}