package com.example.kalmado_dan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    final TestActivity testClass = new TestActivity();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calm = new Button(this);
        calm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                testClass.onCreate(savedInstanceState);
            }
        });

        Button test = new Button (this);
        calm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                testClass.onCreate(savedInstanceState);
            }
        });

        Button profile = new Button (this);
        calm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                testClass.onCreate(savedInstanceState);
            }
        });

        Button settings =  new Button (this);
        calm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                testClass.onCreate(savedInstanceState);
            }
        });
    }

}
