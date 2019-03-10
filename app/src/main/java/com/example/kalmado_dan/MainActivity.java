package com.example.kalmado_dan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    final TestActivity testClass = new TestActivity();
    private Button Test;
    private Button Calm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Test = (Button)findViewById(R.id.testHomeButton);
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

        Calm = (Button)findViewById(R.id.relax);
        Calm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calm();
            }
        });
    }

    public void test(){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }

    public void calm(){
        Intent intent = new Intent(this,RelaxActivity.class);
        startActivity(intent);
    }

}
