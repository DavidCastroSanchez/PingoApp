package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;
import java.util.ArrayList;
import java.util.List;

public class ActivityYoNuncaClasico extends AppCompatActivity {

    //preparar el mazo de cartas
    private List<String> pila_preguntas;
    int k=0;
    int cantidad=49;
    int rango=49;
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
        setContentView(R.layout.activity_yo_nunca_clasico);

        //Mezclar el stack de las preguntas
        pila_preguntas= this.getAllPreguntas();
        //barajar las cartas
        for(int i=0;i<49;i++){
            numAleatorios[i]=(int) (Math.random()*rango);
            for(int j=0;j<i;j++){
                if(numAleatorios[i]==numAleatorios[j]) i--;
            }
        }

        //add banner + interstitial
        HwAds.init(this);
        AdParam adParam=new AdParam.Builder().build();

        BannerView topBannerView=new BannerView(this);
        topBannerView.setAdId("testw6vs28auh3");
        topBannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_SMART);
        topBannerView.loadAd(adParam);


        LinearLayout rootView = findViewById(R.id.layoutSuperiorYoNuncaClasico);
        rootView.addView(topBannerView);

        //Interstitial ad
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");
        interstitialAd.loadAd(adParam);

        btnSiguiente= (Button)findViewById(R.id.buttonSiguietneYoNuncaClasico);
        btnComoJugar= (Button)findViewById(R.id.buttonComoJugarYoNuncaClasico);
        btnSugerir= (Button)findViewById(R.id.buttonSugerirYoNuncaClasico);

        textViewPregunta=(TextView) findViewById(R.id.textViewYoNuncaClasico);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(k!=49) {
                    textViewPregunta.setText(pila_preguntas.get(numAleatorios[k]));
                    k++;
                    if(interstitialAd != null && interstitialAd.isLoaded() && k>10){
                        interstitialAd.show();
                    }
                }else{
                    AlertDialog.Builder alertaFinJuegoYoNuncaClasico = new AlertDialog.Builder(ActivityYoNuncaClasico.this);
                    alertaFinJuegoYoNuncaClasico.setMessage(getString(R.string.yo_nunca_fin_de_juego_texto))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.yo_nunca_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent=new Intent(ActivityYoNuncaClasico.this,ActivityYoNuncaClasico.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(getString(R.string.yo_nunca_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    AlertDialog titulo = alertaFinJuegoYoNuncaClasico.create();
                    titulo.setTitle(getString(R.string.yo_nunca_fin_de_juego_titulo));
                    titulo.show();
                }
            }
        });


        btnComoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertaYoNuncaComoJugar= new AlertDialog.Builder(ActivityYoNuncaClasico.this);
                alertaYoNuncaComoJugar.setMessage(getString(R.string.yo_nunca_como_jugar_texto))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.yo_nunca_como_jugar_boton_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alertaYoNuncaComoJugar.create();
                titulo.setTitle(getString(R.string.yo_nunca_como_jugar_titulo));
                titulo.show();
            }
        });



        btnSugerir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityYoNuncaClasico.this,ActivityDeSugerencias.class);
                startActivity(intent);

            }
        });


    }

    private List<String> getAllPreguntas(){
        return new ArrayList<String>(){{

            add(getString(R.string.yo_nunca_clasico_afirmacion_0));
            add(getString(R.string.yo_nunca_clasico_afirmacion_1));
            add(getString(R.string.yo_nunca_clasico_afirmacion_2));
            add(getString(R.string.yo_nunca_clasico_afirmacion_3));
            add(getString(R.string.yo_nunca_clasico_afirmacion_4));
            add(getString(R.string.yo_nunca_clasico_afirmacion_5));
            add(getString(R.string.yo_nunca_clasico_afirmacion_6));
            add(getString(R.string.yo_nunca_clasico_afirmacion_7));
            add(getString(R.string.yo_nunca_clasico_afirmacion_8));
            add(getString(R.string.yo_nunca_clasico_afirmacion_9));

            add(getString(R.string.yo_nunca_clasico_afirmacion_10));
            add(getString(R.string.yo_nunca_clasico_afirmacion_11));
            add(getString(R.string.yo_nunca_clasico_afirmacion_12));
            add(getString(R.string.yo_nunca_clasico_afirmacion_13));
            add(getString(R.string.yo_nunca_clasico_afirmacion_14));
            add(getString(R.string.yo_nunca_clasico_afirmacion_15));
            add(getString(R.string.yo_nunca_clasico_afirmacion_16));
            add(getString(R.string.yo_nunca_clasico_afirmacion_17));
            add(getString(R.string.yo_nunca_clasico_afirmacion_18));
            add(getString(R.string.yo_nunca_clasico_afirmacion_19));

            add(getString(R.string.yo_nunca_clasico_afirmacion_20));
            add(getString(R.string.yo_nunca_clasico_afirmacion_21));
            add(getString(R.string.yo_nunca_clasico_afirmacion_22));
            add(getString(R.string.yo_nunca_clasico_afirmacion_23));
            add(getString(R.string.yo_nunca_clasico_afirmacion_24));
            add(getString(R.string.yo_nunca_clasico_afirmacion_25));
            add(getString(R.string.yo_nunca_clasico_afirmacion_26));
            add(getString(R.string.yo_nunca_clasico_afirmacion_27));
            add(getString(R.string.yo_nunca_clasico_afirmacion_28));
            add(getString(R.string.yo_nunca_clasico_afirmacion_29));

            add(getString(R.string.yo_nunca_clasico_afirmacion_30));
            add(getString(R.string.yo_nunca_clasico_afirmacion_31));
            add(getString(R.string.yo_nunca_clasico_afirmacion_32));
            add(getString(R.string.yo_nunca_clasico_afirmacion_33));
            add(getString(R.string.yo_nunca_clasico_afirmacion_34));
            add(getString(R.string.yo_nunca_clasico_afirmacion_35));
            add(getString(R.string.yo_nunca_clasico_afirmacion_36));
            add(getString(R.string.yo_nunca_clasico_afirmacion_37));
            add(getString(R.string.yo_nunca_clasico_afirmacion_38));
            add(getString(R.string.yo_nunca_clasico_afirmacion_39));

            add(getString(R.string.yo_nunca_clasico_afirmacion_40));
            add(getString(R.string.yo_nunca_clasico_afirmacion_41));
            add(getString(R.string.yo_nunca_clasico_afirmacion_42));
            add(getString(R.string.yo_nunca_clasico_afirmacion_43));
            add(getString(R.string.yo_nunca_clasico_afirmacion_44));
            add(getString(R.string.yo_nunca_clasico_afirmacion_45));
            add(getString(R.string.yo_nunca_clasico_afirmacion_46));
            add(getString(R.string.yo_nunca_clasico_afirmacion_47));
            add(getString(R.string.yo_nunca_clasico_afirmacion_48));
            add(getString(R.string.yo_nunca_clasico_afirmacion_49));

        }};
    }

}
