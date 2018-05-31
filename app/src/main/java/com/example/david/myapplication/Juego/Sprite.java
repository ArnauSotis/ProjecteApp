package com.example.david.myapplication.Juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.david.myapplication.Clases.Celda;
import com.example.david.myapplication.Juego.GameView;
import com.example.david.myapplication.MatrizesMapas.Matrizes;

public class Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private static final int MAX_SPEED = 5;
    //posició inicial ninot
    private int x = 45;
    private int y = 500;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap bmpPrincipal;
    private int currentFrame = 0;
    private int width;
    private int height;
    //mapa en el que estas
    private int estadoMapa;
    private Celda matrizMapa [][] = new Celda[24][43];

    private float movx = 45;
    private float movy = 500;

    private int mov = 0;

    public int getEstadoMapa() {
        return estadoMapa;
    }
    public void setEstadoMapa(int estadoMapa) {
        this.estadoMapa = estadoMapa;
    }

    public void setMatrizMapa(Celda[][] matrizMapa) {
        this.matrizMapa = matrizMapa;
    }

    public void iniciNino (int x, int y){
        this.x = x;
        this.y = y;
    }
    public void caminarPresion (float x, float y){
        this.movx = x;
        this.movy = y;
    }
    public void caminar (int mov){
        this.mov = mov;
    }



    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmpPrincipal = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }


    private void update(float puntoX, float puntoY) {
        //width del muñeco
//        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
//            xSpeed = -xSpeed;
//        }
//        x = x + xSpeed;
//        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
//            ySpeed = -ySpeed;
//        }
//        y = y + ySpeed;
        int xp = x/45;
        int yp = y/45;
        Celda p = matrizMapa[x][y];
        //if (p.getTipo()==0){
            if (x < movx) {
                x = x + xSpeed;
            } else if (x > movx){
                //xSpeed = -xSpeed;
                x = x - xSpeed;
            }
            if (y < movy) {
                y = y + ySpeed;
            }else if (y> movy){
                //ySpeed = -ySpeed;
                y = y - ySpeed;
            }
        //}
//        if (x > movx ){
//            xSpeed = -xSpeed;
//        }
//        x = x + xSpeed;
//        if (y> movy){
//            ySpeed = -ySpeed;
//        }
//        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;

    }



    public void onDraw(Canvas canvas) {
        update(movx,movy);
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmpPrincipal, src, dst, null);
    }

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right

    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        Log.d("MONIGOTE", "Direction="+direction);
        Log.d("Speed", "Speedx"+xSpeed + "Speedy"+ySpeed);
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}

