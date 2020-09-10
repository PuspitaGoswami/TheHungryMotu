package com.example.user.thehungrymotu;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.thehungrymotu.R;

public class PlayOptionActivity extends AppCompatActivity {

    private Button NewGame;
    private Button Resume, back;
    private HungryMotuView gameView;
    private SoundPlayer sound;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_option);

        NewGame = (Button) findViewById(R.id.play);
        Resume = (Button) findViewById(R.id.Hscore);
        back = findViewById(R.id.back);

        NewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(PlayOptionActivity.this, StageActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences loadGame = getSharedPreferences("MySavedGameFile", Context.MODE_PRIVATE);
        int score = loadGame.getInt("savedScore", 0);
        int level = loadGame.getInt("savedLevel", 0);
        int speed = loadGame.getInt("savedSpeed", 0);
        int lifeCounter = loadGame.getInt("savedLife", 0);
        if(score > 0 || lifeCounter!=3) {
            Resume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent scoreIntent = new Intent(PlayOptionActivity.this, ResumeActivity.class);
                    startActivity(scoreIntent);
                    finish();
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(PlayOptionActivity.this, MenuActivity.class);
                startActivity(scoreIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(PlayOptionActivity.this, MenuActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
