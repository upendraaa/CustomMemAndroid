package com.example.upendra.testapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView marvelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        marvelText = findViewById(R.id.marvelText);

        marvelText.startAnimation((Animation)
                AnimationUtils.loadAnimation(this,R.anim.animate_text));

    }

    private Handler updateHandler = new Handler();
    private Runnable updateRunnable;

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                updateHandler.post(updateRunnable);
            }
        }).start();

        updateRunnable = new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));

            }
        };
    }
}
