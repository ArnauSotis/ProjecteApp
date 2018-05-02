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

//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String tag = "Events";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d(tag,"Event a onCreate");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button buto1 = (Button) findViewById(R.id.login);
        buto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtuser = (EditText) findViewById(R.id.username);
                EditText txtpassword = (EditText) findViewById(R.id.password);
                String userString = txtuser.getText().toString();
                String passString = txtpassword.getText().toString();
                Intent intentOj = new Intent(MainActivity.this, Activity2.class);
                intentOj.putExtra("userName",userString);
                intentOj.putExtra("password",passString);

//                HttpClient httpClient = new DefaultHttpClient();
//                HttpPost post = new HttpPost("http://localhost:8080/myapp/json/login");
//                post.setHeader("content-type", "application/json");
//                JSONObject dato = new JSONObject();
//                ArrayList<String> lista = new ArrayList<>();
//                lista.add(userString);
//                lista.add(passString);
//                try {
//                    dato.put("lista",lista);
//                    StringEntity entity = new StringEntity(dato.toString());
//                    post.setEntity(entity);
//                    HttpResponse resp = httpClient.execute(post);
//                    String respStr = EntityUtils.toString(resp.getEntity());
//                    if (!respStr.equals("true")){
//                        startActivity(intentOj);
//                    }else{
//                        Toast.makeText(MainActivity.this, "Usuario no existente", Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                startActivity(intentOj);
                //Toast.makeText(MainActivity.this, "Usuario no existente", Toast.LENGTH_LONG).show();
            }
        });
        Button buto2 = (Button) findViewById(R.id.register);
        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOj = new Intent(MainActivity.this, Register.class);
                startActivity(intentOj);
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
