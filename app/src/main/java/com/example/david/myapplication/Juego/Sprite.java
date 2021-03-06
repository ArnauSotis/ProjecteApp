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
    private int x;
    private int y;
    private int matrizX;
    private int matrizY;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private GameView gameView;
    private Bitmap bmpPrincipal;
    private int currentFrame = 0;
    private int width;
    private int height;
    private boolean estadoPuenteMapa1=false;
    //mapa en el que estas
    private int estadoMapa;
    Celda matrizMapa [][] = new Celda[24][43];

    private float movx;
    private float movy;

    private int mov = 0;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y){this.y = y;}

    public int getY() {
        return y;
    }

    public int getMatrizX() {
        return matrizX;
    }
    public int getMatrizY() {
        return matrizY;
    }


    public int getEstadoMapa() {
        return estadoMapa;
    }
    //para saber en que mapa esta el sprite
    public void setEstadoMapa(int estadoMapa) {
        this.estadoMapa = estadoMapa;
    }
    //se introduce la matriz del mapa en el que estemos
    public void setMatrizMapa(Celda[][] matrizMapa) {
        this.matrizMapa = matrizMapa;
    }
    //donde se pone el muñeco solo empezar viene del GameView
    public void iniciNino (int x, int y){
        this.x = x;
        this.y = y;
        this.movx =x;
        this.movy =y;
    }
    //son las coordenadas de donde queremos ir, cuando pulsamos la pantalla
    public void caminarPresion (float x, float y){
        this.movx = x;
        this.movy = y;
    }
    //era despres de moure el pont modificar la matriu per poder pasar per sobre pero no ha funcionat
    public void modiMapa1Pont(){
        for(int x=39;x<44;x++){
            Celda c = new Celda(0);
            this.matrizMapa[6][x] = c;
        }
    }

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmpPrincipal = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    //actualiza la posición del muñeco lo va moviendo se actualiza cada poco ms esta declarado en GameLoopThread
    private void update() {
        //width del muñeco
//        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
//            xSpeed = -xSpeed;
//        }
//        x = x + xSpeed;
//        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
//            ySpeed = -ySpeed;
//        }
//        y = y + ySpeed;
        int xp = x / 45;
        Log.d("columna", ":" + xp);
        int yp = y / 45;
        Log.d("fila", ":" + yp);
        // direction = 0 up, 1 left, 2 down, 3 right,
        //comprobamos las 4 casillas posibles donde el muñeco puede ir
        Celda p0 = matrizMapa[yp + 1][xp];
        Celda p1 = matrizMapa[yp][xp - 1];
        Celda p2 = matrizMapa[yp - 1][xp];
        Celda p3 = matrizMapa[yp][xp + 1];
        //diagonales
        Celda p4 = matrizMapa[yp+1][xp+1];
        Celda p5 = matrizMapa[yp-1][xp+1];
        Celda p6 = matrizMapa[yp-1][xp-1];
        Celda p7 = matrizMapa[yp+1][xp-1];
        Log.d("valor de la celda", ":" + p0.getTipo());
        Log.d("valor de la celda", ":" + p1.getTipo());
        Log.d("valor de la celda", ":" + p2.getTipo());
        Log.d("valor de la celda", ":" + p3.getTipo());
        //moviment de x
        int movxs = (int)movx/45;
        int movys = (int)movy/45;
        if (xp == movxs) {
            xSpeed = 0;
        } else if(x < movx) {
            if ((p3.getTipo() == 0 && p5.getTipo() == 0 )|| (p3.getTipo() == 0 && p4.getTipo() == 0) ) {
                xSpeed = +6;
                x = x + 6;
            }
        }else {
            //else if (x > movx) {
            if (p1.getTipo() == 0 && p6.getTipo() == 0 || p1.getTipo() == 0 && p7.getTipo() == 0 ) {
                xSpeed = -6;
                x = x - 6;
            }
        }
        //moviment de y
        if (yp == movys) {
            ySpeed = 0;
        }else if(y < movy){
            if(p0.getTipo()==0 && p4.getTipo() == 0 || p0.getTipo() == 0 && p7.getTipo() == 0 ){
                ySpeed = +6;
                y = y + 6;
            }
        }else {
        //else if (y > movy) {
            if(p2.getTipo()==0 && p6.getTipo() == 0 || p2.getTipo() == 0 && p5.getTipo() == 0 ){
                ySpeed = -6;
                y = y - 6;
            }
        }
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    //pinta el muñeco despues del update
    public void onDraw(Canvas canvas) {
        int goToX = (int)movx/45;
        int gotToY = (int)movy/45;
        int xp = x/45;
        int yp = y/45;
        matrizX=xp;
        matrizY=yp;
        if (goToX==xp && gotToY==yp){
            int srcX = currentFrame * width;
            int srcY = getAnimationRow() * height;
            Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
            Rect dst = new Rect(x, y, x + width, y + height);
            canvas.drawBitmap(bmpPrincipal, src, dst, null);
        } else if (goToX==xp+1 && gotToY==yp+1){
            int srcX = currentFrame * width;
            int srcY = getAnimationRow() * height;
            Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
            Rect dst = new Rect(x, y, x + width, y + height);
            canvas.drawBitmap(bmpPrincipal, src, dst, null);
        }else {
            update();
            int srcX = currentFrame * width;
            int srcY = getAnimationRow() * height;
            Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
            Rect dst = new Rect(x, y, x + width, y + height);
            canvas.drawBitmap(bmpPrincipal, src, dst, null);
        }

    }

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right

    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        Log.d("MONIGOTE", "Direction="+direction);
        Log.d("Speed", "Speedx"+xSpeed + "Speedy"+ySpeed);

        Log.d("POSICION X:", ""+x);

        Log.d("POSICION Y:", ""+y);

        Log.d("Speed", "Speedx"+xSpeed + "Speedy"+ySpeed);
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}

