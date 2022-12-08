package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WaitAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_admin);

        //!!!
        //Устривай что хочешь, тут от админа приходит инфа)

        //Это я уберу, но вообще тут переход по кнопке на след экран.

        Button next=(Button) findViewById(R.id.button3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNext=new Intent(WaitAdmin.this, NextPlace.class);
                startActivity(intentNext);

            }
        });
    }
}