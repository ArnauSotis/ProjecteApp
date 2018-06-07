package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ListaObjetos extends AppCompatActivity {
    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    private TrackAPI trackServices;
    ProgressBar pb1;
    TextView txtuser;
    private String nombre;
    String tag = "Events";
    private Call<List<Objeto>> callListaObjetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lista_objetosv2);

        Bundle intentdata = getIntent().getExtras();
        nombre = intentdata.getString("name");
        txtuser = (TextView) findViewById(R.id.nombre_usuario);
        txtuser.setText(nombre);

        pb1 = (ProgressBar) findViewById(R.id.loading);
        pb1.setVisibility(ProgressBar.INVISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);
        listarObjetos();
    }

    public void listarObjetos (){
            pb1.setVisibility(ProgressBar.VISIBLE);
            callListaObjetos = trackServices.listaObjetosUser(nombre);
            callListaObjetos.enqueue(new Callback<List<Objeto>>() {
                @Override
                public void onResponse(Call<List<Objeto>> call, Response<List<Objeto>> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()) {

                        Log.d("onResponse", "lista ha llegado");
                        final List<Objeto> listaObjetos = response.body();
                        ListView lv = (ListView) findViewById(R.id.lista);
                        ArrayAdapterListaObjetos uaa = new ArrayAdapterListaObjetos(getApplicationContext(), listaObjetos);
                        lv.setAdapter(uaa);
                        lv.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Object listItem = listaLibros.getItemAtPosition(position);
                                Intent i = new Intent(getApplicationContext(), MostrarUnObjeto.class);
                                i.putExtra("id", listaObjetos.get(position).getId());
                                startActivity(i);
                            }
                        });
                        //al final de la tasca
                        pb1.setVisibility(ProgressBar.INVISIBLE);
                    }
                    else {
                        //al final de la tasca
                        pb1.setVisibility(ProgressBar.INVISIBLE);
                        Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                        Toast.makeText (ListaObjetos.this,"Error de connexión",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<List<Objeto>> call, Throwable t) {
                    pb1.setVisibility(ProgressBar.INVISIBLE);
                    // log error here since request failed
                    Log.d("Request: ", "error loading API" + t.toString());
                }
            });

        }
}
