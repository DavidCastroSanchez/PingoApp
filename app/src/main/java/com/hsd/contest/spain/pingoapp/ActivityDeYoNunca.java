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

public class ActivityDeYoNunca extends AppCompatActivity {

    //preparar el stack de preguntas
    private List<String> pila_preguntas;
    int k=0;
    int cantidad=204;
    int rango=204;
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
        setContentView(R.layout.activity_de_yo_nunca);

        //Mezclar el stack de las preguntas
        pila_preguntas= this.getAllPreguntas();
        //barajar las cartas
        for(int i=0;i<204;i++){
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
        LinearLayout rootView = findViewById(R.id.layoutSuperiorYoNunca);
        rootView.addView(topBannerView);

        //Interstitial ad
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");
        interstitialAd.loadAd(adParam);

        btnSiguiente= (Button)findViewById(R.id.buttonSiguietneYoNunca);
        btnComoJugar= (Button)findViewById(R.id.buttonComoJugarYoNunca);
        btnSugerir= (Button)findViewById(R.id.buttonSugerirYoNunca);

        textViewPregunta=(TextView) findViewById(R.id.textViewYoNunca);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(k!=204) {
                    textViewPregunta.setText(pila_preguntas.get(numAleatorios[k]));
                    k++;
                    if(interstitialAd != null && interstitialAd.isLoaded() && k>10){
                            interstitialAd.show();
                    }
                }else{
                    AlertDialog.Builder alertaFinJuegoYoNuncaErotico = new AlertDialog.Builder(ActivityDeYoNunca.this);
                    alertaFinJuegoYoNuncaErotico.setMessage(getString(R.string.yo_nunca_fin_de_juego_texto))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.yo_nunca_fin_de_juego_boton_si), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent=new Intent(ActivityDeYoNunca.this,ActivityDeYoNunca.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(getString(R.string.yo_nunca_fin_de_juego_boton_no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    AlertDialog titulo = alertaFinJuegoYoNuncaErotico.create();
                    titulo.setTitle(getString(R.string.yo_nunca_fin_de_juego_titulo));
                    titulo.show();
                }
            }
        });


        btnComoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertaYoNuncaComoJugar= new AlertDialog.Builder(ActivityDeYoNunca.this);
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
                Intent intent=new Intent(ActivityDeYoNunca.this,ActivityDeSugerencias.class);
                startActivity(intent);

            }
        });


    }

