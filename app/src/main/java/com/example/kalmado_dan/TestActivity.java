// I used relative as the main tag in the XML as it seems to be simpler and still widely used.
package com.example.kalmado_dan; // change to fit the main project

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;


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
    private TextView Dset;
    private DatePickerDialog.OnDateSetListener DateSet;
    private int[] vals={3,3,3,3};
    private int[] date = {0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tView1 = (TextView) findViewById(R.id.textview1); // assigning the objects created in the Xml to a var
        tView2 = (TextView) findViewById(R.id.textview2);
        tView3 = (TextView) findViewById(R.id.textview3);
        tView4 = (TextView) findViewById(R.id.textview4);
        Dset = (TextView) findViewById(R.id.textView);

        Calendar cal = Calendar.getInstance();
        date[0]  = cal.get(Calendar.YEAR);
        date[1] = 1 + cal.get(Calendar.MONTH);
        date[2]  = cal.get(Calendar.DAY_OF_MONTH);


        Dset.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year  = cal.get(Calendar.YEAR);
                int month  = cal.get(Calendar.MONTH);
                int day  = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TestActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,DateSet,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        }));

        DateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date[0]  = year;
                date[1] = 1 + month;
                date[2]  = dayOfMonth;
            }
        };

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
                vals[0] = progress;
                val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                tView1.setText("" + progress);
                tView1.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                tView1.setY(seekBar.getY()+50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView1.setY(seekBar.getY()+30);
            }
        });

        Q2 = (SeekBar)findViewById(R.id.seekBar2);
        Q2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int val;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vals[1] = progress;
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
                vals[2] = progress;
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
                vals[3] = progress;
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
        int total = 0;

        for (int i = 0; i<4;i++){
            total = total+ vals[i];
        }

        //transmit total
        //transmit date
        //Intent intent = new Intent(this,<<the data class>>.class);
        //startActivity(intent);

        finish();

    }
}