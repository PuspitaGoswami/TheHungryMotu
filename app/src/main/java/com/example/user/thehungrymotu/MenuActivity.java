package com.example.user.thehungrymotu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity {

    private Button playButton, helpButton, quit, hscore, playMusicButton, soundButton, aboutButton;
    private MediaPlayer mediaPlayer;
    private SoundPlayer sound;
    private boolean val, val1;
    private Toast toast;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private boolean playMusicBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mediaPlayer = MediaPlayer.create(MenuActivity.this, R.raw.menumusic);
        mediaPlayer.setLooping(true);

        playMusicButton = findViewById(R.id.playMusic);
        soundButton = findViewById(R.id.playSound);
        playButton = findViewById(R.id.play);
        helpButton = findViewById(R.id.help);
        quit = findViewById(R.id.quit);
        hscore = findViewById(R.id.highscore);
        aboutButton = findViewById(R.id.about);

        playMusicBool = sharedPreferences.getBoolean("sound", true);
        if (playMusicBool) {
            mediaPlayer.start();
            playMusicButton.setBackground(getResources().getDrawable(R.drawable.mon));
        } else {
            playMusicButton.setBackground(getResources().getDrawable(R.drawable.moff));
        }


        if (sharedPreferences.getBoolean("sound1", true)) {
            val1 = true;
        } else {
            val1 = false;
            soundButton.setBackground(getResources().getDrawable(R.drawable.soff));
        }


        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


        playMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    editor.putBoolean("sound", false).apply();
                    playMusicButton.setBackground(getResources().getDrawable(R.drawable.moff));
                } else {
                    mediaPlayer.start();
                    editor.putBoolean("sound", true).apply();
                    playMusicButton.setBackground(getResources().getDrawable(R.drawable.mon));
                }
            }
        });


        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (val1 == false) {
                    editor.putBoolean("sound1", true);
                    editor.apply();
                    val1 = true;
                    soundButton.setBackground(getResources().getDrawable(R.drawable.son));
                } else {
                    editor.putBoolean("sound1", false);
                    editor.apply();
                    val1 = false;
                    soundButton.setBackground(getResources().getDrawable(R.drawable.soff));
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlay();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetting();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWindow();
            }
        });

        hscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuActivity.this, HighScoreActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }

//    // public boolean getScore()
//    { //return this.val;
//    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    public void closeWindow() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void openPlay() {
        Intent intent = new Intent(this, PlayOptionActivity.class);
        startActivity(intent);
        finish();
    }

    public void openSetting() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeWindow();
    }
}
