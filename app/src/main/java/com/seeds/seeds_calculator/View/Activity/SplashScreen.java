package com.seeds.seeds_calculator.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.seeds.seeds_calculator.R;
import com.seeds.seeds_calculator.Utils.Helper;


public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Helper.recordEventView("SplashScreen");
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent openMainActivity = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(openMainActivity);
            finish();
        }, 850);
    }
}