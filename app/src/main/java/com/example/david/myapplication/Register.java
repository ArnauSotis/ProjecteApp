package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    TextView txview2;
    EditText txtuser, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        txtuser = (EditText) findViewById(R.id.newuser);
        txtpassword = (EditText) findViewById(R.id.newpassword);

        Button buto1 = (Button) findViewById(R.id.register);
        buto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumirServicio();
            }
        });
    }

    public void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String userName= txtuser.getText().toString();
        String password= txtpassword.getText().toString();

        ServicioTask servicioTask= new ServicioTask(this,"http://localhost:8080/myapp/json/newUser",userName,password);
        servicioTask.execute();

    }
}
