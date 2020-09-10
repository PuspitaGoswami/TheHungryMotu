package com.example.user.thehungrymotu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(HelpActivity.this, MenuActivity.class);
                startActivity(scoreIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(HelpActivity.this, MenuActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
