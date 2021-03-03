package com.trikown.baalber;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //delaySplashScreen();

        startActivity(new Intent(this, SelectScreen.class));
        overridePendingTransition(0, 0);
    }

    private void delaySplashScreen() {

        //SplashScreen delay
        Completable.timer(1, TimeUnit.SECONDS,
                AndroidSchedulers.mainThread())
                .subscribe(() -> {

                    //Check if Already Logged in
                    startActivity(new Intent(this, SelectScreen.class));
                    overridePendingTransition(0, 0);
                });

    }
}