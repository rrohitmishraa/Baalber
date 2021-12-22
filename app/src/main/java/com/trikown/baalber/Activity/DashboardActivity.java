package com.trikown.baalber.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trikown.baalber.Fragment.AppointmentsFragment;
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
    String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        new RemoveWhiteFlash().removeWhiteFlash(this);

        b.bottomNavBar.setVisibility(View.GONE);
        b.bottomNavBar.setItemSelected(R.id.menu_home, true);

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, b.rootLayout);

        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        b.lottieLoader.progressBar.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPreference), MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            accountType = sp.getString("accountType", "");

            if (accountType.equalsIgnoreCase("shopOwner")) {
                transaction.replace(R.id.fragmentContainer, new HomeFragmentO());
                b.bottomNavBar.removeViewAt(1);
                bottomNavigationShop();
            } else if (accountType.equalsIgnoreCase("customer")) {
                transaction.replace(R.id.fragmentContainer, new HomeFragmentC());
                b.bottomNavBar.removeViewAt(2);
                bottomNavigationCustomer();
            }

            transaction.commit();
            b.bottomNavBar.setVisibility(View.VISIBLE);
        }, 2000);

        new Handler().postDelayed(() -> b.lottieLoader.progressBar.setVisibility(View.GONE), 3500);
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

                case R.id.menu_shop_details:
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

                case R.id.menu_appointment:
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new AppointmentsFragment());
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        Toast.makeText(this, "rohit", Toast.LENGTH_SHORT).show();
        menu.removeItem(accountType.equalsIgnoreCase("customer") ? R.id.menu_shop_details : R.id.menu_appointment);
        return super.onPrepareOptionsMenu(menu);
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