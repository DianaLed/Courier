package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_work);
        Button newRes=(Button) findViewById(R.id.button5);
        Button close=(Button) findViewById(R.id.button7);
        newRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //!!! Работаем дальше, идем на новый ресторан
                    Intent intentResAg=new Intent(EndWork.this, GetRestor.class);
                    startActivity(intentResAg);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //!!! Смена закрыта, можешь отправлять
                Intent intentClose=new Intent(EndWork.this, Close.class);
                startActivity(intentClose);
            }
        });
    }
}