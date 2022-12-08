package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;
import java.util.List;

public class GetZak extends AppCompatActivity {
    private MapView mapview;
    private double resP1, resP2;

    private List<Double> placesP1 = new ArrayList<Double>();
    private List<Double> placesP2 = new ArrayList<Double>();
    private List<String> adressStr = new ArrayList<String>();
    private List<Boolean> choise= new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_zak);
        Intent intent_zak = getIntent();
        resP1=intent_zak.getDoubleExtra("p1", 0);
        resP2=intent_zak.getDoubleExtra("p2", 0);

            mapview = (MapView) findViewById(R.id.mapview1); //подвязваем
        mapview.getMap().move(
                new CameraPosition(new Point(resP1, resP2), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null); //выставляем центр карты
        addPoint(resP1, resP2); //Устанавливаем карту на главный ресторан
        servGetAdrClient();

        ListView choiceList;
        choiceList = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, adressStr);
        choiceList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        choiceList.setAdapter(adapter);
        choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                SparseBooleanArray sparseBooleanArray = choiceList.getCheckedItemPositions();
                mapview.getMap().getMapObjects().clear();
                addPoint(resP1, resP2); //вернули точку ресторана
                for(int i=0; i<choise.size(); i++){
                    choise.set(i, sparseBooleanArray.get(i));
                    if(choise.get(i))addPoint(placesP1.get(i), placesP2.get(i));
                }
            }
        });

        Button next=(Button) findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //!!! Тут отправляешь на сервер что выбрал водила
                Intent intentWaitAdm=new Intent(GetZak.this, WaitAdmin.class);
                startActivity(intentWaitAdm);
            }
        });
    }
   void servGetAdrClient(){
        //!!!
       placesP1.add(56.1800);
       placesP2.add(44.0070);

       placesP1.add(56.1900);
       placesP2.add(44.0020);

       adressStr.add("Пушкина 12");
       adressStr.add("Лермонтова 4");

       choise.add(false);// нет-> этот адрес водитель еще не выбрал.
       choise.add(false);//

   }
    void addPoint(Double a, Double b){
        Point mappoint= new Point(a, b); //Установила точку на рандом место.
        mapview.getMap().getMapObjects().addPlacemark(mappoint);
    }
}