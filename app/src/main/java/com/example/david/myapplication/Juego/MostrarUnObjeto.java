package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MostrarUnObjeto extends AppCompatActivity{
    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    private TrackAPI trackServices;
    ProgressBar pb1;
    TextView txtuser;
    ImageView foto;
    private String nombre;
    String tag = "Events";
    int idObjeto;
    private Call<Objeto> callObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.objeto_entero);

        Bundle intentdata = getIntent().getExtras();
        idObjeto = intentdata.getInt("id");

        pb1 = (ProgressBar) findViewById(R.id.loading);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);
        getObjeto();
    }

    public void getObjeto (){
        pb1.setVisibility(ProgressBar.VISIBLE);
        callObjeto = trackServices.consultarObjeto(idObjeto);
        callObjeto.enqueue(new Callback<Objeto>() {
            @Override
            public void onResponse(Call<Objeto> call, Response<Objeto> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {

                    Log.d("onResponse", "lista ha llegado");
                    Objeto obj = response.body();
                    Log.d("onResponse", "Obj:"+obj.getId()+"\n"+obj.getNombre()+"\n"+obj.getTipo()+"\n"+obj.getDescripcion()+"\n"+obj.getValor());
                    TextView et = (TextView)findViewById(R.id.nombre_obj);
                    TextView et2 = (TextView)findViewById(R.id.tipo_obj);
                    TextView et3 = (TextView)findViewById(R.id.text_valor);
                    TextView et4 = (TextView)findViewById(R.id.text_coste);
                    TextView et5 = (TextView)findViewById(R.id.text_descrip);
                    foto = (ImageView)findViewById(R.id.foto_obj);
                    et.setText(obj.getNombre());
                    et2.setText(obj.getTipo());
                    String valor = Integer.toString(obj.getValor());
                    String coste = Integer.toString(obj.getCoste());
                    et3.setText(valor);
                    et4.setText(coste);
                    et5.setText(obj.getDescripcion());
                    if(idObjeto==1) Picasso.with(MostrarUnObjeto.this).load(R.drawable.espada).into(foto);
                    if(idObjeto==2) Picasso.with(MostrarUnObjeto.this).load(R.drawable.escudo).into(foto);
                    if(idObjeto==3) Picasso.with(MostrarUnObjeto.this).load(R.drawable.armadura).into(foto);
                    if(idObjeto==4) Picasso.with(MostrarUnObjeto.this).load(R.drawable.pocion).into(foto);
                    //al final de la tasca
                    pb1.setVisibility(ProgressBar.INVISIBLE);

                }
                else {
                    //al final de la tasca
                    pb1.setVisibility(ProgressBar.INVISIBLE);
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (MostrarUnObjeto.this,"Error de connexión",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Objeto> call, Throwable t) {
                pb1.setVisibility(ProgressBar.INVISIBLE);
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
    }

    @Override
    protected void onStart (){
        super.onStart();
        Log.d(tag,"Event a onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        //pausar();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (calltrack != null) {
//            calltrack.cancel();
//        }
//        if (callstring != null) {
//            callstring.cancel();
//        }

    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        Log.d(tag,"Event a onDestroy");
    }
}
