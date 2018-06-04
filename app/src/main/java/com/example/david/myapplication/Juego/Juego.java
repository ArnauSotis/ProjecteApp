package com.example.david.myapplication.Juego;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.david.myapplication.R;


public class Juego extends Activity {
    TextView txview2;
    GameView view;
    Sprite moverJugador;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this));

    }

     //Toast.makeText(Juego.this, "Successful", Toast.LENGTH_LONG).show();
    @Override
    protected void onStart (){
        super.onStart();
        //Log.d(tag,"Event a onStart");
    }

    @Override
    protected void onResume (){
        super.onResume();
        //Log.d(tag,"Event a onResume");
    }

    @Override
    protected void onPause (){
        super.onPause();
       // Log.d(tag,"Event a onPause");
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
        //Log.d(tag,"Event a onDestroy");
    }
}
