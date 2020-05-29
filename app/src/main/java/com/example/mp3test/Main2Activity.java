package com.example.mp3test;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Button btnplaymp4;
    private SurfaceView msurfaceView;
    private MediaPlayer mp4;
    private SurfaceHolder msurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnplaymp4=(Button)findViewById(R.id.btn_playmp4);
        msurfaceView=(SurfaceView)findViewById(R.id.surfaceView);
        mp4=new MediaPlayer();
        mp4=MediaPlayer.create(Main2Activity.this,R.raw.demo);
        msurfaceHolder=msurfaceView.getHolder();
        msurfaceHolder.addCallback((SurfaceHolder.Callback)this);

        //按钮监听事件
        btnplaymp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp4.start();
            }
        });
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        mp4.setDisplay(holder);
    }

    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height)
    {

    }
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
}
