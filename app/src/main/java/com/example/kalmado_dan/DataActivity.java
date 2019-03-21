package com.example.kalmado_dan;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
    private Spinner M;
    public boolean DM;
    private Spinner Ye;
    private int[] date = {0,0,0};
    private String str;
    private int[] st = {0,1,2,3};
    private int[] ss = {0,1};
    private final int Severity = 0;
    private final int Day = 0;
    private final int Month = 0;
    private final int Year = 0;


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

        M = (Spinner)findViewById(R.id.spinner3);
        Ye = (Spinner)findViewById(R.id.spinner4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        M.setAdapter(adapter);

        Data data = new Data(this);

        ArrayList<String> arrli = new ArrayList<String>();
        arrli = data.getData();

        String[][] vals = new String[arrli.size()][4];

        for (int i = 0; i < arrli.size(); i++) {
            str = arrli.get(i);
            int k = 1;
            int l = 0;
            for (int j = 0; j < str.length(); j++) {
                char temp = str.charAt(j);
                if (temp == '|') {
                    st[k] = j;
                    k++;
                }
                if (temp == '/') {
                    ss[l] = j;
                    l++;
                }
            }
            vals[i][Severity] = str.substring(0, st[1]);
            vals[i][Day] = str.substring(st[1]+1, ss[0]);
            vals[i][Month] = str.substring(ss[0]+1, ss[1]);
            vals[i][Year] = str.substring(ss[1]+1, st[2]);
        }

        Calendar cal = Calendar.getInstance();
        date[0]  = cal.get(Calendar.YEAR);
        date[1] = 1 + cal.get(Calendar.MONTH);
        date[2]  = cal.get(Calendar.DAY_OF_MONTH);

        ArrayList<String> years = new ArrayList<String>();
        years.add(Integer.toString(date[0]));

        for (int i = 0; i < date.length; i++){
            if(!years.contains(vals[i][Year])){
                years.add(vals[i][Year]);
            }
        }

        ArrayAdapter<String> YA = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Ye.setAdapter(YA);



        M.setSelection(date[1]);
        Ye.setSelection(0);


        DM = true;

        if (DM) {
            month(date[1],date[0]);
        }

        M.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Ye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                refresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void refresh(){

        DM = false;
        if(M.getSelectedItemPosition() == 0){
            DM = true;
        }
        //if(DM){
            month(M.getSelectedItemPosition(), Integer.parseInt(String.valueOf(Ye.getSelectedItem())));
        //}else{
            //year(Ye.getSelectedItemPosition());
        //}
    }

    private void year(int selectedItemPosition) {
        //GraphView graph = (GraphView) findViewById(R.id.graph);
        //graph.getViewport().setScrollable(true);
        //graph.getViewport().setXAxisBoundsManual(true);
        //graph.removeAllSeries();
    }

    private void month(int month, int year){
        int[] Y = new int[32];
        Data data = new Data(this);

        ArrayList<String> arrli = new ArrayList<String>();
        arrli = data.getData();

        //data.deleteData(arrli);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

        });
        graph.addSeries(series);


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
            String TmpY = str.substring(ss[1]+1, st[2]);
            if ((Integer.parseInt(Tmp) == month)&&(Integer.parseInt(TmpY)== year)){
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
