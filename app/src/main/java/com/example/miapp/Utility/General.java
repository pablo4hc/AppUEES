package com.example.miapp.Utility;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class General extends Application {
    public static void navigateToActivity(Context context, Class<?> destinationActivity) {
        Intent intent = new Intent(context, destinationActivity);
        context.startActivity(intent);
    }
    public static Bitmap base64ToBitmap(String base64) {
        try {
            byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
