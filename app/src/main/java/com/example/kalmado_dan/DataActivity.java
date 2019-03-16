package com.example.kalmado_dan;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataActivity extends AppCompatActivity {

    private Button Home;
    private Button Data;
    private Data data;
    private int[] date = {0,0,0};
    private String str;
    private int[] st = {0,1,2,3};
    private int[] ss = {0,1};
    private int[] Y = new int[32];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Home = (Button)findViewById(R.id.SettingsButton);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Data = (Button)findViewById(R.id.SubmitButton);
        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this,TableDataActivity.class);
                startActivity(intent);
            }
        });

        Data data = new Data(this);

        ArrayList<String> arrli = new ArrayList<String>();
        arrli = data.getData();

        //data.deleteData(arrli);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(31);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

        });
        graph.addSeries(series);


        Calendar cal = Calendar.getInstance();
        date[0]  = cal.get(Calendar.YEAR);
        date[1] = 1 + cal.get(Calendar.MONTH);
        date[2]  = cal.get(Calendar.DAY_OF_MONTH);

        Y[0] = 0;

        for (int i = 0; i < arrli.size(); i++) {
            str = arrli.get(i);
            int k = 1;
            int l = 0;
            for(int j = 0; j < str.length(); j++){
                char temp = str.charAt(j);
                if (temp == '|'){
                    st[k] = j;
                    k++;
                }
                if (temp == '/'){
                    ss[l] = j;
                    l++;
                }
            }
            String Tmp = str.substring(ss[0]+1,ss[1]);
            if (Integer.parseInt(Tmp) == date[1]){
                Y[Integer.parseInt(str.substring(st[1]+1,ss[0]))] = Integer.parseInt(str.substring(st[0],st[1]));
            }


        }

        int recent = 0;
        for (int i = 0; i< 31;i++){
            if (Y[i] != 0)   {
                series.appendData(new DataPoint(i,Y[i]), true,31);
                recent = i;
            } else {
                series.appendData(new DataPoint(i,Y[recent]), true,31);
            }

        }


        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(31);

    }
}
