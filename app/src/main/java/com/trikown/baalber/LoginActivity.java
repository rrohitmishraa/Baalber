package com.trikown.baalber;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.trikown.baalber.Utils.CircularScreenReveal;

public class LoginActivity extends AppCompatActivity {

    CoordinatorLayout mLoginRootLayout;
    TextView mBtnPhoneLogin, mBtnGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mLoginRootLayout = findViewById(R.id.xLoginRootLayout);
        mBtnPhoneLogin = findViewById(R.id.xBtnPhoneLogin);
        mBtnGoogleLogin = findViewById(R.id.xBtnGoogleLogin);

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mLoginRootLayout);

        String accountType = getIntent().getStringExtra("accountType");

        if(accountType.equalsIgnoreCase("customer")) {
            mLoginRootLayout.setBackgroundResource(R.drawable.black_splash_screen);
        } else {
            mLoginRootLayout.setBackgroundResource(R.drawable.blue_splash_screen);
        }

        mBtnGoogleLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Calling reverse reveal from CircularReveal class
            CircularScreenReveal circularScreenReveal = new CircularScreenReveal(LoginActivity.this);
            circularScreenReveal.reverseCircularReveal(mLoginRootLayout);
        } else {
            super.onBackPressed();
        }
    }
}