package com.trikown.baalber.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trikown.baalber.Fragment.Profile.PersonalDetailsFragment;
import com.trikown.baalber.Fragment.Profile.ShopDetailsFragment;

public class ProfileTabsAdapter extends FragmentPagerAdapter {

    int tabCount;

    public ProfileTabsAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PersonalDetailsFragment();
            case 1:
                return new ShopDetailsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
