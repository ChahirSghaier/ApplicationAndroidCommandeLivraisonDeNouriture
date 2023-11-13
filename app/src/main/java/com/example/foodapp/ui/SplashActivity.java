package com.example.foodapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import com.example.foodapp.R;
import com.example.foodapp.controller.SplashController;

/**
 * The Splash Activity to initiate the application and navigate to the main activity.
 */
public class SplashActivity extends AppCompatActivity {

    private AppCompatButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Fetch the submit button from the layout
        submitButton = findViewById(R.id.submitButton);

        // Set a click listener on the submit button to navigate to the main activity
        submitButton.setOnClickListener(view -> {
            SplashController.navigateToMainActivity(this);
        });
    }
}
