package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register extends AppCompatActivity {
    TextView txview2;
    EditText txtuser, txtpassword;

    public static final String BASE_URL = "http://147.83.7.206:8080/myapp/";
    public static final String BASE_URL_LOCAL ="http://localhost:8080/myapp/";
    private TrackAPI trackServices;
    private Call<Boolean> calluser;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        txtuser = (EditText) findViewById(R.id.newuser);
        txtpassword = (EditText) findViewById(R.id.newpassword);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_LOCAL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        trackServices = retrofit.create(TrackAPI.class);

        Button buto1 = (Button) findViewById(R.id.register);
        buto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRegister();
            }
        });
    }
    void postRegister(){
        String user = txtuser.getText().toString();
        String pass = txtpassword.getText().toString();
        Usuario usuario = new Usuario(user,pass,0,0,0,0);
        calluser = trackServices.register(usuario);
        calluser.enqueue(new Callback<Boolean>(){

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Boolean resp = response.body();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode) + "resultado:" + resp);
                    Toast.makeText (Register.this,"Correct Register",Toast.LENGTH_SHORT).show();
                    //obri el proxim layoud que obrira el joc
                } else {
                    Toast.makeText (Register.this ,"This user already exist",Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse. Code" + Integer.toString(statusCode));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // log error here since request failed
                Log.d("Request: ", "error loading API" + t.toString());
            }
        });
    }

}
