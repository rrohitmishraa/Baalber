package com.trikown.baalber.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trikown.baalber.Fragment.HomeFragmentC;
import com.trikown.baalber.Fragment.HomeFragmentO;
import com.trikown.baalber.Fragment.PersonalFragment;
import com.trikown.baalber.Fragment.ShopDetailsFragment;
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.Utils.RemoveWhiteFlash;
import com.trikown.baalber.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding b;

    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        new RemoveWhiteFlash().removeWhiteFlash(this);

        b.bottomNavBar.setItemSelected(R.id.menu_home, true);

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, b.rootLayout);

        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        b.lottieLoader.progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedData), MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            String accountType = sp.getString("accountType", "");

            if (accountType.equalsIgnoreCase("shopOwner")) {
                transaction.replace(R.id.fragmentContainer, new HomeFragmentO());
                bottomNavigationShop();
            } else if (accountType.equalsIgnoreCase("customer")) {
                transaction.replace(R.id.fragmentContainer, new HomeFragmentC());
                bottomNavigationCustomer();
            }

            transaction.commit();
        }, 2000);

        new Handler().postDelayed(() -> b.lottieLoader.progressBar.setVisibility(View.GONE), 3000);
    }

    //Shop bottom navigation
    public void bottomNavigationShop() {
        b.bottomNavBar.setOnItemSelectedListener(i -> {
            switch (b.bottomNavBar.getSelectedItemId()) {
                case R.id.menu_home:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new HomeFragmentO());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_shop:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new ShopDetailsFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_profile:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new PersonalFragment());
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
        b.bottomNavBar.setOnItemSelectedListener(i -> {
            switch (b.bottomNavBar.getSelectedItemId()) {
                case R.id.menu_home:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new HomeFragmentC());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_shop:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new ShopDetailsFragment());
                    //transaction.addToBackStack("dashboard");
                    transaction.commit();
                    break;

                case R.id.menu_profile:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new PersonalFragment());
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
        if (b.bottomNavBar.getSelectedItemId() != R.id.menu_home) {

            //select the home on bottom navigation
            b.bottomNavBar.setItemSelected(R.id.menu_home, true);

        } else {
            super.onBackPressed();
        }
    }
}