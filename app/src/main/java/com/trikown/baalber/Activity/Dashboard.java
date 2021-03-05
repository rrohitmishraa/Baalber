package com.trikown.baalber.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.trikown.baalber.Fragment.BottomNav.Home;
import com.trikown.baalber.Fragment.BottomNav.Profile;
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.Utils.Exit;

public class Dashboard extends AppCompatActivity {

    CoordinatorLayout mDashboardRootLayout;
    ChipNavigationBar mBottomNavigationBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        
        mDashboardRootLayout = findViewById(R.id.xDashboardRootLayout);
        mBottomNavigationBar = findViewById(R.id.xBottomNavigationBar);

        mBottomNavigationBar.setItemSelected(R.id.home, true);

        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mDashboardRootLayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.xFragmentContainer, new Home()).commit();

        bottomNavigation();
    }

    public void bottomNavigation() {
        mBottomNavigationBar.setOnItemSelectedListener(i -> {
            switch (mBottomNavigationBar.getSelectedItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.xFragmentContainer, new Home()).commit();
                    break;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.xFragmentContainer, new Profile()).commit();
                    break;
                default:
                    Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Exit exit = new Exit(Dashboard.this);
        exit.exitActivity();
        super.onBackPressed();
    }
}