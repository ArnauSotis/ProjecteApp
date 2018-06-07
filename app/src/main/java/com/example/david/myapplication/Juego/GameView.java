package com.example.david.myapplication.Juego;
import android.content.Context;
import android.content.Intent;
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

    private Bitmap bmpHierba,bmpAgua,bmpArbustoH,bmpArbustoV,bmpCasa1,bmpPuente, bmpPrincipal, bmpVallaV, bmpVallaH,bmpTexto, bmpPatrolderecha, bmpPatrolizquierda,bmpMalo1,bmpMascota,bmpCofreAbierto,bmpCofreCerrado;
    private Bitmap bmpAmiga1PosD, bmpFuente1, bmpCajaNormal, bmpCajitas, bmpArbolCortado;
    private Bitmap bandera;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;
    private int patrolx =20, patroly=20, direccion = 1;
    private int inici =1;
    private Sprite sprite;
    private long lastClick;
    private Sprite moverJugador;
    private SpriteMalo1 spriteMalo1;
    private SpriteMascota spriteMascota;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private int posBandera=1;

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
        //bmpTexto = BitmapFactory.decodeResource(getResources(), R.drawable.texto);
        bmpCofreCerrado = BitmapFactory.decodeResource(getResources(), R.drawable.cofre_cerrado);
        bmpCofreAbierto = BitmapFactory.decodeResource(getResources(), R.drawable.cofre_abierto);
        bmpPatrolderecha = BitmapFactory.decodeResource(getResources(),R.drawable.patrolderecha);
        bmpPatrolizquierda = BitmapFactory.decodeResource(getResources(),R.drawable.patrolizquierda);
        bmpMalo1 = BitmapFactory.decodeResource(getResources(), R.drawable.maul);
        spriteMalo1 =  new SpriteMalo1(this,bmpMalo1);
        bmpMascota = BitmapFactory.decodeResource(getResources(), R.drawable.mascota);
        spriteMascota =  new SpriteMascota(this,bmpMascota);
        bmpAmiga1PosD = BitmapFactory.decodeResource(getResources(), R.drawable.amiga1pos_dreta);
        bmpFuente1 = BitmapFactory.decodeResource(getResources(), R.drawable.fuente);
        bmpCajaNormal = BitmapFactory.decodeResource(getResources(), R.drawable.caja_normal);
        bmpCajitas = BitmapFactory.decodeResource(getResources(), R.drawable.cajitas);
        bmpArbolCortado = BitmapFactory.decodeResource(getResources(), R.drawable.arbol_cortado);
        bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera1);

        //sprite = new Sprite(this,bmpPrincipal);
    }
    public void music (){
    }

    //@Override
    //esta funcion es llamada en el GameLoopThread tod el rato para pintarlo tod
    protected void dibuja(Canvas canvas, int mapa) {
        canvas.drawColor(Color.BLACK);
        //alto1080-ancho1920
        int height = getHeight();
        int width = getWidth();
        //Log.d( "this","alto" + height + "ancho"+width);

        // Esto nos permite pasar del mapa 1 al mapa 2 consecutivamente
        if (sprites.get(0).getX()>=1500 && sprites.get(0).getY() <100 && mapa ==1) {
            mapa=2;
            sprites.get(0).iniciNino(50,50);
            sprites.get(0).caminarPresion(70,70);
        }
        if (sprites.get(0).getX()>=1500 && sprites.get(0).getY() <100 && mapa ==2) {
            mapa=3;
            sprites.get(0).iniciNino(50,50);
            sprites.get(0).caminarPresion(70,70);
        }
        //dibujamos el mapa 1
        if(mapa==1) {
            if (this.inici==1){
                sprites.get(0).iniciNino(50,950);
                sprites.get(0).setEstadoMapa(1);
                this.generadorMatrizes.generarMapa(1);
                this.matrizMapa = generadorMatrizes.getMatrizMapa1();
                sprites.get(0).setMatrizMapa(matrizMapa);
                //spriteMalo1.iniciNino(400,600);
                //spriteMalo1.patron(700,600);
                spriteMalo1.setDireccion(1);
                spriteMascota.setDireccion(1);
                this.inici=0;
            }

            dibujaMapa1(canvas,height,width);
            //spriteMalo1.iniciNino(400,600);
            //spriteMalo1.patron(700,600);
            spriteMalo1.onDraw(canvas);
            //spriteMascota.onDraw(canvas);
            //pintar el muñeco en el mapa
            gameLoopThread.cambiarMapa(1);
            sprites.get(0).onDraw(canvas);
        }
        if(mapa==2){
            if (this.inici==1){
                sprites.get(0).iniciNino(10,10);
                sprites.get(0).setEstadoMapa(1);
                this.generadorMatrizes.generarMapa(2);
                this.matrizMapa = generadorMatrizes.getMatrizMapa1();
                sprites.get(0).setMatrizMapa(matrizMapa);
                this.inici=0;
            }
            dibujaMapa2(canvas,height,width);
            gameLoopThread.cambiarMapa(2);
            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);

            Log.d("MAPA", "MAPA 2");
        }

        if(mapa==3){
            dibujaMapa3(canvas,height,width);
            gameLoopThread.cambiarMapa(3);
            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);

            Log.d("MAPA", "MAPA 3");
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
        sprites.add(createSprite(R.drawable.pern_principal));
        //sprites.add(createSprite(R.drawable.good1_opt));
