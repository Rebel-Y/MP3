package com.example.mp3test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btnstart;
    private Button btnpause;
    private SeekBar seekbar;
    private MediaPlayer mp3player;
    private SurfaceHolder surfaceHolder;
    //定时器
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isChanging=false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
   // private boolean iffirst = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart=(Button)findViewById(R.id.btn_Start);
        btnpause=(Button)findViewById(R.id.btn_Pause);
        seekbar=(SeekBar)findViewById(R.id.seekBar);

        seekbar.setOnSeekBarChangeListener(new myseekbar());

        mp3player=new MediaPlayer();
        mp3player=MediaPlayer.create(MainActivity.this,R.raw.test);


        mp3player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("完成");
                btnstart.setEnabled(true);
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp3player.isPlaying())
                {
                    mp3player.pause();
                    btnstart.setEnabled(true);
                }
                else{
                    play();
                    btnpause.setEnabled(true);
                    btnstart.setEnabled(false);
                }



            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstart.setEnabled(true);
                mp3player.pause();
                btnpause.setEnabled(false);
            }
        });


    }
    private void play() {
        try {
            System.out.println("正在播放...");

            //seekbar
            seekbar.setMax(mp3player.getDuration());//设置进度条
            //定时器记录播放进度
            mTimer=new Timer();
            mTimerTask=new TimerTask() {
                @Override
                public void run() {
                    if (isChanging==true){return;}
                    seekbar.setProgress(mp3player.getCurrentPosition());
                }
            };
            mTimer.schedule(mTimerTask,0,10);
           // iffirst=true;
            //播放
            mp3player.start();
            btnstart.setEnabled(true);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//进度条处理
    class myseekbar implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging=true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mp3player.seekTo(seekBar.getProgress());
            isChanging=false;
        }
    }


}
