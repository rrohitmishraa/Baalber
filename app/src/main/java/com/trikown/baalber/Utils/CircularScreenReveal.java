package com.trikown.baalber.Utils;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.trikown.baalber.R;

public class CircularScreenReveal {
    Context ctx;

    public CircularScreenReveal(Context ctx) {
        this.ctx = ctx;
    }

    public void layoutCheck(Bundle savedInstanceState, CoordinatorLayout layout) {
        if (savedInstanceState == null) {
            layout.setVisibility(View.INVISIBLE);

            //to check the height and width before the layout has been laid out using the tree observer
            ViewTreeObserver viewTreeObserver = layout.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        //Calling reveal from CircularReveal class
                        circularRevealActivity(layout);

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    public void circularRevealActivity(CoordinatorLayout layout) {
        //Getting the center of the screen
        int cx = layout.getWidth() / 2;
        int cy = layout.getHeight() / 2;

        float finalRadius = Math.max(layout.getWidth(), layout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, 0, finalRadius);
        circularReveal.setDuration(1000);

        // make the view visible and start the animation
        layout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    public void reverseCircularReveal(CoordinatorLayout layout) {
        int cx = layout.getWidth() / 2;
        int cy = layout.getHeight() / 2;

        float finalRadius = Math.max(layout.getWidth(), layout.getHeight());
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, finalRadius, 0);

        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                layout.setVisibility(View.INVISIBLE);
                ((Activity) ctx).finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        circularReveal.setDuration(400);
        circularReveal.start();
    }
}
