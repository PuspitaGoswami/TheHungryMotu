package com.example.user.thehungrymotu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    private Animation animation;
    private ImageView imageView, imageView3, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10;
    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);

        imageView = findViewById(R.id.imageView);
        imageView3 = findViewById(R.id.imageView3);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);

        rorateAnimation();
        rorateAnimation3();
        rorateAnimation5();
        rorateAnimation6();
        rorateAnimation7();
        rorateAnimation8();
        rorateAnimation9();
        rorateAnimation10();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    dowork();
                    //sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent mainIntent = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
        thread.start();
    }

    public void dowork() {
        for (progress = 20; progress <= 100; progress = progress + 20) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void rorateAnimation10() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView10.startAnimation(animation);
    }

    private void rorateAnimation9() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView9.startAnimation(animation);
    }

    private void rorateAnimation8() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView8.startAnimation(animation);
    }

    private void rorateAnimation7() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView7.startAnimation(animation);
    }

    private void rorateAnimation6() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView6.startAnimation(animation);
    }

    private void rorateAnimation5() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView5.startAnimation(animation);
    }

    private void rorateAnimation3() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView3.startAnimation(animation);
    }

    private void rorateAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animation);
    }


}
