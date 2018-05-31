package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.david.myapplication.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Menu extends AppCompatActivity {
    private String nombre;
    String tag = "Events";
    ProgressBar pb1;
    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    //public static final String BASE_URL ="http://localhost:8080/myapp/";
    //public static final String BASE_URL ="http://192.168.42.214:8080/myapp/";
    private TrackAPI trackServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle intentdata = getIntent().getExtras();
        this.nombre = intentdata.getString("name");

        //pb1 = (ProgressBar) findViewById(R.id.loading);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);

        Button buto2 = (Button) findViewById(R.id.button_start);
        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOj = new Intent(getApplicationContext(), Juego.class);
                startActivity(intentOj);
            }
        });
    }

    public void startGame (View view){
        Intent intentOj = new Intent(Menu.this, Juego.class);
        startActivity(intentOj);
    }
}
