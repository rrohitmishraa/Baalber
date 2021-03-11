package com.trikown.baalber.Fragment.BottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.trikown.baalber.Adapters.HomeTabsAdapter;
import com.trikown.baalber.Adapters.ProfileTabsAdapter;
import com.trikown.baalber.Fragment.DateTabs.TodayFragment;
import com.trikown.baalber.Fragment.Profile.ShopDetailsFragment;
import com.trikown.baalber.R;

public class ProfileFragment extends Fragment {

    View v;
    ViewPager mProfileViewPager;
    TabLayout mTabLayout;
    ProfileTabsAdapter adapter;
    TextView xDetails;
    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);;

        init();

        mProfileViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mProfileViewPager.getCurrentItem() == 0) {
                    xDetails.setText("Shop Name");
                } else if (mProfileViewPager.getCurrentItem() == 1) {
                    xDetails.setText("User Name");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return v;
    }

    private void init() {
        mProfileViewPager = v.findViewById(R.id.xProfileViewPager);
        mTabLayout = v.findViewById(R.id.xProfileTabLayout);
        xDetails = v.findViewById(R.id.xDetails);

        adapter = new ProfileTabsAdapter(getChildFragmentManager(), 2);
        mProfileViewPager.setAdapter(adapter);

        //Load fragment Shop Details on start
        fm = getChildFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.xProfileViewPager, new ShopDetailsFragment());
        transaction.commit();

        mTabLayout.setupWithViewPager(mProfileViewPager);
        mTabLayout.getTabAt(0).setText("Shop Details");
        mTabLayout.getTabAt(1).setText("Account Details");
    }
}