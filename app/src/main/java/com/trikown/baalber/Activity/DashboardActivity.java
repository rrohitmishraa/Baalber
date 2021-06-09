package com.trikown.baalber.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.trikown.baalber.Fragment.BottomNav.Customer.HomeFragmentC;
import com.trikown.baalber.Fragment.BottomNav.Shop.HomeFragment;
import com.trikown.baalber.Fragment.BottomNav.Shop.PersonalFragment;
import com.trikown.baalber.Fragment.BottomNav.Shop.ShopFragment;
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;

public class DashboardActivity extends AppCompatActivity {

    CoordinatorLayout mDashboardRootLayout;
    ChipNavigationBar mBottomNavigationBar;
    FragmentManager fm;
    View v;
    ProgressBar mProgressBar;
    FragmentTransaction transaction;
    ViewPager mHomeViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDashboardRootLayout = findViewById(R.id.xDashboardRootLayout);
        mBottomNavigationBar = findViewById(R.id.xBottomNavigationBar);
        mProgressBar = findViewById(R.id.xProgressBar);

        mBottomNavigationBar.setItemSelected(R.id.menu_home, true);

        v = LayoutInflater.from(this).inflate(R.layout.fragment_shop_owner_home, null);
        mHomeViewPager = v.findViewById(R.id.xHomeViewPager);

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mDashboardRootLayout);

        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        mProgressBar.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedData), MODE_PRIVATE);

        (new Handler()).postDelayed(() -> {
            String accountType = sp.getString("accountType", "");

            if (accountType.equalsIgnoreCase("shopOwner")) {
                transaction.replace(R.id.xFragmentContainer, new HomeFragment());
                bottomNavigationShop();
            } else if (accountType.equalsIgnoreCase("customer")) {
                transaction.replace(R.id.xFragmentContainer, new HomeFragmentC());
                bottomNavigationCustomer();
            }

            transaction.commit();

            mProgressBar.setVisibility(View.INVISIBLE);
        } ,2000);
    }

    //Shop bottom navigation
    public void bottomNavigationShop() {
        mBottomNavigationBar.setOnItemSelectedListener(i -> {
            switch (mBottomNavigationBar.getSelectedItemId()) {
                case R.id.menu_home:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new HomeFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_shop:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new ShopFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_profile:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new PersonalFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;
                default:
                    break;
            }
        });
    }

    //Customer bottom navigation
    public void bottomNavigationCustomer() {
        mBottomNavigationBar.setOnItemSelectedListener(i -> {
            switch (mBottomNavigationBar.getSelectedItemId()) {
                case R.id.menu_home:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new HomeFragmentC());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_shop:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new ShopFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_profile:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.xFragmentContainer, new PersonalFragment());
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