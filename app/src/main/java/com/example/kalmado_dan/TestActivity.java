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
    private TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tView = (TextView) findViewById(R.id.textview1); // assigning the objects created in the Xml to a var
        testSubmitButton = (Button)findViewById(R.id.testSubmitButton);
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
        Q1 = (SeekBar)findViewById(R.id.Q1);
        Q1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                val = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView.setText("" + progress);
                tView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView.setY(seekBar.getY()+70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView.setY(seekBar.getY()+40);
            }
        });
    }

    public void testHome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void testSubmitData(){
        //TODO
    }
}