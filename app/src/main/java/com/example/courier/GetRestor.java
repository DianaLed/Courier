package com.example.courier;
//package com.yandex.mapkitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;
import java.util.List;

public class GetRestor extends AppCompatActivity {
    private MapView mapview;
    private TextView textRecom, textCoise;
    private Button next;
    private ListView listActivity;

    //Для получения данных с сервера
    private Double mainPoint1, mainPoint2;
    private String mainAdr;
    private List<Double> placesP1 = new ArrayList<Double>();
    private List<Double> placesP2 = new ArrayList<Double>();
    private List<String> adressOfRest = new ArrayList<String>();
    private Boolean choise= new Boolean(false);
    private int nomCh;
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_gr);
            mapview = (MapView) findViewById(R.id.mapview); //подвязваем
        }

        Serv_get_info();//заполняем данные с сервера через функцию
        Serv_get_main_rest(); //Принимаем с сервера самый нужный ресторан через функцию
        addPoint(mainPoint1, mainPoint2); //Устанавливаем карту на главный ресторан

        textRecom =findViewById(R.id.TVResomend);
        textCoise=findViewById(R.id.TVchoise);
        textRecom.setText("Мы рекомендуем выбрать ресторан по адресу: "+mainAdr+ ".");

        ImageView im=findViewById(R.id.imageView);
        ImageView im2=findViewById(R.id.imageView2);
        next=findViewById(R.id.bnext);

        im2.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        textCoise.setVisibility(View.INVISIBLE);
        listActivity=(ListView)findViewById(R.id.list);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_zak=new Intent(GetRestor.this, GetZak.class);
                if(choise){
                    intent_zak.putExtra("p1", mainPoint1);
                    intent_zak.putExtra("p2",  mainPoint2);
                }else{
                    intent_zak.putExtra("p1", placesP1.get(nomCh));
                    intent_zak.putExtra("p2",  placesP2.get(nomCh));
                }
                startActivity(intent_zak);
                //!!! тут отправляешь выбранный ресторан на сервер.
            }
        });
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choise){im.setImageResource(R.drawable.badchoise);
                    choise=false;
                    next.setVisibility(View.INVISIBLE);
                    mapview.getMap().getMapObjects().clear(); //зачистить карту
                    textCoise.setVisibility(View.INVISIBLE);
                    im2.setVisibility(View.INVISIBLE);
                }else{
                    im.setImageResource(R.drawable.goodchoise);
                    im2.setImageResource(R.drawable.badchoise);
                    choise=true;
                    next.setVisibility(View.VISIBLE);
                    addPoint(mainPoint1, mainPoint2);
                    textCoise.setText("Вы выбрали ресторан по адресу: "+ mainAdr+ ".");
                    textCoise.setVisibility(View.VISIBLE);
                    im2.setVisibility(View.VISIBLE);
                }
            }
        });

        //работа с list act

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, adressOfRest);

        listActivity.setAdapter(adapter);
        listActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                nomCh=position;
                addPoint(placesP1.get(position), placesP2.get(position));
                textCoise.setText("Вы выбрали ресторан по адресу: "+ adressOfRest.get(position)+ ".");
                textCoise.setVisibility(View.VISIBLE);
                im2.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                im.setImageResource(R.drawable.badchoise);
                im2.setImageResource(R.drawable.goodchoise);
                choise= false;
            }
        });

    }

    void addPoint(Double a, Double b){
        mapview.getMap().move(
                new CameraPosition(new Point(a, b), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null); //выставляем центр карты
        mapview.getMap().getMapObjects().clear(); //зачистить карту
        Point mappoint= new Point(a, b);
        mapview.getMap().getMapObjects().addPlacemark(mappoint);
    }
    void Serv_get_main_rest(){
        //!!!Заполняешь рекомендованный ресторан
        mainPoint1=56.1943;
        mainPoint2=44.0007;
        mainAdr="Комарова 22";
        choise=false;
    }
        void Serv_get_info(){
            //заполняешь  placesP1 !!!
            // placesP2
            // adressOfRest

            //для примера работоспособности, потом удалишь
            adressOfRest.add("Пушкина 2");
            adressOfRest.add("Гагарина 128");
            placesP1.add(56.1950);
            placesP1.add(56.1955);
            placesP2.add(44.0005);
            placesP2.add(44.00010);

            for(int i=0; i<placesP1.size(); i++ ){
                Point mappoint= new Point(placesP1.get(i), placesP2.get(i)); //Установила точку нa место.
                mapview.getMap().getMapObjects().addPlacemark(mappoint);
            }
        }

    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }


}
