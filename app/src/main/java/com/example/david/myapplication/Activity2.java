package com.example.david.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2  extends Activity {
    TextView txview2;
    GameView view;
    Sprite moverJugador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this));

//        Button butoDerecha = (Button) findViewById(R.id.button_derecha);
//        butoDerecha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moverJugador.caminar(1);
//            }
//        });
//        Button butoIzquierda = (Button) findViewById(R.id.button_izquierda);
//        butoIzquierda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moverJugador.caminar(2);
//            }
//        });
//        Button butoArriba = (Button) findViewById(R.id.button_arriba);
//        butoArriba.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moverJugador.caminar(3);
//            }
//        });
//        Button butoAbajo = (Button) findViewById(R.id.button_abajo);
//        butoAbajo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moverJugador.caminar(4);
//            }
//        });

    }
//        txview2 = (TextView) findViewById(R.id.textView2);
//        Bundle intentdata = getIntent().getExtras();
//        String txtpas = intentdata.getString("userName");
//        String txtpas2 = intentdata.getString("password");
//        txview2.setText(txtpas + txtpas2);

        //Toast.makeText(Activity2.this, "Successful", Toast.LENGTH_LONG).show();
}
