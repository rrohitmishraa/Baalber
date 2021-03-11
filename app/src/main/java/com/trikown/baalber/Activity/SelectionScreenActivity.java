package com.trikown.baalber.Activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.Utils.Exit;

public class SelectionScreenActivity extends AppCompatActivity {

    LinearLayout mBtnShop, mBtnCustomer;
    CoordinatorLayout mSelectRootLayout;
    TextView mBtnNext;
    String accountType = "Customer";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        mBtnCustomer = findViewById(R.id.xBtnCustomer);
        mBtnShop = findViewById(R.id.xBtnShop);
        mBtnNext = findViewById(R.id.xBtnNext);

        mSelectRootLayout = findViewById(R.id.xSelectRootLayout);

        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mSelectRootLayout);

        mBtnShop.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                //reduce size animation if button down

                AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.reduce_size);
                reducer.setTarget(mBtnShop);
                reducer.start();

                mBtnCustomer.setEnabled(false);

                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                //regain size animation in button UP

                AnimatorSet gainer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.regain_size);
                gainer.setTarget(mBtnShop);
                gainer.start();

                mBtnCustomer.setEnabled(true);


                // perform changes to the selection and next button
                mBtnShop.postDelayed(() -> {

                    //wait 290 sec (10 sec less than animation end time) then reduce size of another button

                    AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.second_reduce_size);
                    reducer.setTarget(mBtnCustomer);
                    reducer.start();

                    accountType = "Shop Owner";
                    changeSize(1f, 0.9f, 20f, 10f, accountType, R.drawable.btn_blue);
                }, 190);

                return true;
            } else {
                return false;
            }
        });

        mBtnCustomer.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                //reduce size animation if button down

                AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.reduce_size);
                reducer.setTarget(mBtnCustomer);
                reducer.start();

                mBtnShop.setEnabled(false);

                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                //regain size animation in button UP

                AnimatorSet gainer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.regain_size);
                gainer.setTarget(mBtnCustomer);
                gainer.start();

                mBtnShop.setEnabled(true);

                // perform changes to the selection and next button
                mBtnCustomer.postDelayed(() -> {

                    //wait 190 sec (10 sec less than animation end time) then reduce size of another button

                    AnimatorSet reducer = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.second_reduce_size);
                    reducer.setTarget(mBtnShop);
                    reducer.start();

                    accountType = "Customer";
                    changeSize(0.9f, 1f, 10f, 20f, accountType, R.drawable.btn_black);
                }, 190);

                return true;
            } else {
                return false;
            }
        });

        mBtnNext.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("accountType", accountType);
            startActivity(i);
        });
    }

    private void changeSize(float sValue, float cValue, float sElevation, float cElevation, String contAs, int btnBackground) {

        // change the height and width of selector button on click

        /*RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBtnShop.getLayoutParams();
        params.height = sValue; //(475) & 200 in  xml
        params.width = sValue;
        mBtnShop.setLayoutParams(params);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) mBtnCustomer.getLayoutParams();
        params2.height = cValue; //(380) & 160 in xml
        params2.width = cValue;
        mBtnCustomer.setLayoutParams(params2);*/

        mBtnCustomer.setScaleX(cValue);
        mBtnCustomer.setScaleY(cValue);

        mBtnShop.setScaleX(sValue);
        mBtnShop.setScaleY(sValue);

        mBtnShop.setElevation(sElevation); //20f for big & 8f for small
        mBtnCustomer.setElevation(cElevation);


        mBtnNext.setText("Continue as " + contAs);
        mBtnNext.setBackgroundResource(btnBackground);
    }

    @Override
    public void onBackPressed() {
        Exit exit = new Exit(SelectionScreenActivity.this);
        exit.exitActivity();
        super.onBackPressed();
    }
}