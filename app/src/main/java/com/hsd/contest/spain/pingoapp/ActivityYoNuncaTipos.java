package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityYoNuncaTipos extends AppCompatActivity {

    private Button btn_yo_nunca_clasico;
    private Button btn_yo_nunca_caliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yo_nunca_tipos);

        btn_yo_nunca_clasico= (Button)findViewById(R.id.button_yo_nunca_clasico);
        btn_yo_nunca_caliente= (Button)findViewById(R.id.button_yo_nunca_caliente);

        btn_yo_nunca_clasico.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(ActivityYoNuncaTipos.this, ActivityYoNuncaClasico.class);
                   startActivity(intent);
               }
           }
        );
        btn_yo_nunca_caliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ActivityYoNuncaTipos.this,ActivityDeYoNunca.class);
                    startActivity(intent);
                }
            }
        );
    }
}