//        sprites.add(createSprite(R.drawable.mascota));
//        sprites.add(createSprite(R.drawable.mole));
//        sprites.add(createSprite(R.drawable.good2));
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
//                if (isCollitionMap(event.getX(), event.getY())) {
//                    //sprites.remove(sprite);
//                }else{
//                  float x = event.getX();
//                  float y = event.getY();
//                  sprites.get(0).caminarPresion(x,y);
//                }
                float x = event.getX();
                float y = event.getY();
                sprites.get(0).caminarPresion(x,y);
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
        //els numeros concorden amb un dibuix que tinc jo "David"

        //1
        for(int x=45;x<270;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 900, null);
        }
        //2
        for(int y=675;y<990;y=y+90){
            canvas.drawBitmap(bmpArbustoV, 540, y, null);
        }
        //3
        for(int x=270;x<540;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 675, null);
        }
        //4
        for(int y=495;y<630;y=y+90){
            canvas.drawBitmap(bmpArbustoV, 270, y, null);
        }
        //5
        for(int x=45;x<450;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 315, null);
        }
        //6
        canvas.drawBitmap(bmpArbustoH, 45, 585, null);
        //sota el 6 pintem a un personatge
        canvas.drawBitmap(bmpAmiga1PosD, 50, 630, null);

        //7
        canvas.drawBitmap(bmpArbustoH, 310, 500, null);
        canvas.drawBitmap(bmpArbustoH, 400, 500, null);
        //sota el 7 posem cofre
        canvas.drawBitmap(bmpCofreCerrado, 320, 545, null);
        //la valla lateral al lado del agua
        for(int y=0;y<height-60;y=y+60){
            if (y!=60 && y!=120) {
                canvas.drawBitmap(bmpVallaV, 1810, y, null);
            }
        }
        //puente
        canvas.drawBitmap(bmpPuente, 1820, 90, null);
        //fuente y entras en la esquina superior
        canvas.drawBitmap(bmpFuente1, 70, 60, null);
        canvas.drawBitmap(bmpCajitas, 70, 250, null);
        canvas.drawBitmap(bmpCajitas, 190, 250, null);

        //cesped encima de las casas
        for(int x=1280;x<1730;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 570, null);
        }
        //cesped rodea casa 1

        //casa1 i 2 estan juntes
        //estaban a 1305 y 1530
        canvas.drawBitmap(bmpCasa1, 1285, 590, null);
        canvas.drawBitmap(bmpCasa1, 1510, 590, null);
        canvas.drawBitmap(bmpCajitas, 1660, 980, null);
        canvas.drawBitmap(bmpCajitas, 1660, 930, null);
        if(posBandera==1){
            this.posBandera=2;
        }
        //canvas.drawBitmap(bandera1, 1600, 100, null);
        else if(posBandera==2){
            bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera2);
            posBandera=3;
        }
        else if(this.posBandera==3){
            bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera3);
            this.posBandera=4;
        }
        else if(this.posBandera==4){
            bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera4);
            this.posBandera=5;
        }
        else if(this.posBandera==5){
            bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera5);
            posBandera=1;
        }
        else{
            posBandera=1;
        }
        canvas.drawBitmap(bandera, 1600, 100, null);


        //muñeco de arnau
//        if (direccion ==1)
//        {
//            canvas.drawBitmap(bmpPatrolderecha, patrolx, 450, null);
//            patrolx=patrolx+10;
//            if (patrolx>1100)
//                direccion =2;
//        }
//        if (direccion ==2)
//        {
//            canvas.drawBitmap(bmpPatrolizquierda, patrolx, 450, null);
//            patrolx=patrolx-10;
//            if (patrolx<50)
//                direccion =1;
//        }




    }

    //son las colisiones del contorno los tres muros de arbusto mas el agua para que no se pueda ir ni apretando pantalla
//    public boolean isCollitionMap(float x2, float y2) {
//        return x2<45 || x2 > getWidth() - 180  || y2 < 45 || y2 > getHeight()-90;
//    }



    protected void dibujaMapa2 (Canvas canvas, int height, int width){
        //todo hierba
        for(int y=0;y<height;y=y+45)
        {
            for(int x=0;x<width-90;x=x+45)
            {
                canvas.drawBitmap(bmpHierba, x, y, null);
            }
        }

        //agua
        for(int y2=0;y2<width;y2=y2+45){
            canvas.drawBitmap(bmpAgua, height-45, y2, null);
            canvas.drawBitmap(bmpAgua, height-90, y2, null);
        }
        canvas.drawBitmap(bmpCasa1, 200, 200, null);

        if (direccion ==1)
        {
            canvas.drawBitmap(bmpPatrolderecha, patrolx, 380, null);
            patrolx=patrolx+10;
            if (patrolx>850)
                direccion =2;
        }
        if (direccion ==2)
        {
            canvas.drawBitmap(bmpPatrolizquierda, patrolx, 380, null);
            patrolx=patrolx-10;
            if (patrolx<50)
                direccion =1;
        }


    }

    protected void dibujaMapa3 (Canvas canvas, int height, int width){
        //todo agua
        for(int y=0;y<height;y=y+45)
        {
            for(int x=0;x<width-90;x=x+45)
            {
                canvas.drawBitmap(bmpHierba, x, y, null);
            }
        }
        if (direccion ==1)
        {
            canvas.drawBitmap(bmpPatrolderecha, patrolx, 450, null);
            patrolx=patrolx+10;
            if (patrolx>1300)
                direccion =2;
        }
        if (direccion ==2)
        {
            canvas.drawBitmap(bmpPatrolizquierda, patrolx, 450, null);
            patrolx=patrolx-10;
            if (patrolx<50)
                direccion =1;
        }


    }

}
