package com.example.assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btVedio ,Btassessment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btVedio=(Button)findViewById(R.id.video);
        Btassessment=(Button)findViewById(R.id.assessemnt);
        Btassessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Daskbordintent= new Intent(MainActivity.this,Daskboard.class);
                startActivity(Daskbordintent);
            }
        });
        btVedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VedioIntent= new Intent(MainActivity.this, vedio.class);
                startActivity(VedioIntent);
            }
        });
    }
}