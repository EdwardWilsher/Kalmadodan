package com.example.kalmado_dan;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDataActivity extends AppCompatActivity {

    private Data data;
    private String str;
    private int[] st = {0,1,2,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);

        Data data = new Data(this);

        ArrayList<String> arrli = new ArrayList<String>();
        arrli = data.getData();

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Anxiety  \n Severity ");
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Date ");
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Description ");
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("  ");
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < arrli.size(); i++) {
            str = arrli.get(i);
            int k = 1;
            for(int j = 0; j < str.length(); j++){
                char temp = str.charAt(j);
                if (temp == '|'){
                    st[k] = j;
                    k++;
                }
            }
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(str.substring(st[0],st[1]));
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(str.substring(st[1]+1,st[2]));
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(str.substring(st[2]+1,str.length()));
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("Delete");
            t4v.setTextColor(0xFF0000DD);
            t4v.setGravity(Gravity.CENTER);
            t4v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }
}
