package com.example.user.thehungrymotu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class HighScoreActivity extends AppCompatActivity {


    private Button back;
    TextView highScoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        back = findViewById(R.id.back);

        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        int score = getIntent().getIntExtra("SCORE", 0);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        if (score > highScore) {
            highScoreLabel.setText("" + score);
            SharedPreferences.Editor edit = settings.edit();
            edit.putInt("HIGH_SCORE", score);
            edit.commit();
        } else {
            highScoreLabel.setText("" + highScore);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(HighScoreActivity.this, MenuActivity.class);
                startActivity(scoreIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(HighScoreActivity.this, MenuActivity.class);
        startActivity(mainIntent);
        finish();
    }
}