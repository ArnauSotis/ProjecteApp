package com.example.david.myapplication.Juego;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.david.myapplication.Clases.Login;
import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.Clases.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PreguntasServer {
    private Call<Usuario> calluser;
    private Call<Objeto> callobject;
    private Call<Boolean> enviarObjeto;
    private Call<Usuario> callPosYMapa;
    private Call<Boolean> postPosYMapa;

    private Context context;
    private int vida=0;
    private Usuario user = new Usuario();
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

    public void postObjeto(String nombreJugador, int idObject){
        List<String> datos = new ArrayList<>();
        datos.add(nombreJugador);
        String id = Integer.toString(idObject);
        datos.add(id);
        enviarObjeto = trackServices.addObjectAUserDAO(datos);
        enviarObjeto.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Toast.makeText (context,"Objeto almacenado.",Toast.LENGTH_LONG).show();
                    Log.d("add objeto", "code:" + Integer.toString(statusCode));
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (context,"Ya tienes este objeto.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
    }



    public Usuario getPosYVida (String nombreJugador){
//        private Call<Usuario> callPosYMapa;
        callPosYMapa = trackServices.getPosYmapa(nombreJugador);
        callPosYMapa.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                int statusCode = response.code();
                user = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText (context,"datos de vida y mapa recibidos",Toast.LENGTH_LONG).show();
                    Log.d("vida", "code:" + Integer.toString(statusCode));
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (context,"error al recibir la vida y mapa",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
        return user;
    }
    public void postPosMapaVida (String nombreJugador, int vida, int posx, int posy, int mapa){
        //        private Call<Boolean> postPosYMapa;
        Usuario u = new Usuario(nombreJugador, vida, posx, posy, mapa);
        postPosYMapa = trackServices.postPosYmapa(u);
        postPosYMapa.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Toast.makeText (context,"datos de vida y mapa colocados",Toast.LENGTH_LONG).show();
                    Log.d("vida", "code:" + Integer.toString(statusCode));
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (context,"error al poner la vida y mapa",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
    }

    public void save(){
        Toast.makeText (context,"Game saved.",Toast.LENGTH_LONG).show();
    }






}
