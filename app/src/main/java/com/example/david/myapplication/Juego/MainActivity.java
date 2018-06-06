package com.example.david.myapplication.Juego;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.david.myapplication.Clases.Login;
import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.R;
import com.example.david.myapplication.Clases.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    //public static final String BASE_URL ="http://localhost:8080/myapp/";
    //public static final String BASE_URL ="http://192.168.42.214:8080/myapp/";
    private TrackAPI trackServices;
    private Call<Usuario> calluser;
    private Call<Boolean> callLog;
    private Call<Objeto> callobject;
    ProgressBar pb1;
    MediaPlayer mediaPlayer;



    String tag = "Events";
    EditText txtuser,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_gradbk);

        Log.d(tag,"Event a onCreate");

        pb1 = (ProgressBar) findViewById(R.id.loading);

        txtuser = (EditText) findViewById(R.id.editText_Usuario);
        txtpassword = (EditText) findViewById(R.id.editText_Password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackServices = retrofit.create(TrackAPI.class);
//        Button buto1 = (Button) findViewById(R.id.button_Login);
//        buto1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postLogin();
//            }
//        });
        Button buto2 = (Button) findViewById(R.id.button_Register);
        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOj = new Intent(MainActivity.this, Register.class);
                startActivity(intentOj);
            }
        });
    }
    public void postLogin(View view){
        pb1.setVisibility(ProgressBar.VISIBLE);
        final String user = txtuser.getText().toString();
        String pass = txtpassword.getText().toString();


        Login log = new Login(user,pass);
        callLog = trackServices.login(log);

        callLog.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();
                Boolean resp = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText (MainActivity.this,"Login correct",Toast.LENGTH_LONG).show();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode)+ "resultado:" + resp);
                    pb1.setVisibility(ProgressBar.INVISIBLE);
                    //obri el proxim layoud que obrira el joc
//                    Intent intentOj = new Intent(MainActivity.this, Juego.class);
//                    startActivity(intentOj);
                    Intent intentOj = new Intent(MainActivity.this, OpcionesMenu.class);
                    intentOj.putExtra("name", user);
                    startActivity(intentOj);
                } else {
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                    Toast.makeText (MainActivity.this,"Usuario/password erroneos",Toast.LENGTH_LONG).show();
                    pb1.setVisibility(ProgressBar.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
