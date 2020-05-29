package com.example.mp3test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private Button btnmp3;
    private Button btnmp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnmp3=(Button)findViewById(R.id.btn_mp3);
        btnmp4=(Button)findViewById(R.id.btn_mp4);

        btnmp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                startActivity( intent);
               // finish();
            }
        });

        btnmp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                startActivity( intent);
               // finish();
            }
        });

    }
}
