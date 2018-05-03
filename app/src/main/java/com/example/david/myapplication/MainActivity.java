package com.example.david.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String tag = "Events";
    EditText txtuser,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gradbk);
        Log.d(tag,"Event a onCreate");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        txtuser = (EditText) findViewById(R.id.editText_Usuario);
        txtpassword = (EditText) findViewById(R.id.editText_Password);

        Button buto1 = (Button) findViewById(R.id.button_Login);
        buto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String userString = txtuser.getText().toString();
//                String passString = txtpassword.getText().toString();
//                Intent intentOj = new Intent(MainActivity.this, Activity2.class);
//                intentOj.putExtra("userName",userString);
//                intentOj.putExtra("password",passString);
                consumirServicio();
                //startActivity(intentOj);
                //Toast.makeText(MainActivity.this, "Usuario no existente", Toast.LENGTH_LONG).show();
            }
        });
        Button buto2 = (Button) findViewById(R.id.button_Register);
        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOj = new Intent(MainActivity.this, Register.class);
                startActivity(intentOj);
            }
        });
    }

    public boolean consumirServicio(){
        boolean resp;
        // ahora ejecutaremos el hilo creado
        String userName= txtuser.getText().toString();
        String password= txtpassword.getText().toString();

        ServicioTask2 servicioTask2= new ServicioTask2(this,"http://localhost:8080/myapp/json/login",userName,password);
        servicioTask2.execute();
        return true;
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
    protected void onStop (){
        super.onStop();
        Log.d(tag,"Event a onStop");
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
