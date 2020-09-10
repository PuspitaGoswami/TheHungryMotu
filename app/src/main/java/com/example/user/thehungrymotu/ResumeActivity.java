package com.example.user.thehungrymotu;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ResumeActivity extends AppCompatActivity {

    private HungryMotuView gameView;
    private Handler handler = new Handler();
    MediaPlayer mediaPlayer;
    private boolean hitSound, difficulty;
    private final static long Interval = 30;
    private SoundPlayer sound;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sound = new SoundPlayer(this);
        mediaPlayer = MediaPlayer.create(ResumeActivity.this, R.raw.music);
        mediaPlayer.setLooping(true);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences loadGame = getSharedPreferences("MySavedGameFile", Context.MODE_PRIVATE);
        int score = loadGame.getInt("savedScore", 0);
        int level = loadGame.getInt("savedLevel", 0);
        int speed = loadGame.getInt("savedSpeed", 0);
        int lifeCounter = loadGame.getInt("savedLife", 0);
        if (sharedPreferences.getBoolean("sound", true)) {
            mediaPlayer.start();

        }
        if (sharedPreferences.getBoolean("sound1", true)) {
            hitSound = true;
        }
        if (sharedPreferences.getBoolean("diff", true)) {
            difficulty = true;
        } else {
            difficulty = false;
        }

        gameView = new HungryMotuView(this, score, level, speed, lifeCounter, sound, hitSound, difficulty);
        setContentView(gameView);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        SharedPreferences saveGame = getSharedPreferences("MySavedGameFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveGame.edit();
        editor.putInt("savedScore", gameView.getScore()).putInt("savedLevel", gameView.getLevel()).putInt("savedSpeed", gameView.getSpeed()).putInt("savedLife", gameView.getLifeCounter());
        editor.apply();
    }


    @Override
    public void onBackPressed() {
        SharedPreferences saveGame = getSharedPreferences("MySavedGameFile", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = saveGame.edit();
        editor2.putInt("savedScore", gameView.getScore()).putInt("savedLevel", gameView.getLevel()).putInt("savedSpeed",
                gameView.getSpeed()).putInt("savedLife", gameView.getLifeCounter());
        editor2.apply();
        pauseMethod();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to exit?")
                .setCancelable(false)
                .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.super.onBackPressed();
                        Intent mainIntent = new Intent(ResumeActivity.this, PlayOptionActivity.class);
                        startActivity(mainIntent);
                        finish();

                    }
                })
                .setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        mediaPlayer.start();
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        gameView.invalidate();
                                    }
                                });
                            }
                        }, 0, Interval);

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void pauseMethod() {
        timer.cancel();
        timer = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences saveGame = getSharedPreferences("MySavedGameFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveGame.edit();
        editor.putInt("savedScore", gameView.getScore()).putInt("savedLevel", gameView.getLevel()).putInt("savedSpeed", gameView.getSpeed()).putInt("savedLife", gameView.getLifeCounter());
        editor.apply();
    }

}