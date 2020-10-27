package com.ivandeveloper.newbaseballapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivandeveloper.newbaseballapp.controller.GlobalController;
import com.ivandeveloper.newbaseballapp.controller.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    CountDownTimer countDownTimer;
    GlobalController globalController;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        globalController = new GlobalController(SplashScreen.this);
        globalController.clearContents();
        globalController.saveGames();
        globalController.saveTeams();
        globalController.saveStandings();
        globalController.saveLeagues();

        imageView = findViewById(R.id.splash_imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        imageView.startAnimation(animation);
        loop();

//            countDownTimer = new CountDownTimer(3000,1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                @Override
//                public void onFinish() {

//                }
//            }.start();

    }

    public void loop() {
        start();
    }

    public void start() {
        countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (globalController.retrieveGames() == null ||
                        globalController.retrieveLeagues() == null ||
                        globalController.retrieveStandings() == null ||
                        globalController.retrieveTeams() == null) {
                    loop();
                } else {
                    globalController.NextIntent(MainActivity.class);
                    finish();
                }
            }
        }.start();

    }
}