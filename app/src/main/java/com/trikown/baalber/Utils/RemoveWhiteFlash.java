package com.trikown.baalber.Utils;

import android.app.Activity;
import android.content.Context;
import android.transition.Fade;
import android.view.View;

import com.trikown.baalber.R;

public class RemoveWhiteFlash {

    public void removeWhiteFlash(Context context) {
        Fade fade = new Fade();
        View decor = ((Activity) context).getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        ((Activity) context).getWindow().setEnterTransition(fade);
    }
}
