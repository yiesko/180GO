package xq.yiesko.app180go.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.transition.platform.MaterialSharedAxis;

import xq.yiesko.app180go.databinding.FragmentHomeBinding;
import xq.yiesko.app180go.fragments.home.adapter.ViewPagerAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        var tabLayout = fragmentHomeBinding.fragmentHomeTabs;
        var viewPager = fragmentHomeBinding.fragmentHomeViewpager;
        var viewPagerAdapter = new ViewPagerAdapter(requireActivity());

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(viewPagerAdapter.getTabName(position));
            tab.setIcon(viewPagerAdapter.getTabIcon(position));
        }).attach();
    }
}