package com.trikown.baalber.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.trikown.baalber.Fragment.BottomNav.HomeFragment;
import com.trikown.baalber.Fragment.BottomNav.ProfileFragment;
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;

public class DashboardActivity extends AppCompatActivity {

    CoordinatorLayout mDashboardRootLayout;
    ChipNavigationBar mBottomNavigationBar;
    FragmentManager fm;
    View v;
    FragmentTransaction transaction;
    ViewPager mHomeViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDashboardRootLayout = findViewById(R.id.xDashboardRootLayout);
        mBottomNavigationBar = findViewById(R.id.xBottomNavigationBar);

        mBottomNavigationBar.setItemSelected(R.id.menu_home, true);

        v = LayoutInflater.from(this).inflate(R.layout.fragment_home, null);
        mHomeViewPager = v.findViewById(R.id.xHomeViewPager);

        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mDashboardRootLayout);

        fm = getSupportFragmentManager();

        transaction = fm.beginTransaction();
        transaction.replace(R.id.xFragmentContainer, new HomeFragment());
        transaction.commit();

        bottomNavigation();
    }

    public void bottomNavigation() {
        mBottomNavigationBar.setOnItemSelectedListener(i -> {
            switch (mBottomNavigationBar.getSelectedItemId()) {
                case R.id.menu_home:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new HomeFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;
                case R.id.menu_profile:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new ProfileFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBottomNavigationBar.getSelectedItemId() != R.id.menu_home) {

            //select the home on bottom navigation
            mBottomNavigationBar.setItemSelected(R.id.menu_home, true);

        } else {
            super.onBackPressed();
        }
    }
}