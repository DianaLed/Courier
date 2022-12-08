package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        Button next;
        next=(Button)findViewById(R.id.SWnext);
        TextView message;
        message=(TextView)findViewById(R.id.SWmessage);

        //Нужно имя с сервера !!!

        String nameFromServ="Иван";
        message.setText("Здраствуйте "+ nameFromServ + ",\nвы готовы начать работу?");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartWork.this, GetRestor.class);
                startActivity(intent);
            }
        });
    }
}