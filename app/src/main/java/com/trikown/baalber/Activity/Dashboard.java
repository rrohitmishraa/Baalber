package com.trikown.baalber.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;

import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.Utils.Exit;

public class Dashboard extends AppCompatActivity {

    CoordinatorLayout mDashboardRootLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        
        mDashboardRootLayout = findViewById(R.id.xDashboardRootLayout);
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.setStatusBarColor();
        circularScreenReveal.layoutCheck(savedInstanceState, mDashboardRootLayout);
    }

    @Override
    public void onBackPressed() {
        Exit exit = new Exit(Dashboard.this);
        exit.exitActivity();
    }
}