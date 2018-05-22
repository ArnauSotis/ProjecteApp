package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2  extends AppCompatActivity {
    TextView txview2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
//        txview2 = (TextView) findViewById(R.id.textView2);
//        Bundle intentdata = getIntent().getExtras();
//        String txtpas = intentdata.getString("userName");
//        String txtpas2 = intentdata.getString("password");
//        txview2.setText(txtpas + txtpas2);
        Toast.makeText(Activity2.this, "Successful", Toast.LENGTH_LONG).show();

    }
}
