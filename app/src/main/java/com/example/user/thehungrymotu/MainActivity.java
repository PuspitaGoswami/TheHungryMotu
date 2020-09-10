package com.example.user.thehungrymotu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class
MainActivity extends AppCompatActivity {

    private HungryMotuView gameView;
    private SoundPlayer sound;
    private Handler handler = new Handler();
    private MediaPlayer mediaPlayer;
    private boolean hitSound, difficulty;
    private Timer timer;
    private final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sound = new SoundPlayer(this);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
        mediaPlayer.setLooping(true);

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
        gameView = new HungryMotuView(this, 0, 1, 11, 3, sound, hitSound, difficulty);

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
        SharedPreferences.Editor editor2 = saveGame.edit();
        editor2.putInt("savedScore", gameView.getScore()).putInt("savedLevel", gameView.getLevel()).putInt("savedSpeed", gameView.getSpeed()).putInt("savedLife", gameView.getLifeCounter());
        editor2.apply();
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
            mediaPlayer.pause();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to leave?")
                .setCancelable(false)
                .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.super.onBackPressed();
                        Intent mainIntent = new Intent(MainActivity.this, StageActivity.class);
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
}