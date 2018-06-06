package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.david.myapplication.R;

public class OpcionesMenu extends AppCompatActivity {
    ProgressBar pb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_opciones_menu);

        pb1 = (ProgressBar) findViewById(R.id.loading);

    }

    public void runJuego (View view){
        Intent intentOj = new Intent(OpcionesMenu.this, Juego.class);
        startActivity(intentOj);
    }
    public void listaObjetos (View view){

    }
    public void logOut (View view){
        Intent intentOj = new Intent(OpcionesMenu.this, MainActivity.class);
        startActivity(intentOj);
    }
}
