package com.example.foodapp.controller;

import android.content.Context;
import android.content.Intent;
import com.example.foodapp.ui.MainActivity;

/**
 * The SplashController manages navigation to the main activity after a splash screen.
 */
public class SplashController {

    /**
     * Navigates from the splash screen to the main activity and clears the back stack.
     *
     * @param context The application context.
     */
    public static void navigateToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
