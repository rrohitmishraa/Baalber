package com.trikown.baalber.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Exit {
    Context context;

    public Exit(Context context) {
        this.context = context;
    }

    public void exitActivity() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(a);
        ((Activity)context).finish();
    }
}
