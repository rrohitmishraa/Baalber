package com.trikown.baalber.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.trikown.baalber.Adapters.HomeTabsAdapter;
import com.trikown.baalber.R;
import com.trikown.baalber.databinding.FragmentHomeShopOwnerBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragmentO extends Fragment {

    private FragmentHomeShopOwnerBinding b;

    HomeTabsAdapter adapter;
    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentHomeShopOwnerBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        init();

        populateViewPager();

        return v;
    }

    private void populateViewPager() {
        b.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (b.viewPager.getCurrentItem() == 0) {
                    b.curDay.setText("Today");
                } else if (b.viewPager.getCurrentItem() == 1) {
                    b.curDay.setText("Tomorrow");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void init() {
        b.xToolbar.xBtnBack.setVisibility(View.GONE);
        //Get and format date
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        //today's date
        String today = dateFormat.format(calendar.getTime());
        //adding 1 day in today
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        //Tomorrow's date
        String tomorrow = dateFormat.format(calendar.getTime());

        adapter = new HomeTabsAdapter(getChildFragmentManager(), 2);
        b.viewPager.setAdapter(adapter);

        //Load fragment today on start
        fm = getChildFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.viewPager, new TodayFragment());
        transaction.commit();

        b.tabLayout.setupWithViewPager(b.viewPager);
        b.tabLayout.getTabAt(0).setText(today);
        b.tabLayout.getTabAt(1).setText(tomorrow);
    }
}