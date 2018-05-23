package com.example.david.myapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

    private Bitmap bmp,bmp2,bmp3,bmp4,bmp5,bmp6;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;



    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while(retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        // DO NOTHING
                    }
                }
            }
        });

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.hierba);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.puente);
        bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.agua);
        bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.casa1);
        bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.arbustoh);
        bmp6 = BitmapFactory.decodeResource(getResources(), R.drawable.arbustov);
    }

    //@Override
    protected void dibuja(Canvas canvas, int mapa) {
        canvas.drawColor(Color.BLACK);
        int height = getHeight();
        int width = getWidth();
        if(mapa==1){
            dibujaMapa1(canvas,height,width);
        }
        if (x < getWidth() - bmp2.getWidth()) {
            x=x+10;
            if(x>=1500)
                x=0;
        }

        canvas.drawBitmap(bmp2, x, 50, null);
    }

    //mapa1
    protected void dibujaMapa1 (Canvas canvas, int height, int width){
        for(int y=0;y<height;y=y+45)
        {
            for(int x=0;x<width-90;x=x+45)
            {
                canvas.drawBitmap(bmp, x, y, null);
            }
        }
        for(int y2=0;y2<height;y2=y2+45){
            canvas.drawBitmap(bmp3, width-45, y2, null);
            canvas.drawBitmap(bmp3, width-90, y2, null);
        }
        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmp5, x, 0, null);
        }
        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmp5, x, height, null);
        }
        for(int y2=40;y2<height;y2=y2+90){
            canvas.drawBitmap(bmp6, 0, y2, null);
        }
        //puente
        canvas.drawBitmap(bmp2, 1820, 45, null);
        //casa
        canvas.drawBitmap(bmp4, 1300, 600, null);
    }

}
