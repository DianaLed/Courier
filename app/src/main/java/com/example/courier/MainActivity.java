package com.example.courier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapKitFactory.setApiKey("bebbd76d-6e91-4a77-bf63-70dcb9127799");
        MapKitFactory.setLocale("ru_RU");
        give();

        Button next;
        next=(Button)findViewById(R.id.button);
        TextView log, pass;
        log=(TextView)findViewById(R.id.TextPasswordLog);
        pass=(TextView)findViewById(R.id.TextPasswordPas);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log_s=log.getText().toString();
                String log_p=pass.getText().toString();
                //Отправляем на сервер, проверяем все ли хорошо
                //!!!
                if(true){
                    //Если все хорошо идем дальше
                    Intent intent=new Intent(MainActivity.this, StartWork.class);
                    startActivity(intent);
                }else{
                    //Если нет, то выводим сообщение
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неправильный логин или пароль.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
    void give(){
        MapKitFactory.initialize(this);
    }
}