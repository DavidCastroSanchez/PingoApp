package com.hsd.contest.spain.pingoapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.hsd.contest.spain.pingoapp.R.*;

public class ActivityDeSugerencias extends AppCompatActivity {

    String correo,contraseña;
    String contenido_enviado_al_usuario;
    EditText et_asunto, et_direccion, et_correo;
    Button btn_enviar;
    javax.mail.Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_de_sugerencias);

        et_correo=(EditText)findViewById(id.edit_text_correo);
        et_asunto=(EditText)findViewById(id.edit_text_asunto);
        et_direccion=(EditText)findViewById(id.edit_text_direccion);
        btn_enviar=(Button)findViewById(id.boton_enviar_mail);

        correo="pingo.aplicacion@gmail.com";
        contraseña="PingoApp2020";


        btn_enviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties props =new Properties();
                props.put("mail.smtp.host","smtp.googlemail.com");
                props.put("mail.smtp.socketFactory.port","465");
                props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.port","465");

                try {

                    session= Session.getDefaultInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contraseña);
                        }
                    });
                    if(session!=null){
                        contenido_enviado_al_usuario= getText(string.sugerencia_mensaje_enviado_parte_1)+et_correo.getText().toString()+getText(string.sugerencia_mensaje_enviado_parte_2);
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject(et_asunto.getText().toString());
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(et_direccion.getText().toString()));
                        message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("pingo.aplicacion@gmail.com"));
                        message.setContent(contenido_enviado_al_usuario,"text/html; charset=utf-8");

                        Transport.send(message);
                        Toast.makeText(getApplicationContext(),getText(string.sugerencia_toast_mensaje_enviado), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),getText(string.sugerencia_toast_mensaje_error), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}