package com.example.user.thehungrymotu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.thehungrymotu.R;

public class GameOverActivity extends AppCompatActivity {

    private Button StartGameAgain;
    private Button GoToMenu;
    TextView scoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        StartGameAgain = (Button) findViewById(R.id.play_again_btn);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        GoToMenu = (Button) findViewById(R.id.home);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText("YOUR SCORE: " + score);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            //  highScoreLabel.setText("");
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        } else {
            /// highScoreLabel.setText("High Score: " + highScore);
        }

        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });



        GoToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(GameOverActivity.this, MenuActivity.class);
                startActivity(menuIntent);
                finish();
            }
        });

        SharedPreferences saveGame = getSharedPreferences("MySavedGameFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveGame.edit();
        editor.putInt("savedScore", 0).putInt("savedLevel", 1).putInt("savedSpeed", 11).putInt("savedLife", 3);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(GameOverActivity.this, MenuActivity.class);
        startActivity(mainIntent);
        finish();
    }


}
