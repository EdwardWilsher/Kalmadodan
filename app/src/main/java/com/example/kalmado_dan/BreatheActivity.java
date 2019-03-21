package com.example.kalmado_dan;

// needs dynamic button resizing

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BreatheActivity extends AppCompatActivity {

    int counter = 0;
    boolean stop = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        stop = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        Button homeButton = findViewById(R.id.SettingsButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop = true;
                Intent i = new Intent(BreatheActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button relax = findViewById(R.id.BackButton);
        relax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop = true;
                finish();
            }
        });

        Button exit = findViewById(R.id.BackButton);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });

        this.breathingExercises();
    }

    Handler handlerOut = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Button breathe = findViewById(R.id.BreatheCircle);
            breathe.setText("Breathe Out");
            breathe.setWidth(breathe.getWidth() - 10);
            breathe.setHeight(breathe.getHeight() - 10);
        }
    };

    Handler handlerIn = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Button breathe = findViewById(R.id.BreatheCircle);
            breathe.setText("Breathe In");
            breathe.setWidth(breathe.getWidth() - 10);
            breathe.setHeight(breathe.getHeight() - 10);
        }
    };

    private void breathingExercises() {
        Thread breathing = new Thread() {
            public void run() {
                while (!stop) {
                    counter = 0;
                    this.breatheOut();
                    counter = 0;
                    this.breatheIn();
                }
            }

            private void breatheOut() {
                Thread reduceSize = new Thread() {
                    public void run() {
                        counter++;
                        handlerOut.sendEmptyMessage(0);
                    }
                };

                while (counter < 400) {
                    reduceSize.run();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void breatheIn() {
                Thread increaseSize = new Thread() {
                    public void run() {
                        counter++;
                        handlerIn.sendEmptyMessage(0);
                    }
                };

                while (counter < 400) {
                    increaseSize.run();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        breathing.start();
    }
}
