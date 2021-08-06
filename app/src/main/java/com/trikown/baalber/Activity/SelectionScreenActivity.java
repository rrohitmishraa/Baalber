package com.trikown.baalber.Activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.databinding.ActivitySelectionScreenBinding;

public class SelectionScreenActivity extends AppCompatActivity {
    private ActivitySelectionScreenBinding b;

    String accountType = "Customer";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySelectionScreenBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, b.selectionRootLayout);

        b.btnShop.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                //reduce size animation if button down

                AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.reduce_size);
                reducer.setTarget(b.btnShop);
                reducer.start();

                b.btnCustomer.setEnabled(false);

                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                //regain size animation in button UP

                AnimatorSet gainer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.regain_size);
                gainer.setTarget(b.btnShop);
                gainer.start();

                b.btnCustomer.setEnabled(true);

                // perform changes to the selection and next button
                b.btnShop.postDelayed(() -> {

                    //wait 290 sec (10 sec less than animation end time) then reduce size of another button

                    AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.second_reduce_size);
                    reducer.setTarget(b.btnCustomer);
                    reducer.start();

                    accountType = "ShopOwner";
                    changeSize(1f, 0.9f, 20f, 10f, accountType, R.drawable.btn_blue);
                }, 190);

                return true;
            } else {
                return false;
            }
        });

        b.btnCustomer.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                //reduce size animation if button down

                AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.reduce_size);
                reducer.setTarget(b.btnCustomer);
                reducer.start();

                b.btnShop.setEnabled(false);

                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                //regain size animation in button UP

                AnimatorSet gainer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.regain_size);
                gainer.setTarget(b.btnCustomer);
                gainer.start();

                b.btnShop.setEnabled(true);

                // perform changes to the selection and next button
                b.btnCustomer.postDelayed(() -> {

                    //wait 190 sec (10 sec less than animation end time) then reduce size of another button

                    AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.second_reduce_size);
                    reducer.setTarget(b.btnShop);
                    reducer.start();

                    accountType = "Customer";
                    changeSize(0.9f, 1f, 10f, 20f, accountType, R.drawable.btn_black);
                }, 190);

                return true;
            } else {
                return false;
            }
        });

        b.btnNext.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("accountType", accountType);
            overridePendingTransition(0, 0);
            startActivity(i);
        });
    }

    private void changeSize(float sValue, float cValue, float sElevation, float cElevation, String contAs, int btnBackground) {

        b.btnCustomer.setScaleX(cValue);
        b.btnCustomer.setScaleY(cValue);

        b.btnShop.setScaleX(sValue);
        b.btnShop.setScaleY(sValue);

        b.btnShop.setElevation(sElevation); //20f for big & 8f for small
        b.btnCustomer.setElevation(cElevation);

        b.btnNext.setText("Continue as " + contAs);
        b.btnNext.setBackgroundResource(btnBackground);
    }
}