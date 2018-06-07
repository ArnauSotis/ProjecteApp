package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.Clases.Usuario;
import com.example.david.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OpcionesMenu extends AppCompatActivity {

    TextView txtuser;
    private String nombre;
    String tag = "Events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_opciones_menu);

        Bundle intentdata = getIntent().getExtras();
        nombre = intentdata.getString("name");
        txtuser = (TextView) findViewById(R.id.editText_user);
        txtuser.setText(nombre);

        Button buto2 = (Button) findViewById(R.id.lista_objetos);
        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOj = new Intent(OpcionesMenu.this, ListaObjetos.class);
                intentOj.putExtra("name", nombre);
                startActivity(intentOj);
            }
        });

    }

    public void runJuego (View view){
        Intent intentOj = new Intent(OpcionesMenu.this, SplashActivityStart.class);
        startActivity(intentOj);
    }

    //public void listaObjetos (View view){
    //    Intent intentOj = new Intent(OpcionesMenu.this, ListaObjetos.class);
    //    intentOj.putExtra("name", nombre);
    //    startActivity(intentOj);
    //}
    public void logOut (View view){
        Intent intentOj = new Intent(OpcionesMenu.this, MainActivity.class);
        startActivity(intentOj);
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

    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        Log.d(tag,"Event a onDestroy");
    }
}
