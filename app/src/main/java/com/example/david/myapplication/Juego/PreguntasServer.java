package com.example.david.myapplication.Juego;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.david.myapplication.Clases.Login;
import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.Clases.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PreguntasServer {
    private Call<Usuario> calluser;
    private Call<Boolean> callLog;
    private Call<Objeto> callobject;
    private Context context;
    private int vida=0;
    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    //public static final String BASE_URL ="http://localhost:8080/myapp/";
    //public static final String BASE_URL ="http://192.168.42.214:8080/myapp/";
    private TrackAPI trackServices;

    public PreguntasServer(Context context) {
        this.context=context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);
    }

    public int getVida (String nombreJugador){
        calluser = trackServices.consultarUsuario(nombreJugador);
        calluser.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                int statusCode = response.code();
                Usuario resp = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText (context,"vida obtenida",Toast.LENGTH_LONG).show();
                    Log.d("vida", "code:" + Integer.toString(statusCode)+ "vida:" + resp.getVida());
                    vida =  resp.getVida();
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (context,"Usuario/password erroneos",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
        return vida;
    }
    public void postObjeto(String nombreJugador){

    }

    public void getPosYVida (String nombreJugador){

    }






}
