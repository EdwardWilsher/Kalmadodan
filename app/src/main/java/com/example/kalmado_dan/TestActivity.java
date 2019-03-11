// I used relative as the main tag in the XML as it seems to be simpler and still widely used.
package com.example.kalmado_dan; // change to fit the main project

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class TestActivity extends AppCompatActivity {  //change

    private Button testSubmitButton;
    private Button testHomeButton;
    private SeekBar Q1;
    private SeekBar Q2;
    private SeekBar Q3;
    private SeekBar Q4;
    private TextView tView1;
    private TextView tView2;
    private TextView tView3;
    private TextView tView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tView1 = (TextView) findViewById(R.id.textview1); // assigning the objects created in the Xml to a var
        tView2 = (TextView) findViewById(R.id.textview2);
        tView3 = (TextView) findViewById(R.id.textview3);
        tView4 = (TextView) findViewById(R.id.textview4);

        testSubmitButton = (Button)findViewById(R.id.SubmitButton);
        testSubmitButton.setOnClickListener(new View.OnClickListener() { // creating an onclick event for the buttons. The function is defined below
            @Override
            public void onClick(View v) {
                testSubmitData();
            }
        });
        testHomeButton = (Button)findViewById(R.id.testHomeButton);
        testHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testHome();
            }
        });
        Q1 = (SeekBar)findViewById(R.id.seekBar1);
        Q1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView1.setText("" + progress);
                tView1.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView1.setY(seekBar.getY()+70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView1.setY(seekBar.getY()+40);
            }
        });

        Q2 = (SeekBar)findViewById(R.id.seekBar2);
        Q2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView2.setText("" + progress);
                tView2.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView2.setY(seekBar.getY()+70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView2.setY(seekBar.getY()+40);
            }
        });

        Q3 = (SeekBar)findViewById(R.id.seekBar3);
        Q3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView3.setText("" + progress);
                tView3.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView3.setY(seekBar.getY()+70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView3.setY(seekBar.getY()+40);
            }
        });

        Q4 = (SeekBar)findViewById(R.id.seekBar4);
        Q4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView4.setText("" + progress);
                tView4.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView4.setY(seekBar.getY()+70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView4.setY(seekBar.getY()+40);
            }
        });
    }

    public void testHome(){
        finish();
    }

    public void testSubmitData(){
        //TODO
    }
}