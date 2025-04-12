package com.example.miapp.Utility;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class General extends Application {
    public static void navigateToActivity(Context context, Class<?> destinationActivity) {
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }
}
