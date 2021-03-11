package com.trikown.baalber.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trikown.baalber.Fragment.DateTabs.TodayFragment;
import com.trikown.baalber.Fragment.DateTabs.TomorrowFragment;

public class HomeTabsAdapter extends FragmentPagerAdapter {

    int tabCount;

    public HomeTabsAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TodayFragment();
            case 1:
                return new TomorrowFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
