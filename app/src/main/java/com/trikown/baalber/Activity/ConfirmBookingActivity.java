package com.trikown.baalber.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.ActivityConfirmBookingBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConfirmBookingActivity extends AppCompatActivity {

    private ActivityConfirmBookingBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityConfirmBookingBinding.inflate(getLayoutInflater());
        View v = b.getRoot();
        setContentView(v);

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

        b.toolbar.xBtnBack.setOnClickListener(v1 -> this.onBackPressed());

        b.btnConfirm.setOnClickListener(view -> {
            //If user chooses both requirements book one extra slot
            String firstSlot = appointment.getTime();
            String nextSlot = addOneSlot(firstSlot);

            if (appointment.getShave() == 1 && appointment.getHaircut() == 1) {
                new Repo().getAllBookedTimeSlots(appointment.getDate(), appointment.getShopCode(),
                        usedTimeSlots -> {
                            if (usedTimeSlots.contains(nextSlot)) {
                                b.error.setText("During this time slot all your requirements can not be fulfilled.\nPlease select a different time slot.");
                            } else {
                                //First Slot
                                new Repo().createAppointment(appointment);
                                //new Repo().addAppointmentForCustomer(appointment);

                                //Second Slot
                                appointment.setTime(nextSlot);
                                new Repo().createAppointment(appointment);
                                new Repo().addAppointmentForCustomer(appointment);

                                appointment.setTime(firstSlot);
                                appointmentDoneAnimation(appointment);
                            }
                        });
            } else {
                //Just One Slot
                new Repo().createAppointment(appointment);
                new Repo().addAppointmentForCustomer(appointment);
                appointmentDoneAnimation(appointment);

            }
        });
    }

    private void appointmentDoneAnimation(Appointment appointment) {
        b.loading.setVisibility(View.VISIBLE);
        b.message.setVisibility(View.GONE);
        b.cardView.setVisibility(View.GONE);
        b.btnConfirm.setVisibility(View.GONE);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(this, AppointmentDoneActivity.class);
            i.putExtra("BUNDLE", appointment);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            b.loading.setVisibility(View.GONE);
            finish();
        }, 1000);
    }

    private String addOneSlot(String time) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        Date oTime = null;
        try {
            oTime = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();

        cal.setTime(oTime);
        cal.add(Calendar.MINUTE, 15);

        return df.format(cal.getTime());
    }
}