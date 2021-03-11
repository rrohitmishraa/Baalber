package com.trikown.baalber.Fragment.BottomNav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trikown.baalber.Adapters.HomeTabsAdapter;
import com.trikown.baalber.Fragment.DateTabs.TodayFragment;
import com.trikown.baalber.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    View v;
    ViewPager mHomeViewPager;
    TabLayout mTabLayout;
    HomeTabsAdapter adapter;
    TextView mCurDate;
    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        mHomeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mHomeViewPager.getCurrentItem() == 0) {
                    mCurDate.setText("Today");
                } else if (mHomeViewPager.getCurrentItem() == 1) {
                    mCurDate.setText("Tomorrow");
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
        //Get and format date
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        //today's date
        String today = dateFormat.format(calendar.getTime());
        //adding 1 day in today
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        //Tomorrow's date
        String tomorrow = dateFormat.format(calendar.getTime());

        mHomeViewPager = v.findViewById(R.id.xHomeViewPager);
        mTabLayout = v.findViewById(R.id.xHomeTabLayout);
        mCurDate = v.findViewById(R.id.xCurDate);

        adapter = new HomeTabsAdapter(getChildFragmentManager(), 2);
        mHomeViewPager.setAdapter(adapter);

        //Load fragment today on start
        fm = getChildFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.xHomeViewPager, new TodayFragment());
        transaction.commit();

        mTabLayout.setupWithViewPager(mHomeViewPager);
        mTabLayout.getTabAt(0).setText(today);
        mTabLayout.getTabAt(1).setText(tomorrow);
    }
}