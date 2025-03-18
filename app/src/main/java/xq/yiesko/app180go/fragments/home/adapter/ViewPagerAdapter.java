package xq.yiesko.app180go.fragments.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import xq.yiesko.app180go.R;
import xq.yiesko.app180go.fragments.home.pages.about.AboutFragment;
import xq.yiesko.app180go.fragments.home.pages.calc.CalcFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final FragmentActivity fragmentActivity;
    private final Fragment[] fragments = {
            new CalcFragment(),
            new AboutFragment()
    };

    public ViewPagerAdapter(
            @NonNull FragmentActivity fragmentActivity
    ) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(
            int position
    ) {
        return fragments[position];
    }

    public String getTabName(
            int position
    ) {
        return switch (position) {
            case 0 -> fragmentActivity.getString(R.string.calc_fragment);
            case 1 -> fragmentActivity.getString(R.string.about_fragment);
            default -> throw new IllegalStateException(String.format("There is no position set for %d, check the code!", position));
        };
    }

    public int getTabIcon(
            int position
    ) {
        return switch (position) {
            case 0 -> R.drawable.tab_calc_selector;
            case 1 -> R.drawable.tab_info_selector;
            default -> throw new IllegalStateException(String.format("There is no position set for %d, check the code!", position));
        };
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}