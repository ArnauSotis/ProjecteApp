package com.example.david.myapplication.Juego;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.david.myapplication.Clases.Celda;
import com.example.david.myapplication.MatrizesMapas.Matrizes;
import com.example.david.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    private Bitmap bmpHierba,bmpAgua,bmpArbustoH,bmpArbustoV,bmpCasa1,bmpPuente, bmpPrincipal, bmpVallaV, bmpVallaH;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;
    private Sprite sprite;
    private long lastClick;
    private Sprite moverJugador;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private static final int BMP_ROWS = 24;
    private static final int BMP_COLUMNS = 43;
    private Celda matrizMapa [][] = new Celda[24][43];
    private Celda celdaArbusto = new Celda (2);
    private Celda celdaAgua = new Celda(3);
    private Celda celdaHierba = new Celda(0);
    private Celda celdaActuar = new Celda(1);



    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                createSprites();
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

        bmpHierba = BitmapFactory.decodeResource(getResources(), R.drawable.hierba);
        bmpPuente = BitmapFactory.decodeResource(getResources(), R.drawable.puente);
        bmpAgua = BitmapFactory.decodeResource(getResources(), R.drawable.agua);
        bmpCasa1 = BitmapFactory.decodeResource(getResources(), R.drawable.casa1);
        bmpArbustoH = BitmapFactory.decodeResource(getResources(), R.drawable.arbustoh);
        bmpArbustoV = BitmapFactory.decodeResource(getResources(), R.drawable.arbustov);
        //bmpPrincipal = BitmapFactory.decodeResource(getResources(), R.drawable.good1_opt);
        bmpVallaH = BitmapFactory.decodeResource(getResources(), R.drawable.vallah);
        bmpVallaV = BitmapFactory.decodeResource(getResources(), R.drawable.vallav);

        //sprite = new Sprite(this,bmpPrincipal);
    }

    //@Override
    protected void dibuja(Canvas canvas, int mapa) {
        canvas.drawColor(Color.BLACK);
        //alto1080-ancho1920
        int height = getHeight();
        int width = getWidth();
        //Log.d( "this","alto" + height + "ancho"+width);
        if(mapa==1){
            dibujaMapa1(canvas,height,width);
        }
//        if (x < getWidth() - bmpPuente.getWidth()) {
//            x=x+10;
//            if(x>=1500)
//                x=0;
//        }
//        canvas.drawBitmap(bmpPuente, x, 50, null);
        //sprite.onDraw(canvas);
//        for (Sprite sprite : sprites) {
//            sprite.onDraw(canvas);
//        }
        sprites.get(0).setMatrizMapa(matrizMapa);
        sprites.get(0).onDraw(canvas);
    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);
    }
    private void createSprites() {
        sprites.add(createSprite(R.drawable.good1_opt));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 500) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                    if (isCollitionMap(event.getX(), event.getY())) {
                        //sprites.remove(sprite);
                    }else{
                        float x = event.getX();
                        float y = event.getY();
                        sprites.get(0).caminarPresion(x,y);
                    }
            }
        }
        return true;
    }

    //mapa1
    protected void dibujaMapa1 (Canvas canvas, int height, int width){
        //posició inicial nino mapa 1
        sprites.get(0).iniciNino(45,500);
        sprites.get(0).setEstadoMapa(1);
        //matrizMapa = gameLoopThread.getMatrizMapa();
//        Celda c = matrizMapa[0][0];
//        Log.d("matriz", "tipo:" + c.getTipo());
        for(int y=0;y<height;y=y+45)
        {
            for(int x=0;x<width-90;x=x+45)
            {
                canvas.drawBitmap(bmpHierba, x, y, null);
            }
        }
        for(int y2=0;y2<height;y2=y2+45){
            canvas.drawBitmap(bmpAgua, width-45, y2, null);
            canvas.drawBitmap(bmpAgua, width-90, y2, null);
        }
        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 0, null);
        }
        //arbustos por el medio
        for(int x=45;x<450;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 315, null);
        }
        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, height - 45, null);
        }
        for(int y2=40;y2<height-90;y2=y2+90){
            canvas.drawBitmap(bmpArbustoV, 0, y2, null);
        }
        for(int y=0;y<height-60;y=y+60){
            if (y!=60) {
                canvas.drawBitmap(bmpVallaV, 1810, y, null);
            }
        }
        //puente
        canvas.drawBitmap(bmpPuente, 1820, 45, null);
        //casa
        canvas.drawBitmap(bmpCasa1, 1300, 600, null);
        //pintar el nino

        //Matriz del mapa
        for(int y=45;y<1035;y=y+45){
            for(int x=45;x<1830;x=x+45){
                this.matrizMapa [x][y] = this.celdaHierba;
            }
        }
        for(int x=0; x<1935; x=x+45 ){
            this.matrizMapa [x][0] = this.celdaArbusto;
        }
        for(int x=45; x<450; x=x+45 ){
            this.matrizMapa [x][315] = this.celdaArbusto;
        }
        for(int y=0; y<1080; y=y+45 ){
            this.matrizMapa [0][y] = this.celdaArbusto;
        }
        for(int x=0; x<1935; x=x+45 ){
            this.matrizMapa [x][1035] = this.celdaArbusto;
        }
        for(int y=0;y<1080;y=y+45){
            this.matrizMapa [1875][y] = this.celdaAgua;
            this.matrizMapa [1830][y] = this.celdaAgua;
        }
        sprites.get(0).setMatrizMapa(matrizMapa);
    }
    public boolean isCollitionMap(float x2, float y2) {
        return x2<45 || x2 > getWidth() - 200 || y2 < 45 || y2 > getHeight()-90;
    }

}
