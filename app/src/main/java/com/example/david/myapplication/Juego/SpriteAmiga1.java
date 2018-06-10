package com.example.david.myapplication.Juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.david.myapplication.Clases.Celda;

public class SpriteAmiga1 {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private static final int MAX_SPEED = 5;
    //posició inicial ninot
    private int x;
    private int y;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private GameView gameView;
    private Bitmap bmpPrincipal;
    private int currentFrame = 0;
    private int width;
    private int height;
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

    public void iniciNino (int x, int y){
        this.x = x;
        this.y = y;
        this.movx =x;
        this.movy =y;
    }
    //son las coordenadas de donde queremos ir, cuando pulsamos la pantalla
    public void caminar (float x, float y){
        this.movx = x;
        this.movy = y;
    }


    public SpriteAmiga1(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmpPrincipal = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    //actualiza la posición del muñeco lo va moviendo se actualiza cada poco ms esta declarado en GameLoopThread
    private void update() {
        int xp = x / 45;
        Log.d("columna", ":" + xp);
        int yp = y / 45;
        Log.d("fila", ":" + yp);
        // direction = 0 up, 1 left, 2 down, 3 right,
        //moviment de x
        int movxs = (int)movx/45;
        int movys = (int)movy/45;
        if (xp == movxs) {
            xSpeed = 0;
        } else if(x < movx) {
                xSpeed = +7;
                x = x + 7;
        }else {
                xSpeed = -7;
                x = x - 7;

        }
        //moviment de y
        if (yp == movys) {
            ySpeed = 0;
        }else if(y < movy){
                ySpeed = +7;
                y = y + 7;

        }else {
                ySpeed = -7;
                y = y - 7;

        }
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    //pinta el muñeco despues del update
    public void onDraw(Canvas canvas) {
        int goToX = (int)movx/45;
        int gotToY = (int)movy/45;
        int xp = x/45;
        int yp = y/45;
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
