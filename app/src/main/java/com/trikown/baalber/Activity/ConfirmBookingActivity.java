package com.trikown.baalber.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.trikown.baalber.databinding.ActivityConfirmBookingBinding;

public class ConfirmBookingActivity extends AppCompatActivity {

    private ActivityConfirmBookingBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityConfirmBookingBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

        b.toolbar.xBtnBack.setOnClickListener(v1 -> this.onBackPressed());
    }
}