package com.example.kalmado_dan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;




public class RelaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);

        Button meditateButton = findViewById(R.id.MeditateButton);
        meditateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(RelaxActivity.this, MeditateActivity.class);
                startActivity(i);
            }
        });

        Button breatheButton = findViewById(R.id.RelaxButton);
        breatheButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent j2 = new Intent(Calm.this, Breathe.class);
                //startActivity(j2);
            }
        });

        Button musicButton = findViewById(R.id.MusicButton);
        musicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent k2 = new Intent(Calm.this, Music.class);
                //startActivity(k2);
            }
        });

        Button homeButton = findViewById(R.id.SettingsButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
