package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_etilico;
    private Button btn_carreras;
    private Button btn_yoNunca;
    private Button btn_el_juego;
    private Button btn_ayuda;
    private final String GREETER="Prueba de cambio de activitys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_etilico= (Button)findViewById(R.id.button_etilico);
        btn_carreras= (Button)findViewById(R.id.button_carrera);
        btn_yoNunca= (Button)findViewById(R.id.button_yoNunca);
        btn_el_juego= (Button)findViewById(R.id.button_el_juego);
        btn_ayuda= (Button)findViewById(R.id.button_ayuda);

        btn_etilico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ActivityDePingo.class);

                startActivity(intent);
            }
        }
        );
        btn_carreras.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View view) {
                   Intent intent=new Intent(MainActivity.this,ActivityDeAutopista.class);
                   startActivity(intent);
               }
        }

        );
        btn_yoNunca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityYoNuncaTipos.class);
                startActivity(intent);
            }
        }

        );
        btn_el_juego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ActivityDeElJuego.class);
                startActivity(intent);
            }
        }

        );
        btn_ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityDeAyuda.class);
                startActivity(intent);
            }
        }

        );

    }

    
}
