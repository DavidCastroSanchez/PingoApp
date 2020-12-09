package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityDeAutopista extends AppCompatActivity {

    //preparar el mazo de cartas
    private List<Carta> mazo_cartas;
    int k=0;
    int cantidad=48;
    int rango=48;
    final int numAleatorios[]=new int[cantidad];

    //Guardar el valor de cada una de las 8 cartas(solo seran 6 ya que hay dos que son peaje)

    private int valorCarta1=0;
    private int valorCarta2=0;
    private int valorCarta4=0;
    private int valorCarta5=0;
    private int valorCarta7=0;
    private int valorCarta8=0;

    //Layout Declaracion de elementos
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private ImageView imageViewCarta1;
    private ImageView imageViewCarta2;
    private ImageView imageViewCarta3;
    private ImageView imageViewCarta4;
    private ImageView imageViewCarta5;
    private ImageView imageViewCarta6;
    private ImageView imageViewCarta7;
    private ImageView imageViewCarta8;

    private ImageView imageViewFlecha1;
    private ImageView imageViewFlecha2;
    private ImageView imageViewFlecha3;
    private ImageView imageViewFlecha4;
    private ImageView imageViewFlecha5;
    private ImageView imageViewFlecha6;
    private ImageView imageViewFlecha7;
    private ImageView imageViewFlecha8;

    private ImageView imageViewMazo;

    private TextView textViewPregunta;
    private TextView textViewContadorCartas;

    //Booleanos para gestionar las pulsaciones

    private boolean btn_1_pulsado=false;
    private boolean btn_2_pulsado =false;


    //Booleano para gestionar las cartas de peaje
    private boolean peajePorError = false;

    //Contador para gestionar las fases del juego
    private int contador=1;
    //Temporizador para finalizar el juego
    private final int tiempo =3000;

    private boolean adflag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_autopista);

        //declarar los views de los elementos y asociarlos a los elementos
        btn1= (Button)findViewById(R.id.button1);
        btn2= (Button)findViewById(R.id.button2);
        btn3= (Button)findViewById(R.id.button3);
        btn4= (Button)findViewById(R.id.button4);

        imageViewCarta1=(ImageView) findViewById(R.id.imageViewCarta1);
        imageViewCarta2=(ImageView) findViewById(R.id.imageViewCarta2);
        imageViewCarta3=(ImageView) findViewById(R.id.imageViewCarta3);
        imageViewCarta4=(ImageView) findViewById(R.id.imageViewCarta4);
        imageViewCarta5=(ImageView) findViewById(R.id.imageViewCarta5);
        imageViewCarta6=(ImageView) findViewById(R.id.imageViewCarta6);
        imageViewCarta7=(ImageView) findViewById(R.id.imageViewCarta7);
        imageViewCarta8=(ImageView) findViewById(R.id.imageViewCarta8);

        imageViewFlecha1=(ImageView) findViewById(R.id.imageViewFlecha1);
        imageViewFlecha2=(ImageView) findViewById(R.id.imageViewFlecha2);
        imageViewFlecha3=(ImageView) findViewById(R.id.imageViewFlecha3);
        imageViewFlecha4=(ImageView) findViewById(R.id.imageViewFlecha4);
        imageViewFlecha5=(ImageView) findViewById(R.id.imageViewFlecha5);
        imageViewFlecha6=(ImageView) findViewById(R.id.imageViewFlecha6);
        imageViewFlecha7=(ImageView) findViewById(R.id.imageViewFlecha7);
        imageViewFlecha8=(ImageView) findViewById(R.id.imageViewFlecha8);

        imageViewMazo=(ImageView) findViewById(R.id.imageViewMazo);

        textViewPregunta=(TextView) findViewById(R.id.textViewPregunta);
        textViewContadorCartas=(TextView) findViewById(R.id.textViewContadorCartas);

        //adds
        HwAds.init(this);
        AdParam adParam=new AdParam.Builder().build();
        //Interstitial ad
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");
        interstitialAd.loadAd(adParam);
        //enseñar anuncio



        //preparar el mazo
        mazo_cartas= this.getAllCartas();
            //barajar las cartas
            for(int i=0;i<48;i++){
            numAleatorios[i]=(int) (Math.random()*rango);
            for(int j=0;j<i;j++){
                if(numAleatorios[i]==numAleatorios[j]) i--;
            }
        }


        //inicializar el juego
        init();

         //listener de los botones
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(interstitialAd != null && interstitialAd.isLoaded() && adflag){
                    interstitialAd.show();
                    adflag=false;
                }
                btn_1_pulsado=true;
                juego(valorCarta1,valorCarta2,valorCarta4,valorCarta5,valorCarta7,valorCarta8);
                ;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_2_pulsado=true;
                juego(valorCarta1,valorCarta2,valorCarta4,valorCarta5,valorCarta7,valorCarta8);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                AlertDialog.Builder comoJugar = new AlertDialog.Builder(ActivityDeAutopista.this);
                comoJugar.setMessage(getString(R.string.autopista_como_jugar_texto))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.autopista_como_jugar_boton_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = comoJugar.create();
                titulo.setTitle(getString(R.string.autopista_como_jugar_titulo));
                titulo.show();
            }
        });


    }

    //funciones

    //Genera la baraja de cartas
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
    //Estado inicial del juego
    private void init() {

        //inicializar los valores de las cartas

        imageViewCarta1.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta1=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;
        imageViewCarta2.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta2=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;
        imageViewCarta4.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta4=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;
        imageViewCarta5.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta5=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;
        imageViewCarta7.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta7=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;
        imageViewCarta8.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        valorCarta8=mazo_cartas.get(numAleatorios[k]).getValor();
        k++;


        //Inicializar el resto de elementos
        textViewContadorCartas.setText(k+"/48");


        btn1.setVisibility(btn1.VISIBLE);
        btn2.setVisibility(btn2.VISIBLE);
        btn3.setVisibility(btn3.INVISIBLE);
        btn4.setVisibility(btn4.VISIBLE);


        imageViewFlecha1.setVisibility(imageViewFlecha1.VISIBLE);
        imageViewFlecha2.setVisibility(imageViewFlecha2.INVISIBLE);
        imageViewFlecha3.setVisibility(imageViewFlecha3.INVISIBLE);
        imageViewFlecha4.setVisibility(imageViewFlecha4.INVISIBLE);
        imageViewFlecha5.setVisibility(imageViewFlecha5.INVISIBLE);
        imageViewFlecha6.setVisibility(imageViewFlecha6.INVISIBLE);
        imageViewFlecha7.setVisibility(imageViewFlecha7.INVISIBLE);
        imageViewFlecha8.setVisibility(imageViewFlecha8.INVISIBLE);

    }

    //Logica del juego
    private void juego(int vc1,int vc2,int vc4,int vc5,int vc7,int vc8){
        if(k!=47){
        switch(contador){
            case 1:
                    if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta1 && btn_1_pulsado||
                       mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta1 && btn_2_pulsado){
                        correctoCaso1();
                    }else{
                        incorrectoCaso1();
                    }
                break;
            case 2:
                if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta2 && btn_1_pulsado||
                   mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta2 && btn_2_pulsado){
                    correctoCaso2();
                }else{
                    incorrectoCaso2();
                }
                break;
            case 3:

               if(btn_1_pulsado){
                   //este booleano gestionara si el peaje se pasa de izq a derecha (es decir si se ha acertado)
                   //o si se pasa de dch a izq porque se ha fallado en cuyo caso la variable peajePorError sera true
                   if(peajePorError){
                         peajeHaciaAtras();
                    }else{
                        peajeHaciaDelante();
                    }
               }
                break;
            case 4:
                if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta4 && btn_1_pulsado||
                   mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta4 && btn_2_pulsado){
                    correctoCaso4();
                }else{
                    incorrectoCaso4();
                }
                break;
            case 5:
                if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta5 && btn_1_pulsado||
                        mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta5 && btn_2_pulsado){
                    correctoCaso5();
                }else{
                    incorrectoCaso5();
                }
                break;
            case 6:

                if(btn_1_pulsado){
                    //este booleano gestionara si el peaje se pasa de izq a derecha (es decir si se ha acertado)
                    //o si se pasa de dch a izq porque se ha fallado en cuyo caso la variable peajePorError sera true
                    if(peajePorError){
                        peajeHaciaAtras2();
                    }else{
                        peajeHaciaDelante2();
                    }
                }
                break;
            case 7:
                if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta7 && btn_1_pulsado||
                        mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta7 && btn_2_pulsado){
                    correctoCaso7();
                }else{
                    incorrectoCaso7();
                }
                break;
            case 8:
                if(mazo_cartas.get((int) numAleatorios[k]).getValor()>valorCarta8 && btn_1_pulsado||
                        mazo_cartas.get((int) numAleatorios[k]).getValor()<valorCarta8 && btn_2_pulsado){
                    correctoCaso8();
                }else{
                    incorrectoCaso8();
                }
                break;
            default:
                break;
        }
        }else{
            textViewContadorCartas.setText("48/48");
            AlertDialog.Builder alertaFinJuegoIncompleto = new AlertDialog.Builder(ActivityDeAutopista.this);
            alertaFinJuegoIncompleto.setMessage(getString(R.string.autopista_fin_de_juego_no_terminado_texto))
                    .setCancelable(false)
            .setPositiveButton(getString(R.string.autopista_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    Intent intent=new Intent(ActivityDeAutopista.this,ActivityDeAutopista.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton(getString(R.string.autopista_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog titulo = alertaFinJuegoIncompleto.create();
            titulo.setTitle(getString(R.string.autopista_fin_de_juego_titulo));
            titulo.show();
        }
    }

    //casos de juego
    //1
    private void correctoCaso1(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //avanzamos la flecha
        imageViewFlecha1.setVisibility(View.INVISIBLE);
        imageViewFlecha2.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //avanzamos la carta del mazo
        k++;
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;

    }
    private void incorrectoCaso1(){
        //al ser el primer caso no hay modificacion de caso(Me mantengo en el primero) mas alla del mazo

        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //FALLO
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_bebes), Toast.LENGTH_SHORT).show();
        //Ni avanzamos ni retrocedemos la flecha
        //ni avanzamos ni retrocedemos el contador
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }
    //2
    private void correctoCaso2(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_peaje));
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //Corroboramos el acierto
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_peaje), Toast.LENGTH_SHORT).show();
        //avanzamos la flecha
        imageViewFlecha2.setVisibility(View.INVISIBLE);
        imageViewFlecha3.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //avanzamos la carta del mazo
        k++;
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        //Seteamos los botones para el peaje
        btn1.setText(getString(R.string.autopista_pagado));
        btn2.setVisibility(View.INVISIBLE);

    }
    private void incorrectoCaso2(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //FALLO
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_bebes), Toast.LENGTH_SHORT).show();
        //retrocedemos la flecha
        imageViewFlecha2.setVisibility(View.INVISIBLE);
        imageViewFlecha1.setVisibility(View.VISIBLE);
        //retrocedemos el contador
        contador--;
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }
    //3
    private void peajeHaciaDelante(){

        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_principal));
        //avanzamos la flecha
        imageViewFlecha3.setVisibility(View.INVISIBLE);
        imageViewFlecha4.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //NO avanzamos la carta del mazo
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        //restablecemos los botones
        btn1.setText(getString(R.string.autopista_boton_mayor));
        btn2.setVisibility(View.VISIBLE);
    }
    private void peajeHaciaAtras(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_principal));
        //avanzamos la flecha
        imageViewFlecha3.setVisibility(View.INVISIBLE);
        imageViewFlecha2.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador--;
        //NO avanzamos la carta del mazo
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        peajePorError=false;
        //Restauramos los botones
        btn1.setText(getString(R.string.autopista_boton_mayor));
        btn2.setVisibility(View.VISIBLE);
    }
    //4
    private void correctoCaso4(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //Corroboramos el acierto

        //avanzamos la flecha
        imageViewFlecha4.setVisibility(View.INVISIBLE);
        imageViewFlecha5.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //avanzamos la carta del mazo
        k++;
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }
    private void incorrectoCaso4(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_peaje));
        //Seteamos los botones para el peaje
        btn1.setText(getString(R.string.autopista_pagado));
        btn2.setVisibility(View.INVISIBLE);
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //FALLO
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_peaje), Toast.LENGTH_SHORT).show();
        //retrocedemos la flecha
        imageViewFlecha4.setVisibility(View.INVISIBLE);
        imageViewFlecha3.setVisibility(View.VISIBLE);
        //retrocedemos el contador
        contador--;
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        peajePorError=true;
    }
    //5
    //igual que en el caso 2 (hay que modificar las flechas)
    private void correctoCaso5(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_peaje));
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //Toast
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_peaje), Toast.LENGTH_SHORT).show();
        //avanzamos la flecha
        imageViewFlecha5.setVisibility(View.INVISIBLE);
        imageViewFlecha6.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //avanzamos la carta del mazo
        k++;
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        //Seteamos los botones para el peaje
        btn1.setText(getString(R.string.autopista_pagado));
        btn2.setVisibility(View.INVISIBLE);

    }
    private void incorrectoCaso5(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //FALLO
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_bebes), Toast.LENGTH_SHORT).show();
        //retrocedemos la flecha
        imageViewFlecha5.setVisibility(View.INVISIBLE);
        imageViewFlecha4.setVisibility(View.VISIBLE);
        //retrocedemos el contador
        contador--;
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }
    //6
    //igual que en los peajes (hay que modificar las flechas)
    private void peajeHaciaDelante2(){

        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_principal));
        //avanzamos la flecha
        imageViewFlecha6.setVisibility(View.INVISIBLE);
        imageViewFlecha7.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //NO avanzamos la carta del mazo
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        //restablecemos los botones
        btn1.setText(R.string.autopista_boton_mayor);
        btn2.setVisibility(View.VISIBLE);
    }
    private void peajeHaciaAtras2(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_principal));
        //avanzamos la flecha
        imageViewFlecha6.setVisibility(View.INVISIBLE);
        imageViewFlecha5.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador--;
        //NO avanzamos la carta del mazo
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        peajePorError=false;
        //Restauramos los botones
        btn1.setText(getString(R.string.autopista_boton_mayor));
        btn2.setVisibility(View.VISIBLE);
    }
    //7
    //igual que en el caso 4 (hay que modificar las flechas)
    private void correctoCaso7(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //avanzamos la flecha
        imageViewFlecha7.setVisibility(View.INVISIBLE);
        imageViewFlecha8.setVisibility(View.VISIBLE);
        //avanzamos el contador
        contador++;
        //avanzamos la carta del mazo
        k++;
        //Actualziamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }
    private void incorrectoCaso7(){
        //setear el texto superior de los botones
        textViewPregunta.setText(getString(R.string.autopista_textView_peaje));
        //Toast
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_peaje), Toast.LENGTH_SHORT).show();
        //Boton para el peaje
        //Seteamos los botones para el peaje
        btn1.setText(getString(R.string.autopista_pagado));
        btn2.setVisibility(View.INVISIBLE);
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //retrocedemos la flecha
        imageViewFlecha7.setVisibility(View.INVISIBLE);
        imageViewFlecha6.setVisibility(View.VISIBLE);
        //retrocedemos el contador
        contador--;
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
        peajePorError=true;
    }
    //8
    //hacia atras igual que en el caso 4
    private void correctoCaso8(){

        AlertDialog.Builder alertaFinJuegoCompleto = new AlertDialog.Builder(ActivityDeAutopista.this);
        alertaFinJuegoCompleto.setMessage(getString(R.string.autopista_fin_de_juego_texto))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.autopista_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent=new Intent(ActivityDeAutopista.this,ActivityDeAutopista.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(getString(R.string.autopista_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog titulo = alertaFinJuegoCompleto.create();
        titulo.setTitle(getString(R.string.autopista_fin_de_juego_titulo));
        titulo.show();

    }
    private void incorrectoCaso8(){
        //Enseñamos la carta
        imageViewMazo.setImageResource((int) mazo_cartas.get(numAleatorios[k]).getPoster());
        //FALLO
        Toast.makeText(getApplicationContext(),getString(R.string.autopista_toast_bebes), Toast.LENGTH_SHORT).show();
        //retrocedemos la flecha
        imageViewFlecha8.setVisibility(View.INVISIBLE);
        imageViewFlecha7.setVisibility(View.VISIBLE);
        //retrocedemos el contador
        contador--;
        //Avanzamos la carta del mazo
        k++;
        //Actualizamos el contador de cartas del mazo
        textViewContadorCartas.setText(k+"/48");
        //despulsamos los dos botones por si acaso
        btn_1_pulsado=false;
        btn_2_pulsado=false;
    }

}