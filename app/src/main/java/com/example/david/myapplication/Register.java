package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    TextView txview2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EditText txtuser = (EditText) findViewById(R.id.newuser);
        EditText txtpassword = (EditText) findViewById(R.id.newpassword);
        String userString = txtuser.getText().toString();
        String passString = txtpassword.getText().toString();

    }
}
