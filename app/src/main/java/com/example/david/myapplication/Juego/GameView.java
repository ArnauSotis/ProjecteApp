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
    private int inici =1;
    private Sprite sprite;
    private long lastClick;
    private Sprite moverJugador;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    Matrizes generadorMatrizes = new Matrizes();
    Celda matrizMapa [][];


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
        //asignamos a cada bitmap el dibujito que le toca
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
    //esta funcion es llamada en el GameLoopThread tod el rato para pintarlo todo
    protected void dibuja(Canvas canvas, int mapa) {
        canvas.drawColor(Color.BLACK);
        //alto1080-ancho1920
        int height = getHeight();
        int width = getWidth();
        //Log.d( "this","alto" + height + "ancho"+width);

        //dibujamos el mapa 1
        if(mapa==1){
            if (this.inici==1){
                sprites.get(0).iniciNino(90,500);
                sprites.get(0).setEstadoMapa(1);
                this.generadorMatrizes.generarMapa(1);
                this.matrizMapa = generadorMatrizes.getMatrizMapa1();
                sprites.get(0).setMatrizMapa(matrizMapa);
                this.inici=0;
            }
            dibujaMapa1(canvas,height,width);
            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);
        }
        //era el puente que se movia por el mapa de lado a lado
//        if (x < getWidth() - bmpPuente.getWidth()) {
//            x=x+10;
//            if(x>=1500)
//                x=0;
//        }
//        canvas.drawBitmap(bmpPuente, x, 50, null);
        //pintar el muñeco en el mapa
        //sprites.get(0).onDraw(canvas);
    }
    //para crear el personaje principal igual haremos para los enemigos
    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);
    }
    private void createSprites() {
        sprites.add(createSprite(R.drawable.good1_opt));
    }

    ///////////////
    //Aqui es donde apretas la pantalla envia las coordenadas al Sprite
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 500) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                //aqui posarem el boto d'agafar objectes
                if(false){

                }
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
        //pinta el mapa a partir de las fotos
        //tod verde
        for(int y=0;y<height;y=y+45)
        {
            for(int x=0;x<width-90;x=x+45)
            {
                canvas.drawBitmap(bmpHierba, x, y, null);
            }
        }
        //agua
        for(int y2=0;y2<height;y2=y2+45){
            canvas.drawBitmap(bmpAgua, width-45, y2, null);
            canvas.drawBitmap(bmpAgua, width-90, y2, null);
        }
        //contorno de los tres muros de arbustos
        //arbusto horizontal de alli viene el nombre de ArbustoH
        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 0, null);
        }

        for(int x=0;x<width-180;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, height - 45, null);
        }
        for(int y2=40;y2<height-90;y2=y2+90){
            canvas.drawBitmap(bmpArbustoV, 0, y2, null);
        }
        //////////////
        //arbustos por el medio
        for(int x=45;x<450;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 315, null);
        }
        //la valla lateral al lado del agua
        for(int y=0;y<height-60;y=y+60){
            if (y!=60) {
                canvas.drawBitmap(bmpVallaV, 1810, y, null);
            }
        }
        //puente
        canvas.drawBitmap(bmpPuente, 1820, 45, null);
        //casa1 i 2 estan juntes
        canvas.drawBitmap(bmpCasa1, 1305, 585, null);
        canvas.drawBitmap(bmpCasa1, 1530, 585, null);

    }
    //son las colisiones del contorno los tres muros de arbusto mas el agua para que no se pueda ir ni apretando pantalla
    public boolean isCollitionMap(float x2, float y2) {
        return x2<45 || x2 > getWidth() - 180  || y2 < 45 || y2 > getHeight()-90;
    }

}
