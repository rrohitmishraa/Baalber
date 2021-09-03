package com.trikown.baalber.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.R;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.ActivityAppointmentDoneBinding;

public class AppointmentDoneActivity extends AppCompatActivity {

    private ActivityAppointmentDoneBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityAppointmentDoneBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.toolbar.xBtnBack.setVisibility(View.GONE);

        Appointment appointment = getIntent().getParcelableExtra("BUNDLE");

        new Repo().getCustomerDetails("Customer", appointment.getUserCode(), customer ->
                b.userName.setText("Username: -> " + customer.getUserName()));

        new Repo().getShopDetails(appointment.getShopCode(), shop ->
                b.shopName.setText("Shop Name: -> " + shop.getShopName()));

        b.date.setText("Date: -> " + appointment.getDate());
        b.time.setText("Time Slot: -> " + appointment.getTime());

        String haircut, shave;
        if (appointment.getHaircut() == 1) {
            haircut = " Haircut ";
        } else {
            haircut = "";
        }

        if (appointment.getShave() == 1) {
            shave = " Shave ";
        } else {
            shave = "";
        }

        String req = haircut + shave;

        b.requirements.setText("Requirements: -> " + req.trim());

        b.bntGoToHome.setOnClickListener(view -> goToHome());
    }

    private void goToHome() {
        Intent i = new Intent(this, DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        goToHome();
    }
}