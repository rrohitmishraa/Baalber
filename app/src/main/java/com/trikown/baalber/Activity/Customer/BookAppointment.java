package com.trikown.baalber.Activity.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.trikown.baalber.R;

public class BookAppointment extends AppCompatActivity {
    ImageView mBtnBack;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        init();
    }

    private void init() {
        mBtnBack = findViewById(R.id.xBtnBack);
        mTitle = findViewById(R.id.xTitle);

        ImageViewCompat.setImageTintList(mBtnBack, ColorStateList.valueOf(getColor(R.color.white)));
        mTitle.setTextColor(getColor(R.color.white));

        onClicks();
    }

    private void onClicks() {

    }
}