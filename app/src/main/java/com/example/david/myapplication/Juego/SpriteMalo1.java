package com.example.david.myapplication.Juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class SpriteMalo1 {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private static final int MAX_SPEED = 5;
    //posició inicial ninot
    public int x=400;
    public int y=600;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private GameView gameView;
    private Bitmap bmpMalo1;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int movxfinal=1000;
    private int movyfinal=600;
    private int movxini=400;
    private int movyini=600;
    private int direccion;


    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void iniciNino (int x, int y){
        this.x = x;
        this.y = y;
        this.movxini=x;
        this.movyini=y;
    }
    //son las coordenadas de donde queremos ir, cuando pulsamos la pantalla
    public void patron (int x, int y){
        this.movxfinal = x;
        this.movyfinal = y;
    }

    public SpriteMalo1(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmpMalo1 = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    //actualiza la posición del muñeco lo va moviendo se actualiza cada poco ms esta declarado en GameLoopThread
    private void update() {
        //patrulla horizontal
        //hacia la derecha
        if(direccion==1){
            ySpeed=0;
            xSpeed = +5;
            x = x + 5;
            if (x>=movxfinal){
                this.direccion=2;
            }
        }
        //hacia la izquierda
        if(direccion==2){
            ySpeed=0;
            xSpeed = -5;
            x = x - 5;
            if (x<=movxini){
                this.direccion=1;
            }
        }

        //patrulla vertical
        //Hacia abajo
        if(direccion==3){
            xSpeed=0;
            ySpeed = -5;
            y = y - 5;
            if (y>=movyfinal){
                this.direccion=4;
            }
        }
        //hacia arriba
        if(direccion==4){
            xSpeed=0;
            ySpeed = +5;
            y = y + 5;
            if (y<=movyini){
                this.direccion=3;
            }
        }



//        if (x == movxfinal) {
//                xSpeed = -5;
//                x = x - 5;
//        } else if (x < movxfinal) {
//            xSpeed = +5;
//            x = x + 5;
//        }else {
//            xSpeed = +5;
//            x = x + 5;
//        }
//        //moviment de y
//        if (y == movxfinal) {
//            ySpeed = 0;
//        }else if(y < movyfinal){
//            ySpeed = +5;
//            y = y + 5;
//        }
////        else if(y < movyini){
////            ySpeed = +5;
////            y = y + 5;
////        }
//        else {
//            ySpeed = -5;
//            y = y - 5;
//
//        }

        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    //pinta el muñeco despues del update
    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmpMalo1, src, dst, null);
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
