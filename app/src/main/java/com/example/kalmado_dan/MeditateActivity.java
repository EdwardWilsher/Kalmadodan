package com.example.kalmado_dan;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;


public class MeditateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static final int REQUEST_CODE = 1;
    ArrayAdapter<String> songs;
    ArrayList<String> songsList = new ArrayList<String>();
    ArrayList<Uri> uris = new ArrayList<Uri>();
    Spinner musicList;
    String currentChoice = "Default";
    boolean stopped = true;
    boolean close = false;
    MediaPlayer musicPlay;
    SeekBar progress;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        musicPlay = MediaPlayer.create(MeditateActivity.this,R.raw.emotionalpianosong);
        musicList = findViewById(R.id.spinner);
        songsList.add("Default");
        songs = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsList);
        songs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uris.add(Uri.parse("android.resource://" + this.getPackageName() + "/raw/emotionalpianosong"));
        musicList.setAdapter(songs);
        musicList.setOnItemSelectedListener(this);

        Button homeButton = findViewById(R.id.SettingsButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicPlay.isPlaying()) {
                    musicPlay.stop();
                    stopped = true;
                }
                close = true;
                Intent i5 = new Intent(MeditateActivity.this, MainActivity.class);
                startActivity(i5);
            }
        });

        Button relax = findViewById(R.id.SettingsButton);
        relax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicPlay.isPlaying()) {
                    musicPlay.stop();
                    stopped = true;
                }
                close = true;
                finish();
            }
        });

        Button exit = findViewById(R.id.BackButton);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopped = true;
                close = true;
                System.exit(0);
            }
        });

        ImageView next = findViewById(R.id.imageView5);
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick (View v) {
                if (musicPlay.isPlaying()) {
                    musicPlay.stop();
                }
                int currentPos = songsList.indexOf(currentChoice);
                if (currentPos == songsList.size() - 1) {
                    currentPos = -1;
                }
                currentChoice = songsList.get(currentPos + 1);
                try {
                    musicPlay.reset();
                    musicPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    musicPlay.setDataSource(getApplicationContext(), uris.get(songsList.indexOf(currentChoice)));
                    musicPlay.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                musicPlay.start();
                musicList.setSelection(currentPos + 1);
                stopped = false;
            }
        });

        ImageView prev = findViewById(R.id.imageView6);
        prev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick (View v) {
                if (musicPlay.isPlaying()) {
                    musicPlay.stop();
                }
                int currentPos = songsList.indexOf(currentChoice);
                if (currentPos == 0) {
                    currentPos = songsList.size();
                }
                currentChoice = songsList.get(currentPos - 1);
                try {
                    musicPlay.reset();
                    musicPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    musicPlay.setDataSource(getApplicationContext(), uris.get(songsList.indexOf(currentChoice)));
                    musicPlay.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                musicPlay.start();
                musicList.setSelection(currentPos - 1);
                stopped = false;
            }
        });

        ImageView play = findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {
                if (!musicPlay.isPlaying() && currentChoice == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    //View popupView = inflater.inflate(R.layout.popup_window, null);
                    //final PopupWindow popupWindow = new PopupWindow(popupView, 100, 100, true);
                   // popupView.setOnTouchListener(new View.OnTouchListener() {
                      //  @Override
                       // public boolean onTouch(View v, MotionEvent event) {
                           // popupWindow.dismiss();
                         //   return true;
                      //  }
                  //  });
                }
                else if (!musicPlay.isPlaying()) {
                    try {
                        musicPlay.reset();
                        musicPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        musicPlay.setDataSource(getApplicationContext(), uris.get(songsList.indexOf(currentChoice)));
                        musicPlay.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    musicPlay.start();
                }
                stopped = false;
            }
        });

        ImageView pause = findViewById(R.id.pauseButton);
        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (musicPlay.isPlaying()) {
                    musicPlay.pause();
                }
                stopped = true;
            }
        });

        //ImageView download = findViewById(R.id.imageView9);
        //download.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View v) {
                //Intent mediaIntent = new Intent(Intent.ACTION_GET_CONTENT);
                //mediaIntent.setType("audio/*");
                //startActivityForResult(mediaIntent, REQUEST_CODE);
            //}
        //});

        progress = findViewById(R.id.seekBar2);
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int level, boolean fromUser) {}

            public void onStartTrackingTouch(SeekBar seekBar) {
                stopped = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                float level2 = (float) seekBar.getProgress();
                musicPlay.seekTo((int) ((level2/(float)10)*musicPlay.getDuration()));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopped = false;
            }
        });

        this.changeSeekBar();
    }

    private void changeSeekBar() {
        Thread playingMusic = new Thread() {
            public void run() {
                while (!close) {
                    if (!stopped) {
                        progress.setProgress((int) (10 * ((float)musicPlay.getCurrentPosition() / (float)musicPlay.getDuration())));
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        playingMusic.start();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        currentChoice = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        currentChoice = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                uris.add(fileUri);
                songs.add(this.getName(fileUri));
                musicList.setAdapter(songs);
                currentChoice = "Default";
            }
        }
    }

    private String getName(Uri uri) {
        String returnVal = uri.getLastPathSegment();
        return returnVal;
    }
}
