package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView ;
    SeekBar seekBar;
    Boolean alarmactive= false;
    Button StopButton;
    CountDownTimer countDownTimer;

    public void timerStart(View view) {
        if (alarmactive) {
            textView.setText("00 : 30 ");
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            countDownTimer.cancel();
            StopButton.setText("Go !");
            alarmactive=false;



        } else {

            alarmactive = true;
            seekBar.setEnabled(false);
            StopButton.setText("Stop ! ");

             countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateSecond((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.endvoice);
                    mediaPlayer.start();
                    Log.i("info", "finish");

                }
            }.start();
        }
    }

    public void updateSecond(int secondLeft)
    {
        int min = secondLeft / 60;
        int second = secondLeft - (min*60);
        String SecondString = Integer.toString(second);
            if(second<=9)
            {
                SecondString="0"+SecondString;
            }

        textView.setText(Integer.toString(min)+ ":" +SecondString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         seekBar = (SeekBar) findViewById(R.id.seekBar);
         textView = (TextView) findViewById(R.id.textView);
           StopButton = findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSecond(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
