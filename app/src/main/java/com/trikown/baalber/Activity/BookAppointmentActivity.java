package com.trikown.baalber.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import com.trikown.baalber.Dialog.SlotsDialog;
import com.trikown.baalber.Interface.DialogToActivity;
import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.R;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.Utils.RemoveWhiteFlash;
import com.trikown.baalber.databinding.ActivityBookAppointmentBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookAppointmentActivity extends AppCompatActivity implements DialogToActivity {
    private ActivityBookAppointmentBinding b;
    private String shopCode;
    private SimpleDateFormat sdf;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityBookAppointmentBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        init();
    }


    private void init() {
        new RemoveWhiteFlash().removeWhiteFlash(this);
        b.lottieLoader.progressBar.setVisibility(View.VISIBLE);

        sdf = new SimpleDateFormat("MMMM dd, yyyy");
        calendar = Calendar.getInstance();

        ImageViewCompat.setImageTintList(b.xToolbar.xBtnBack, ColorStateList.valueOf(getColor(R.color.white)));
        b.xToolbar.xTitle.setTextColor(getColor(R.color.white));

        b.date.setText(sdf.format(calendar.getTime()));

        shopCode = getIntent().getStringExtra("shopCode");

        new Repo().getShopDetails(shopCode, shop -> {
            b.shopName.setText(shop.getShopName());
            b.rating.setText(shop.getRating());
        });

        new Handler(Looper.getMainLooper()).postDelayed(() -> b.lottieLoader.progressBar.setVisibility(View.GONE), 1000);

        onClicks();
    }

    private void onClicks() {
        b.xBtnBookApp.setOnClickListener(v -> {
            Intent i = new Intent(this, ConfirmBookingActivity.class);

            int haircut, shave;

            if (b.hairCut.isChecked()) {
                haircut = 1;
            } else {
                haircut = 0;
            }

            if (b.shave.isChecked()) {
                shave = 1;
            } else {
                shave = 0;
            }

            if (b.time.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show();
            } else if (haircut == 1 || shave == 1) {
                SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPreference), MODE_PRIVATE);
                String userCode = sp.getString("googleId", null);

                Appointment ap = new Appointment("n", shopCode, b.time.getText().toString(), userCode, b.date.getText().toString(), haircut, shave);
                i.putExtra("BUNDLE", ap);
                startActivity(i);
            } else {
                Toast.makeText(this, "Please select what you need", Toast.LENGTH_SHORT).show();
            }
        });

        b.calendar.setOnClickListener(v -> {
            calendar.setTime(new Date());

            DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                b.date.setText(sdf.format(calendar.getTime()));
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointmentActivity.this, date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            long minDate = System.currentTimeMillis(); //Today
            long maxDate = minDate + 5038400000L; //Today + 2 months

            datePickerDialog.getDatePicker().setMinDate(minDate);
            datePickerDialog.getDatePicker().setMaxDate(maxDate);
            datePickerDialog.show();
        });

        b.btnTime.setOnClickListener(v -> openDialog());

        b.xToolbar.xBtnBack.setOnClickListener(v -> this.onBackPressed());
    }

    private void openDialog() {
        SlotsDialog slots = new SlotsDialog();
        Bundle args = new Bundle();
        args.putString("ShopCode", shopCode);
        args.putString("Date", b.date.getText().toString());
        slots.setArguments(args);
        slots.show(getSupportFragmentManager(), "slots");
    }

    @Override
    public void getData(String time) {
        b.time.setText(time);
    }
}