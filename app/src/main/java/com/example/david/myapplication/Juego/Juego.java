package com.example.david.myapplication.Juego;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.david.myapplication.R;


public class Juego extends Activity {
    TextView txview2;
    GameView view;
    Sprite moverJugador;
    MediaPlayer mediaPlayer;
    String tag = "Event";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this));

    }
    @Override
    protected void onStart (){
        super.onStart();
        Log.d(tag,"Event a onStart");
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
    @Override
    public void onPause() {
        super.onPause();
        //pausar();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }
}
