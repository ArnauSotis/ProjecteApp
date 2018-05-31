package com.example.david.myapplication.Juego;

import android.graphics.Canvas;

import com.example.david.myapplication.Clases.Celda;
import com.example.david.myapplication.Juego.GameView;
import com.example.david.myapplication.MatrizesMapas.Matrizes;

public class GameLoopThread extends Thread {

    static final long FPS = 30;
    private int mapaActual = 1;
    private GameView view;
    private boolean running = false;


    public GameLoopThread(GameView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.dibuja(c,mapaActual);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {
                // DO NOTHING
            }
        }
    }
    public void cambiarMapa (int newMapa){
        this.mapaActual = newMapa;
    }
}