private List<String> getAllPreguntas(){
    return new ArrayList<String>(){{

        add(getString(R.string.yo_nunca_afirmacion_0));
        add(getString(R.string.yo_nunca_afirmacion_1));
        add(getString(R.string.yo_nunca_afirmacion_2));
        add(getString(R.string.yo_nunca_afirmacion_3));
        add(getString(R.string.yo_nunca_afirmacion_4));
        add(getString(R.string.yo_nunca_afirmacion_5));
        add(getString(R.string.yo_nunca_afirmacion_6));
        add(getString(R.string.yo_nunca_afirmacion_7));
        add(getString(R.string.yo_nunca_afirmacion_8));
        add(getString(R.string.yo_nunca_afirmacion_9));

        add(getString(R.string.yo_nunca_afirmacion_10));
        add(getString(R.string.yo_nunca_afirmacion_11));
        add(getString(R.string.yo_nunca_afirmacion_12));
        add(getString(R.string.yo_nunca_afirmacion_13));
        add(getString(R.string.yo_nunca_afirmacion_14));
        add(getString(R.string.yo_nunca_afirmacion_15));
        add(getString(R.string.yo_nunca_afirmacion_16));
        add(getString(R.string.yo_nunca_afirmacion_17));
        add(getString(R.string.yo_nunca_afirmacion_18));
        add(getString(R.string.yo_nunca_afirmacion_19));

        add(getString(R.string.yo_nunca_afirmacion_20));
        add(getString(R.string.yo_nunca_afirmacion_21));
        add(getString(R.string.yo_nunca_afirmacion_22));
        add(getString(R.string.yo_nunca_afirmacion_23));
        add(getString(R.string.yo_nunca_afirmacion_24));
        add(getString(R.string.yo_nunca_afirmacion_25));
        add(getString(R.string.yo_nunca_afirmacion_26));
        add(getString(R.string.yo_nunca_afirmacion_27));
        add(getString(R.string.yo_nunca_afirmacion_28));
        add(getString(R.string.yo_nunca_afirmacion_29));

        add(getString(R.string.yo_nunca_afirmacion_30));
        add(getString(R.string.yo_nunca_afirmacion_31));
        add(getString(R.string.yo_nunca_afirmacion_32));
        add(getString(R.string.yo_nunca_afirmacion_33));
        add(getString(R.string.yo_nunca_afirmacion_34));
        add(getString(R.string.yo_nunca_afirmacion_35));
        add(getString(R.string.yo_nunca_afirmacion_36));
        add(getString(R.string.yo_nunca_afirmacion_37));
        add(getString(R.string.yo_nunca_afirmacion_38));
        add(getString(R.string.yo_nunca_afirmacion_39));

        add(getString(R.string.yo_nunca_afirmacion_40));
        add(getString(R.string.yo_nunca_afirmacion_41));
        add(getString(R.string.yo_nunca_afirmacion_42));
        add(getString(R.string.yo_nunca_afirmacion_43));
        add(getString(R.string.yo_nunca_afirmacion_44));
        add(getString(R.string.yo_nunca_afirmacion_45));
        add(getString(R.string.yo_nunca_afirmacion_46));
        add(getString(R.string.yo_nunca_afirmacion_47));
        add(getString(R.string.yo_nunca_afirmacion_48));
        add(getString(R.string.yo_nunca_afirmacion_49));

        add(getString(R.string.yo_nunca_afirmacion_50));
        add(getString(R.string.yo_nunca_afirmacion_51));
        add(getString(R.string.yo_nunca_afirmacion_52));
        add(getString(R.string.yo_nunca_afirmacion_53));
        add(getString(R.string.yo_nunca_afirmacion_54));
        add(getString(R.string.yo_nunca_afirmacion_55));
        add(getString(R.string.yo_nunca_afirmacion_56));
        add(getString(R.string.yo_nunca_afirmacion_57));
        add(getString(R.string.yo_nunca_afirmacion_58));
        add(getString(R.string.yo_nunca_afirmacion_59));

        add(getString(R.string.yo_nunca_afirmacion_60));
        add(getString(R.string.yo_nunca_afirmacion_61));
        add(getString(R.string.yo_nunca_afirmacion_62));
        add(getString(R.string.yo_nunca_afirmacion_63));
        add(getString(R.string.yo_nunca_afirmacion_64));
        add(getString(R.string.yo_nunca_afirmacion_65));
        add(getString(R.string.yo_nunca_afirmacion_66));
        add(getString(R.string.yo_nunca_afirmacion_67));
        add(getString(R.string.yo_nunca_afirmacion_68));
        add(getString(R.string.yo_nunca_afirmacion_69));

        add(getString(R.string.yo_nunca_afirmacion_70));
        add(getString(R.string.yo_nunca_afirmacion_71));
        add(getString(R.string.yo_nunca_afirmacion_72));
        add(getString(R.string.yo_nunca_afirmacion_73));
        add(getString(R.string.yo_nunca_afirmacion_74));
        add(getString(R.string.yo_nunca_afirmacion_75));
        add(getString(R.string.yo_nunca_afirmacion_76));
        add(getString(R.string.yo_nunca_afirmacion_77));
        add(getString(R.string.yo_nunca_afirmacion_78));
        add(getString(R.string.yo_nunca_afirmacion_79));

        add(getString(R.string.yo_nunca_afirmacion_80));
        add(getString(R.string.yo_nunca_afirmacion_81));
        add(getString(R.string.yo_nunca_afirmacion_82));
        add(getString(R.string.yo_nunca_afirmacion_83));
        add(getString(R.string.yo_nunca_afirmacion_84));
        add(getString(R.string.yo_nunca_afirmacion_85));
        add(getString(R.string.yo_nunca_afirmacion_86));
        add(getString(R.string.yo_nunca_afirmacion_87));
        add(getString(R.string.yo_nunca_afirmacion_88));
        add(getString(R.string.yo_nunca_afirmacion_89));

        add(getString(R.string.yo_nunca_afirmacion_90));
        add(getString(R.string.yo_nunca_afirmacion_91));
        add(getString(R.string.yo_nunca_afirmacion_92));
        add(getString(R.string.yo_nunca_afirmacion_93));
        add(getString(R.string.yo_nunca_afirmacion_94));
        add(getString(R.string.yo_nunca_afirmacion_95));
        add(getString(R.string.yo_nunca_afirmacion_96));
        add(getString(R.string.yo_nunca_afirmacion_97));
        add(getString(R.string.yo_nunca_afirmacion_98));
        add(getString(R.string.yo_nunca_afirmacion_99));

        add(getString(R.string.yo_nunca_afirmacion_100));
        add(getString(R.string.yo_nunca_afirmacion_101));
        add(getString(R.string.yo_nunca_afirmacion_102));
        add(getString(R.string.yo_nunca_afirmacion_103));
        add(getString(R.string.yo_nunca_afirmacion_104));
        add(getString(R.string.yo_nunca_afirmacion_105));
        add(getString(R.string.yo_nunca_afirmacion_106));
        add(getString(R.string.yo_nunca_afirmacion_107));
        add(getString(R.string.yo_nunca_afirmacion_108));
        add(getString(R.string.yo_nunca_afirmacion_109));

        add(getString(R.string.yo_nunca_afirmacion_100));
        add(getString(R.string.yo_nunca_afirmacion_101));
        add(getString(R.string.yo_nunca_afirmacion_102));
        add(getString(R.string.yo_nunca_afirmacion_103));
        add(getString(R.string.yo_nunca_afirmacion_104));
        add(getString(R.string.yo_nunca_afirmacion_105));
        add(getString(R.string.yo_nunca_afirmacion_106));
        add(getString(R.string.yo_nunca_afirmacion_107));
        add(getString(R.string.yo_nunca_afirmacion_108));
        add(getString(R.string.yo_nunca_afirmacion_109));

        add(getString(R.string.yo_nunca_afirmacion_110));
        add(getString(R.string.yo_nunca_afirmacion_111));
        add(getString(R.string.yo_nunca_afirmacion_112));
        add(getString(R.string.yo_nunca_afirmacion_113));
        add(getString(R.string.yo_nunca_afirmacion_114));
        add(getString(R.string.yo_nunca_afirmacion_115));
        add(getString(R.string.yo_nunca_afirmacion_116));
        add(getString(R.string.yo_nunca_afirmacion_117));
        add(getString(R.string.yo_nunca_afirmacion_118));
        add(getString(R.string.yo_nunca_afirmacion_119));

        add(getString(R.string.yo_nunca_afirmacion_120));
        add(getString(R.string.yo_nunca_afirmacion_121));
        add(getString(R.string.yo_nunca_afirmacion_122));
        add(getString(R.string.yo_nunca_afirmacion_123));
        add(getString(R.string.yo_nunca_afirmacion_124));
        add(getString(R.string.yo_nunca_afirmacion_125));
        add(getString(R.string.yo_nunca_afirmacion_126));
        add(getString(R.string.yo_nunca_afirmacion_127));
        add(getString(R.string.yo_nunca_afirmacion_128));
        add(getString(R.string.yo_nunca_afirmacion_129));

        add(getString(R.string.yo_nunca_afirmacion_130));
        add(getString(R.string.yo_nunca_afirmacion_131));
        add(getString(R.string.yo_nunca_afirmacion_132));
        add(getString(R.string.yo_nunca_afirmacion_133));
        add(getString(R.string.yo_nunca_afirmacion_134));
        add(getString(R.string.yo_nunca_afirmacion_135));
        add(getString(R.string.yo_nunca_afirmacion_136));
        add(getString(R.string.yo_nunca_afirmacion_137));
        add(getString(R.string.yo_nunca_afirmacion_138));
        add(getString(R.string.yo_nunca_afirmacion_139));

        add(getString(R.string.yo_nunca_afirmacion_140));
        add(getString(R.string.yo_nunca_afirmacion_141));
        add(getString(R.string.yo_nunca_afirmacion_142));
        add(getString(R.string.yo_nunca_afirmacion_143));
        add(getString(R.string.yo_nunca_afirmacion_144));
        add(getString(R.string.yo_nunca_afirmacion_145));
        add(getString(R.string.yo_nunca_afirmacion_146));
        add(getString(R.string.yo_nunca_afirmacion_147));
        add(getString(R.string.yo_nunca_afirmacion_148));
        add(getString(R.string.yo_nunca_afirmacion_149));

        add(getString(R.string.yo_nunca_afirmacion_150));
        add(getString(R.string.yo_nunca_afirmacion_151));
        add(getString(R.string.yo_nunca_afirmacion_152));
        add(getString(R.string.yo_nunca_afirmacion_153));
        add(getString(R.string.yo_nunca_afirmacion_154));
        add(getString(R.string.yo_nunca_afirmacion_155));
        add(getString(R.string.yo_nunca_afirmacion_156));
        add(getString(R.string.yo_nunca_afirmacion_157));
        add(getString(R.string.yo_nunca_afirmacion_158));
        add(getString(R.string.yo_nunca_afirmacion_159));

        add(getString(R.string.yo_nunca_afirmacion_160));
        add(getString(R.string.yo_nunca_afirmacion_161));
        add(getString(R.string.yo_nunca_afirmacion_162));
        add(getString(R.string.yo_nunca_afirmacion_163));
        add(getString(R.string.yo_nunca_afirmacion_164));
        add(getString(R.string.yo_nunca_afirmacion_165));
        add(getString(R.string.yo_nunca_afirmacion_166));
        add(getString(R.string.yo_nunca_afirmacion_167));
        add(getString(R.string.yo_nunca_afirmacion_168));
        add(getString(R.string.yo_nunca_afirmacion_169));

        add(getString(R.string.yo_nunca_afirmacion_170));
        add(getString(R.string.yo_nunca_afirmacion_171));
        add(getString(R.string.yo_nunca_afirmacion_172));
        add(getString(R.string.yo_nunca_afirmacion_173));
        add(getString(R.string.yo_nunca_afirmacion_174));
        add(getString(R.string.yo_nunca_afirmacion_175));
        add(getString(R.string.yo_nunca_afirmacion_176));
        add(getString(R.string.yo_nunca_afirmacion_177));
        add(getString(R.string.yo_nunca_afirmacion_178));
        add(getString(R.string.yo_nunca_afirmacion_179));

        add(getString(R.string.yo_nunca_afirmacion_180));
        add(getString(R.string.yo_nunca_afirmacion_181));
        add(getString(R.string.yo_nunca_afirmacion_182));
        add(getString(R.string.yo_nunca_afirmacion_183));
        add(getString(R.string.yo_nunca_afirmacion_184));
        add(getString(R.string.yo_nunca_afirmacion_185));
        add(getString(R.string.yo_nunca_afirmacion_186));
        add(getString(R.string.yo_nunca_afirmacion_187));
        add(getString(R.string.yo_nunca_afirmacion_188));
        add(getString(R.string.yo_nunca_afirmacion_189));

        add(getString(R.string.yo_nunca_afirmacion_190));
        add(getString(R.string.yo_nunca_afirmacion_191));
        add(getString(R.string.yo_nunca_afirmacion_192));
        add(getString(R.string.yo_nunca_afirmacion_193));
        add(getString(R.string.yo_nunca_afirmacion_194));
        add(getString(R.string.yo_nunca_afirmacion_195));
        add(getString(R.string.yo_nunca_afirmacion_196));
        add(getString(R.string.yo_nunca_afirmacion_197));
        add(getString(R.string.yo_nunca_afirmacion_198));
        add(getString(R.string.yo_nunca_afirmacion_199));

        add(getString(R.string.yo_nunca_afirmacion_200));
        add(getString(R.string.yo_nunca_afirmacion_201));
        add(getString(R.string.yo_nunca_afirmacion_202));
        add(getString(R.string.yo_nunca_afirmacion_203));



    }};
}
}
