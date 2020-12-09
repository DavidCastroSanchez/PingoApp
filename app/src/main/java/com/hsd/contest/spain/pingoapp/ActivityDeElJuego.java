package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import java.util.ArrayList;
import java.util.List;

public class ActivityDeElJuego extends AppCompatActivity {

    //preparar el stack de preguntas
    private List<String> pila_preguntas;
    int k=0;
    int cantidad=66;
    int rango=66;
    final int numAleatorios[]=new int[cantidad];

    //Botones
    private Button btnSiguiente;
    private Button btnComoJugar;
    private Button btnSugerir;

    //Text view de las preguntas
    private TextView textViewPregunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_el_juego);

        //Mezclar el stack de las preguntas
        pila_preguntas= this.getAllPreguntas();
        //barajar las cartas
        for(int i=0;i<66;i++){
            numAleatorios[i]=(int) (Math.random()*rango);
            for(int j=0;j<i;j++){
                if(numAleatorios[i]==numAleatorios[j]) i--;
            }
        }

        // interstitial
        HwAds.init(this);
        AdParam adParam=new AdParam.Builder().build();

        //Interstitial ad
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");
        interstitialAd.loadAd(adParam);

        btnSiguiente= (Button)findViewById(R.id.button_siguiente_el_juego);
        btnComoJugar= (Button)findViewById(R.id.button_como_jugar_el_juego);
        btnSugerir= (Button)findViewById(R.id.button_sugerencia_el_juego);

        textViewPregunta=(TextView) findViewById(R.id.textView_el_juego);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(k!=66) {
                    textViewPregunta.setText(pila_preguntas.get(numAleatorios[k]));
                    k++;
                    if(interstitialAd != null && interstitialAd.isLoaded() && k>10){
                        interstitialAd.show();
                    }
                }else{
                    AlertDialog.Builder alertaFinDeElJuego = new AlertDialog.Builder(ActivityDeElJuego.this);
                    alertaFinDeElJuego.setMessage(getString(R.string.el_juego_fin_de_juego_texto))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.el_juego_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent=new Intent(ActivityDeElJuego.this,ActivityDeElJuego.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(getString(R.string.el_juego_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    AlertDialog titulo = alertaFinDeElJuego.create();
                    titulo.setTitle(getString(R.string.el_juego_fin_de_juego_titulo));
                    titulo.show();
                }
            }
        });


        btnComoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertaElJuegoComoJugar= new AlertDialog.Builder(ActivityDeElJuego.this);
                alertaElJuegoComoJugar.setMessage(getString(R.string.el_juego_como_jugar_texto))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.el_juego_como_jugar_boton_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alertaElJuegoComoJugar.create();
                titulo.setTitle(getString(R.string.el_juego_como_jugar_titulo));
                titulo.show();
            }
        });



        btnSugerir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityDeElJuego.this,ActivityDeSugerencias.class);
                startActivity(intent);

            }
        });


    }

    private List<String> getAllPreguntas(){
        return new ArrayList<String>(){{

            add(getString(R.string.el_juego_frase_0));
            add(getString(R.string.el_juego_frase_1));
            add(getString(R.string.el_juego_frase_2));
            add(getString(R.string.el_juego_frase_3));
            add(getString(R.string.el_juego_frase_4));
            add(getString(R.string.el_juego_frase_5));
            add(getString(R.string.el_juego_frase_6));
            add(getString(R.string.el_juego_frase_7));
            add(getString(R.string.el_juego_frase_8));
            add(getString(R.string.el_juego_frase_9));

            add(getString(R.string.el_juego_frase_10));
            add(getString(R.string.el_juego_frase_11));
            add(getString(R.string.el_juego_frase_12));
            add(getString(R.string.el_juego_frase_13));
            add(getString(R.string.el_juego_frase_14));
            add(getString(R.string.el_juego_frase_15));
            add(getString(R.string.el_juego_frase_16));
            add(getString(R.string.el_juego_frase_17));
            add(getString(R.string.el_juego_frase_18));
            add(getString(R.string.el_juego_frase_19));

            add(getString(R.string.el_juego_frase_20));
            add(getString(R.string.el_juego_frase_21));
            add(getString(R.string.el_juego_frase_22));
            add(getString(R.string.el_juego_frase_23));
            add(getString(R.string.el_juego_frase_24));
            add(getString(R.string.el_juego_frase_25));
            add(getString(R.string.el_juego_frase_26));
            add(getString(R.string.el_juego_frase_27));
            add(getString(R.string.el_juego_frase_28));
            add(getString(R.string.el_juego_frase_29));

            add(getString(R.string.el_juego_frase_30));
            add(getString(R.string.el_juego_frase_31));
            add(getString(R.string.el_juego_frase_32));
            add(getString(R.string.el_juego_frase_33));
            add(getString(R.string.el_juego_frase_34));
            add(getString(R.string.el_juego_frase_35));
            add(getString(R.string.el_juego_frase_36));
            add(getString(R.string.el_juego_frase_37));
            add(getString(R.string.el_juego_frase_38));
            add(getString(R.string.el_juego_frase_39));

            add(getString(R.string.el_juego_frase_40));
            add(getString(R.string.el_juego_frase_41));
            add(getString(R.string.el_juego_frase_42));
            add(getString(R.string.el_juego_frase_43));
            add(getString(R.string.el_juego_frase_44));
            add(getString(R.string.el_juego_frase_45));
            add(getString(R.string.el_juego_frase_46));
            add(getString(R.string.el_juego_frase_47));
            add(getString(R.string.el_juego_frase_48));
            add(getString(R.string.el_juego_frase_49));

            add(getString(R.string.el_juego_frase_50));
            add(getString(R.string.el_juego_frase_51));
            add(getString(R.string.el_juego_frase_52));
            add(getString(R.string.el_juego_frase_53));
            add(getString(R.string.el_juego_frase_54));
            add(getString(R.string.el_juego_frase_55));
            add(getString(R.string.el_juego_frase_56));
            add(getString(R.string.el_juego_frase_57));
            add(getString(R.string.el_juego_frase_58));
            add(getString(R.string.el_juego_frase_59));

            add(getString(R.string.el_juego_frase_60));
            add(getString(R.string.el_juego_frase_61));
            add(getString(R.string.el_juego_frase_62));
            add(getString(R.string.el_juego_frase_63));
            add(getString(R.string.el_juego_frase_64));
            add(getString(R.string.el_juego_frase_65));
        }};
    }
}