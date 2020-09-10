package com.example.user.thehungrymotu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StageActivity extends AppCompatActivity {

    private Button easy, hard, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        easy = findViewById(R.id.easy);
        hard = findViewById(R.id.hard);
        back = findViewById(R.id.back);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("diff", false);
                editor.apply();
                Intent mainIntent = new Intent(StageActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("diff", true);
                editor.apply();
                Intent mainIntent = new Intent(StageActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(StageActivity.this, PlayOptionActivity.class);
                startActivity(scoreIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(StageActivity.this, PlayOptionActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
