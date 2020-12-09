package com.hsd.contest.spain.pingoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;


public class ActivityDePingo extends AppCompatActivity {

    private List<Carta> mazo_cartas;

    private Button btn_siguiente;
    private Button btn_comoJugar;
    private ImageView imageView;
    private TextView textView;
    int k=0;
    boolean inicio=false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_de_pingo);

        //ads
        //add banner
        HwAds.init(this);
        AdParam adParam=new AdParam.Builder().build();

        BannerView topBannerView=new BannerView(this);
        topBannerView.setAdId("testw6vs28auh3");
        topBannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_SMART);
        topBannerView.loadAd(adParam);

        LinearLayout rootView = findViewById(R.id.layoutSuperior);
        rootView.addView(topBannerView);

        //gestion del boton siguiente + como jugar
        btn_siguiente= (Button)findViewById(R.id.button_siguiente);
        btn_comoJugar= (Button)findViewById(R.id.button_ayuda);

        mazo_cartas= this.getAllCartas();

        int cantidad=48;
        int rango=48;


        final int numAleatorios[]=new int[cantidad];


        for(int i=0;i<48;i++){
            numAleatorios[i]=(int) (Math.random()*rango);
            for(int j=0;j<i;j++){
                if(numAleatorios[i]==numAleatorios[j]) i--;
            }

        }


        //getion del boton siguiente
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
                                           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                           @Override
                   public void onClick(View view) {

                       if(k!=48 ){

                           imageView.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
                           textView.setText(pruebaARealizar((int)mazo_cartas.get(numAleatorios[k]).getValor()));
                           k++;
                        }else{
                           if(k==48 && !inicio){

                               inicio=true;
                               imageView.setImageResource(R.drawable.reverso_carta);
                                textView.setText(getString(R.string.pingo_text_view_fin_de_juego));

                            }else{
                                   k=0;
                                   inicio=false;
                               AlertDialog.Builder alertaFinJuegoPINGO = new AlertDialog.Builder(ActivityDePingo.this);
                               alertaFinJuegoPINGO.setMessage(getString(R.string.pingo_fin_de_juego_texto))
                                       .setCancelable(false)
                                       .setPositiveButton(getString(R.string.pingo_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialogInterface, int i) {
                                               finish();
                                               Intent intent=new Intent(ActivityDePingo.this, ActivityDePingo.class);
                                               startActivity(intent);
                                           }
                                       })
                                       .setNegativeButton(getString(R.string.pingo_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialogInterface, int i) {
                                               finish();
                                           }
                                       });
                               AlertDialog titulo = alertaFinJuegoPINGO.create();
                               titulo.setTitle(getString(R.string.pingo_fin_de_juego_titulo));
                               titulo.show();
                                 }
                       }

                   }

               }
        );
        btn_comoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertaEtilicoComoJugar= new AlertDialog.Builder(ActivityDePingo.this);
                alertaEtilicoComoJugar.setMessage(getString(R.string.pingo_como_jugar_texto_0)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_1)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_2)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_3)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_4)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_5)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_6)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_7)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_8)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_9)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_10)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_11)+"\n" +
                        getString(R.string.pingo_como_jugar_texto_12)+"\n")
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.pingo_como_jugar_boton_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alertaEtilicoComoJugar.create();
                titulo.setTitle(getString(R.string.pingo_como_jugar_titulo));
                titulo.show();
            }
        });

      
        init();
        imageView.setImageResource(R.drawable.reverso_carta);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        this.imageView=findViewById(R.id.imagenCartaDelCardView);
        this.imageView.setClipToOutline(true);
        this.textView=findViewById(R.id.textViewPrueba);
    }
    private List<Carta> getAllCartas(){
        return new ArrayList<Carta>(){
            {
                add(new Carta("Uno_Oros", R.drawable.uno_oros,1, "oros","redondas"));
                add(new Carta("Uno_Copas", R.drawable.uno_copas,1,"copas","redondas"));
                add(new Carta("Uno_Bastos", R.drawable.uno_bastos,1,"bastos","picudas"));
                add(new Carta("Uno_Espadas", R.drawable.uno_espadas,1,"espadas","picudas"));

                add(new Carta("Dos_Oros", R.drawable.dos_oros,2, "oros","redondas"));
                add(new Carta("Dos_Copas", R.drawable.dos_copas,2,"copas","redondas"));
                add(new Carta("Dos_Bastos", R.drawable.dos_bastos,2,"bastos","picudas"));
                add(new Carta("Dos_Espadas", R.drawable.dos_espadas,2, "espadas","picudas"));

                add(new Carta("Tres_Oros", R.drawable.tres_oros,3, "oros","redondas"));
                add(new Carta("Tres_Copas", R.drawable.tres_copas,3,"copas","redondas"));
                add(new Carta("Tres_Bastos", R.drawable.tres_bastos,3,"bastos","picudas"));
                add(new Carta("Tres_Espadas", R.drawable.tres_espadas,3,"espadas","picudas"));

                add(new Carta("Cuatro_Oros", R.drawable.cuatro_oros,4, "oros","redondas"));
                add(new Carta("Cuatro_Copas", R.drawable.cuatro_copas,4,"copas","redondas"));
                add(new Carta("Cuatro_Bastos", R.drawable.cuatro_bastos,4,"bastos","picudas"));
                add(new Carta("Cuatro_Espadas", R.drawable.cuatro_espadas,4,"espadas","picudas"));

                add(new Carta("Cinco_Oros", R.drawable.cinco_oros,5, "oros","redondas"));
                add(new Carta("Cinco_Copas", R.drawable.cinco_copas,5,"copas","redondas"));
                add(new Carta("Cinco_Bastos", R.drawable.cinco_bastos,5,"bastos","picudas"));
                add(new Carta("Cinco_Espadas", R.drawable.cinco_espadas,5,"espadas","picudas"));

                add(new Carta("Seis_Oros", R.drawable.seis_oros,6, "oros","redondas"));
                add(new Carta("Seis_Copas", R.drawable.seis_copas,6,"copas","redondas"));
                add(new Carta("Seis_Bastos", R.drawable.seis_bastos,6,"bastos","picudas"));
                add(new Carta("Seis_Espadas", R.drawable.seis_espadas,6,"espadas","picudas"));

                add(new Carta("Siete_Oros", R.drawable.siete_oros,7, "oros","redondas"));
                add(new Carta("Siete_Copas", R.drawable.siete_copas,7,"copas","redondas"));
                add(new Carta("Siete_Bastos", R.drawable.siete_bastos,7,"bastos","picudas"));
                add(new Carta("Siete_Espadas", R.drawable.siete_espadas,7,"espadas","picudas"));

                add(new Carta("Ocho_Oros", R.drawable.ocho_oros,8, "oros","redondas"));
                add(new Carta("Ocho_Copas", R.drawable.ocho_copas,8,"copas","redondas"));
                add(new Carta("Ocho_Bastos", R.drawable.ocho_bastos,8,"bastos","picudas"));
                add(new Carta("Ocho_Espadas", R.drawable.ocho_espadas,8,"espadas","picudas"));

                add(new Carta("Nueve_Oros", R.drawable.nueve_oros,9, "oros","redondas"));
                add(new Carta("Nueve_Copas", R.drawable.nueve_copas,9,"copas","redondas"));
                add(new Carta("Nueve_Bastos", R.drawable.nueve_bastos,9,"bastos","picudas"));
                add(new Carta("Nueve_Espadas", R.drawable.nueve_espadas,9,"espadas","picudas"));

                add(new Carta("Sota_Oros", R.drawable.sota_oros,10, "oros","redondas"));
                add(new Carta("Sota_Copas", R.drawable.sota_copas,10,"copas","redondas"));
                add(new Carta("Sota_Bastos", R.drawable.sota_bastos,10,"bastos","picudas"));
                add(new Carta("Sota_Espadas", R.drawable.sota_espadas,10,"espadas","picudas"));

                add(new Carta("Caballo_Oros", R.drawable.caballo_oros,11, "oros","redondas"));
                add(new Carta("Caballo_Copas", R.drawable.caballo_copas,11,"copas","redondas"));
                add(new Carta("Caballo_Bastos", R.drawable.caballo_bastos,11,"bastos","picudas"));
                add(new Carta("Caballo_Espadas", R.drawable.caballo_espadas,11,"espadas","picudas"));

                add(new Carta("Rey_Oros", R.drawable.rey_oros,12, "oros","redondas"));
                add(new Carta("Rey_Copas", R.drawable.rey_copas,12,"copas","redondas"));
                add(new Carta("Rey_Bastos", R.drawable.rey_bastos,12,"bastos","picudas"));
                add(new Carta("Rey_Espadas", R.drawable.rey_espadas,12,"espadas","picudas"));


            }};
    }
    private String pruebaARealizar(int a){
        switch (a){
            case 1:
                return getString(R.string.pingo_prueba_1);
            case 2:
                return getString(R.string.pingo_prueba_2);
            case 3:
                return getString(R.string.pingo_prueba_3);
            case 4:
                return getString(R.string.pingo_prueba_4);
            case 5:
                return getString(R.string.pingo_prueba_5);
            case 6:
                return getString(R.string.pingo_prueba_6);
            case 7:
                return getString(R.string.pingo_prueba_7);
            case 8:
                return getString(R.string.pingo_prueba_8);
            case 9:
                return getString(R.string.pingo_prueba_9);
            case 10:
                return getString(R.string.pingo_prueba_10);
            case 11:
                return getString(R.string.pingo_prueba_11);
            case 12:
                return getString(R.string.pingo_prueba_12);
            default:
                return null;
        }
    }


}