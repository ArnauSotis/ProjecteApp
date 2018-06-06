package com.example.david.myapplication.Juego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
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

public class MostrarUnObjeto extends AppCompatActivity{
    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    private TrackAPI trackServices;
    ProgressBar pb1;
    TextView txtuser;
    private String nombre;
    String tag = "Events";
    int idObjeto;
    private Call<Objeto> callObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lista_objetos);

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
                    TextView et = (TextView)findViewById(R.id.nombre_obj);
                    TextView et2 = (TextView)findViewById(R.id.tipo_obj);
                    TextView et3 = (TextView)findViewById(R.id.valor_obj);
                    TextView et4 = (TextView)findViewById(R.id.coste_obj);
                    TextView et5 = (TextView)findViewById(R.id.descripcion_obj);
                    et.setText(obj.getNombre());
                    et2.setText(obj.getTipo());
                    et3.setText(obj.getValor());
                    et4.setText(obj.getCoste());
                    et5.setText(obj.getDescripcion());
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
    protected void onResume (){
        super.onResume();
        Log.d(tag,"Event a onResume");
    }

    @Override
    protected void onPause (){
        super.onPause();
        Log.d(tag,"Event a onPause");
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
