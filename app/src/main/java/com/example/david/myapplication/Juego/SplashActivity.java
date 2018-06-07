package com.example.david.myapplication.Juego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.david.myapplication.R;

public class SplashActivity extends AppCompatActivity{

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 1000; // 3 segundos

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.splash2);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                //Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                //Intent intent = new Intent(SplashActivity.this, Juego.class);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
