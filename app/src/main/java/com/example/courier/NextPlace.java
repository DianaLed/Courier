package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class NextPlace extends AppCompatActivity {
    private MapView mapview;
    private double resP1, resP2;
    private String adress, comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_plase);
        servGetInfo();
        mapview = (MapView) findViewById(R.id.mapview1); //подвязваем
        mapview.getMap().move(
                new CameraPosition(new Point(resP1, resP2), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null); //выставляем центр карты
        addPoint(resP1, resP2); //Устанавливаем карту на главный ресторан
        TextView ad, com;
        ad=findViewById(R.id.tVAd);
        ad.setText("Адрес: "+adress+".");
        com=findViewById(R.id.tvComment);
        com.setText("Комментарии: "+comments+".");
        Button next=(Button) findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //!!! Тут отправляешь на сервер что водитель прибыл на эту точку и ему нужна следующая или конц смены.

//                {  //след точка
//                    Intent intentNextPoint=new Intent(NextPlace.this, NextPlace.class);
//                    startActivity(intentNextPoint);
//                }
                { //конец смены
                    Intent intentMenuEnd=new Intent(NextPlace.this, EndWork.class);
                    startActivity(intentMenuEnd);
                }

            }
        });
    }
    void servGetInfo(){
        //!!!
        //Тут пример
        resP1=56.1800;
        resP2=44.0070;
        adress="Ульянова 10Б";
        comments= "Частный дом";
    }
    void addPoint(Double a, Double b){
        Point mappoint= new Point(a, b); //Установила точку на рандом место.
        mapview.getMap().getMapObjects().addPlacemark(mappoint);
    }
}