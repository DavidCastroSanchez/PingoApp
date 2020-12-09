package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDeAyuda extends AppCompatActivity {

    private Button btn_contactar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_ayuda);
        btn_contactar= (Button)findViewById(R.id.buttonContactanos);

        btn_contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityDeAyuda.this,ActivityDeSugerencias.class);
                startActivity(intent);

            }
        });
    }
}
