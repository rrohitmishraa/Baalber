package com.trikown.baalber.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trikown.baalber.R;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkExistence();
    }

    private void checkExistence() {

        //Check If already Logged In by checking FirebaseAuth current user existence
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            startActivity(new Intent(this, Dashboard.class));
        } else {
            startActivity(new Intent(this, SelectionScreen.class));
        }
        overridePendingTransition(0, 0);
    }
}