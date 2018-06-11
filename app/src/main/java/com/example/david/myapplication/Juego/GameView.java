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

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    private Bitmap bmpvida100,bmpvida75,bmpvida50,bmpvida25, bmpHierba,bmpAgua,bmpArbustoH,bmpArbustoV,bmpCasa1,bmpPuente, bmpPrincipal, bmpVallaV, bmpVallaH,bmpTexto, bmpPatrolderecha, bmpPatrolizquierda,bmpMascota,bmpCofre;
    private Bitmap bmpAmiga1PosD,bmpAmiga1PosC, bmpAmiga2PosC, bmpAmigo3PosC, bmpFuente1, bmpCajaNormal, bmpCajitas, bmpArbolCortado, bmpCaseta, bmpPiedra1, bmpPiedra2, bmpPiedra3, bmpConjuntoArbustos, bmpLlave;
    private Bitmap bandera, pensament1, pensament2, pensament3, pensament4, pensaInterrogant, pensaExclamacio, palanca, botonAccion, bmpSueloCasa;
    //interior de la casa
    private Bitmap alfombra, libreria, luz, mesa, pared,planta, silla, logo, pared_falsa, cama;
    //malos + personaje principal accion
    private Bitmap bmpMalo1, bmpMalo2, bmpMalo3, bmpPerPrincipalMovPuente, bmpChicoRio;
    //textos mapa1
    private Bitmap  textoM1_1, textoEspigaOp1, textoEspigaOp2, textoChicoPuenteOp1, textoChicoPuenteOp2, textoFuenteM1Op1, textoFuenteM1Op2, textodespuesPalanca, textoNoPuedesPasar;
    //textos mapa4
    private Bitmap  textoM4_1, textoM4_2, textoM4_3;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;
    private int patrolx =20, patroly=20, direccion = 1;
    private int inici =1;
    private Sprite sprite;
    private long lastClick;
    private Sprite moverJugador;
    private SpriteMalo1 spriteMalo1, spriteMalo2, spriteMalo3;
    private SpriteAmiga1 spritePrincipalPuente, spriteChicoRio;
    private SpriteMascota spriteMascota;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private int posBandera=1;
    private int posPalanca=2;
    private int posPont=1;
    private int posPontX=1875;
    private int posCofre=1;
    //llave mapa 1
    private int llaveX = 230;
    private int llaveY = 150;
    /////////inici del personarje principal en el mapa 1 depenen de on vingui
    private int iniciPrincipalMapa1=1;
    ///////////mapa4
    private boolean haLlegado=false;
    private boolean soloUnaVez=false;
    private boolean haHabladoEspigaOp1=false;

    private int vidaJugador=100;
    private boolean patrol1vivo1=true;
    private boolean patrol1vivo2=true;
    private boolean patrol1vivo3=true;
    //////////////Puente//////////////
    private boolean estadoPuenteMapa1 = false;
    //////////////Para hacer la animacion que el muñeco se va por el puente/////////
    private boolean accionPuente=false;
    private boolean deMapa1A4= false;
    private boolean deMapa1A2=false;
    private boolean deMapa4A1=false;

    private boolean pasarAMapa2=false;
    ////////////contadores textos mapa1/////////////////////
    private int contadorTextM1=1,contadorText2M1=1,contadorText3M1=1,contadorText4M1=1,contadorText5M1=1,contadorText6M1=1,contadorText7M1=1,contadorText8M1=1,contadorText9M1=1;
    ///////////contadores textos mapa4/////////////////
    private int contadorText1M4=1,contadorText2M4=1,contadorText3M4=1;
    //si ha hablado con el chico del rio para ir a la palanca si ha hablado por primera vez o no para sacar un mensaje o otro
    private boolean hablarConChicoRio = false;
    private boolean palancaOn=false;
    private int tieneLaLlave = 1;
    ///////////////////////////////
    private  String nombreJugador;
    private PreguntasServer preguntasServer;
    ///////////////////////LLAVES//////////////////////////////
    boolean llave1_Mapa1=false;

    Matrizes generadorMatrizes = new Matrizes();
    Celda matrizMapa [][];
    Celda matrizMapa2 [][];

    public int getPosPalanca() {
        return posPalanca;
    }
    public void setPosPalanca(int posPalanca) {
        this.posPalanca = posPalanca;
    }

    public int getPosCofre() {
        return posCofre;
    }

    public void setPosCofre(int posCofre) {
        this.posCofre = posCofre;
    }

    public GameView(Context context,String nombre) {
        super(context);
        //descomentar si queremos hacer preguntas al server
        //preguntasServer = new PreguntasServer(context);
        this.nombreJugador = nombre;
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                createSprites();
                //vidaJugador = preguntasServer.getVida(nombreJugador);
                //era que como he jugador lo he creado con 0 de vida la barra de vida a 0 no hace nada le he sumado 50 y veo que si carga bien
                //vidaJugador = vidaJugador+100;
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
        bmpvida100 = BitmapFactory.decodeResource(getResources(), R.drawable.vida100);
        bmpvida75 = BitmapFactory.decodeResource(getResources(), R.drawable.vida75);
        bmpvida50 = BitmapFactory.decodeResource(getResources(), R.drawable.vida50);
        bmpvida25 = BitmapFactory.decodeResource(getResources(), R.drawable.vida25);
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
        bmpCofre = BitmapFactory.decodeResource(getResources(), R.drawable.cofre_cerrado);
        bmpPatrolderecha = BitmapFactory.decodeResource(getResources(),R.drawable.patrolderecha);
        bmpPatrolizquierda = BitmapFactory.decodeResource(getResources(),R.drawable.patrolizquierda);
        ///////textos mapa4
        textoM4_1 = BitmapFactory.decodeResource(getResources(),R.drawable.texto1_m4);
        textoM4_2 = BitmapFactory.decodeResource(getResources(),R.drawable.texto2_m4);
        textoM4_3 = BitmapFactory.decodeResource(getResources(),R.drawable.texto3_m4);
        ///////////
        bmpLlave = BitmapFactory.decodeResource(getResources(), R.drawable.llave);
        bmpMascota = BitmapFactory.decodeResource(getResources(), R.drawable.mascota);
        spriteMascota =  new SpriteMascota(this,bmpMascota);
        bmpAmiga1PosD = BitmapFactory.decodeResource(getResources(), R.drawable.amiga1pos_dreta);
        bmpFuente1 = BitmapFactory.decodeResource(getResources(), R.drawable.fuente);
        bmpCajaNormal = BitmapFactory.decodeResource(getResources(), R.drawable.caja_normal);
        bmpCajitas = BitmapFactory.decodeResource(getResources(), R.drawable.cajitas);
        bmpSueloCasa = BitmapFactory.decodeResource(getResources(), R.drawable.terra_casa);
        bmpArbolCortado = BitmapFactory.decodeResource(getResources(), R.drawable.arbol_cortado);
        bmpCaseta = BitmapFactory.decodeResource(getResources(), R.drawable.caseta);
        bmpPiedra1 = BitmapFactory.decodeResource(getResources(), R.drawable.piedra1);
        bmpPiedra2 = BitmapFactory.decodeResource(getResources(), R.drawable.piedra2);
        bmpConjuntoArbustos = BitmapFactory.decodeResource(getResources(), R.drawable.cuadrado_arbustos);
        bmpPiedra3  = BitmapFactory.decodeResource(getResources(), R.drawable.piedra3);
        pensament1 = BitmapFactory.decodeResource(getResources(), R.drawable.pensamiento1);
        pensament2 = BitmapFactory.decodeResource(getResources(), R.drawable.pensamiento2);
        pensament3= BitmapFactory.decodeResource(getResources(), R.drawable.pensamiento3);
        pensament4= BitmapFactory.decodeResource(getResources(), R.drawable.pensamiento4);
        pensaExclamacio = BitmapFactory.decodeResource(getResources(), R.drawable.pensa_exclamacion);
        pensaInterrogant = BitmapFactory.decodeResource(getResources(), R.drawable.pensa_interrogante);
        botonAccion = BitmapFactory.decodeResource(getResources(), R.drawable.boton_accion);
        textoM1_1 = BitmapFactory.decodeResource(getResources(), R.drawable.textom1_1);
        bmpAmiga1PosC = BitmapFactory.decodeResource(getResources(), R.drawable.amiga1pos_cara);
        bmpAmiga2PosC = BitmapFactory.decodeResource(getResources(), R.drawable.amiga2pos);
        //el amigo3
        bmpAmigo3PosC = BitmapFactory.decodeResource(getResources(), R.drawable.amigo3pos_c);
        //chico del rio
        bmpChicoRio = BitmapFactory.decodeResource(getResources(), R.drawable.chicorio);
        spriteChicoRio =  new SpriteAmiga1(this,bmpChicoRio);
        ///textos mapa 1////
        textoEspigaOp1 = BitmapFactory.decodeResource(getResources(), R.drawable.texto_espiga);
        textoEspigaOp2 = BitmapFactory.decodeResource(getResources(), R.drawable.texto_espigaytappers);
        textoChicoPuenteOp1 = BitmapFactory.decodeResource(getResources(), R.drawable.chico_puente_op1);
        textoChicoPuenteOp2 = BitmapFactory.decodeResource(getResources(), R.drawable.chico_puente_op2);
        textoFuenteM1Op1 = BitmapFactory.decodeResource(getResources(), R.drawable.fuente_m1op1);
        textoFuenteM1Op2 = BitmapFactory.decodeResource(getResources(), R.drawable.fuente_m1op2);
        textodespuesPalanca = BitmapFactory.decodeResource(getResources(), R.drawable.texto_despuespalanca);
        textoNoPuedesPasar = BitmapFactory.decodeResource(getResources(), R.drawable.texto_nopuedespasar);
        /////////////
        bmpMalo2 = BitmapFactory.decodeResource(getResources(), R.drawable.malo1);
        bmpMalo3 = BitmapFactory.decodeResource(getResources(), R.drawable.malo1);
        bmpMalo1 = BitmapFactory.decodeResource(getResources(), R.drawable.malo1);
        bmpPerPrincipalMovPuente = BitmapFactory.decodeResource(getResources(), R.drawable.pern_principal);
        spritePrincipalPuente = new SpriteAmiga1(this,bmpPerPrincipalMovPuente);
        spriteMalo1 =  new SpriteMalo1(this,bmpMalo1);
        spriteMalo2 =  new SpriteMalo1(this,bmpMalo2);
        spriteMalo3 =  new SpriteMalo1(this,bmpMalo3);
        palanca = BitmapFactory.decodeResource(getResources(), R.drawable.palanca2);
        bandera = BitmapFactory.decodeResource(getResources(), R.drawable.bandera1);
        /////interior de la casa
        alfombra = BitmapFactory.decodeResource(getResources(), R.drawable.alfombra);
        mesa = BitmapFactory.decodeResource(getResources(), R.drawable.mesa);
        silla = BitmapFactory.decodeResource(getResources(), R.drawable.silla);
        libreria = BitmapFactory.decodeResource(getResources(), R.drawable.libreria);
        pared = BitmapFactory.decodeResource(getResources(), R.drawable.pared);
        planta =BitmapFactory.decodeResource(getResources(), R.drawable.planta);
        luz = BitmapFactory.decodeResource(getResources(), R.drawable.luz);
        logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        cama = BitmapFactory.decodeResource(getResources(), R.drawable.cama);
        pared_falsa = BitmapFactory.decodeResource(getResources(), R.drawable.pared_falsa);
        //sprite = new Sprite(this,bmpPrincipal);
    }

    //@Override
    //esta funcion es llamada en el GameLoopThread tod el rato para pintarlo tod
    protected void dibuja(Canvas canvas, int mapa) {
        canvas.drawColor(Color.BLACK);
        Log.d("NombreJugador",":"+nombreJugador);
        Log.d("vida", "vida:   "+vidaJugador);
        //alto1080-ancho1920
        int height = getHeight();
        int width = getWidth();
        int posx = sprites.get(0).getX();
        int posy = sprites.get(0).getY();
        //Log.d( "this","alto" + height + "ancho"+width);
        //Cuando esta delante del puente y el puente esta tocando tierra podemos pasar al mapa 2
        if(deMapa1A2 && pasarAMapa2){
                gameLoopThread.cambiarMapa(2);
                mapa=2;
                this.inici=1;
                sprites.get(0).iniciNino(70,70);
                deMapa1A2=false;
                pasarAMapa2=false;
        }
        if(deMapa1A4){
                gameLoopThread.cambiarMapa(4);
                mapa=4;
                this.inici=1;
                deMapa1A4=false;

        }
        if(deMapa4A1){
            gameLoopThread.cambiarMapa(1);
            mapa=1;
            this.inici=1;
            this.iniciPrincipalMapa1=3;
            deMapa4A1=false;
        }
        // Esto nos permite pasar del mapa 1 al mapa 2 consecutivamente
//        if (sprites.get(0).getX()>=1500 && sprites.get(0).getY() <100 && mapa ==1) {
//            mapa=2;
//            sprites.get(0).iniciNino(50,50);
//            sprites.get(0).caminarPresion(70,70);
//        }
//        if (sprites.get(0).getX()>=1500 && sprites.get(0).getY() <100 && mapa ==2) {
//            mapa=3;
//            sprites.get(0).iniciNino(50,50);
//            sprites.get(0).caminarPresion(70,70);
//        }
        //dibujamos el mapa 1
        if(mapa==1) {
            if (this.inici==1){
                if(iniciPrincipalMapa1==1){
                    //aquest es el inici del principi del joc
                    sprites.get(0).iniciNino(50,950);
                }else if(iniciPrincipalMapa1==3){
                    //sortin de la casa del espiga
                    sprites.get(0).iniciNino(855,230);
                }else if(iniciPrincipalMapa1==2){
                    //tornan del pont
                    sprites.get(0).iniciNino(1820,270);
                }else{

                }
                sprites.get(0).setEstadoMapa(1);
                this.generadorMatrizes.generarMapa1();
                this.matrizMapa = generadorMatrizes.getMatrizMapa1();
                sprites.get(0).setMatrizMapa(matrizMapa);
                spriteMalo1.iniciNino(700,600);
                spriteMalo1.patron(1000,600);
                spriteMalo2.iniciNino(450,250);
                spriteMalo2.patron(700,250);
                spriteMalo3.iniciNino(1400,50);
                spriteMalo3.patron(1400,230);
                spriteChicoRio.iniciNino(1740, 650);
                spriteChicoRio.caminar(1740,765);
                spriteMalo1.setDireccion(1);
                spriteMalo2.setDireccion(1);
                spriteMalo3.setDireccion(3);
                spriteMascota.setDireccion(1);
                this.inici=0;
            }

            dibujaMapa1(canvas,height,width);

            //spriteMalo1.iniciNino(400,600);
            //spriteMalo1.patron(700,600);
            if (patrol1vivo1==true) {
                spriteMalo1.onDraw(canvas);
            }
            if (patrol1vivo2==true) {
                spriteMalo2.onDraw(canvas);
            }
            if (patrol1vivo3==true) {
                spriteMalo3.onDraw(canvas);
            }
            //spriteMascota.onDraw(canvas);
            //pintar el muñeco en el mapa
            spriteChicoRio.onDraw(canvas);
            gameLoopThread.cambiarMapa(1);
            sprites.get(0).onDraw(canvas);
        }
        if(mapa==2){
            if (this.inici==1){
                sprites.get(0).iniciNino(70,70);
                sprites.get(0).setEstadoMapa(2);
                this.generadorMatrizes.generarMapa2();
                //encara que sifui la segona carrego la primera sino tinc problemes ja que la segoana encara no esta feta
                this.matrizMapa2 = generadorMatrizes.getMatrizMapa2();
                sprites.get(0).setMatrizMapa(matrizMapa2);
                //sprites.get(0).caminarPresion(5,5);
                this.inici=0;
            }
            dibujaMapa2(canvas,height,width);
            gameLoopThread.cambiarMapa(2);
            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);

            Log.d("MAPA", "MAPA 2");
        }

        if(mapa==3){
            if (this.inici==1){
                sprites.get(0).iniciNino(50,950);
                sprites.get(0).setEstadoMapa(3);
                this.generadorMatrizes.generarMapa3();
                this.matrizMapa = generadorMatrizes.getMatrizMapa3();
                sprites.get(0).setMatrizMapa(matrizMapa);
                this.inici=0;
            }

            dibujaMapa3(canvas,height,width);
            gameLoopThread.cambiarMapa(3);

            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);

            Log.d("MAPA", "MAPA 3");
        }
        if(mapa==4){
            if (this.inici==1){
                sprites.get(0).iniciNino(945,715);
                sprites.get(0).setEstadoMapa(4);
                this.generadorMatrizes.generarMapa4();
                this.matrizMapa = generadorMatrizes.getMatrizMapa4();
                sprites.get(0).setMatrizMapa(matrizMapa);
                this.inici=0;
            }
            dibujaMapa4(canvas,height,width);
            gameLoopThread.cambiarMapa(4);
            //pintar el muñeco en el mapa
            sprites.get(0).onDraw(canvas);

            Log.d("MAPA", "MAPA 4");
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Aqui es donde apretas la pantalla envia las coordenadas al Sprite
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 500) {
            lastClick = System.currentTimeMillis();

            synchronized (getHolder()) {
                //aqui posarem el boto d'agafar objectes
                if(false){

                }
                float x = event.getX();
                float y = event.getY();
                int posx = sprites.get(0).getX();
                int posy = sprites.get(0).getY();
                if(x>=1780 && y>=940){
                    int posX =sprites.get(0).getX();
                    int posY = sprites.get(0).getY();
                    int columnaX = posX/45;
                    int filaY = posY/45;
                    ////////////////////tod esto mapa 1///////////////////
                    //cofre
                    if(filaY==11&&columnaX==7 || filaY==12&&columnaX==7|| filaY==11&&columnaX==8|| filaY==12&&columnaX==8|| filaY==12&&columnaX==6|| filaY==11&&columnaX==6){
                        posCofre=2;
                        Log.d("entra",":en abrir cofre");
                    }
                    //palanca mirar mas posiciones
                    if(filaY==9 && columnaX==34 || filaY==9 && columnaX==35 || filaY==9 && columnaX==33){
                        Log.d("entra",":mover puente");
                        posPalanca=1;
                        posPont=2;
                        deMapa1A2=true;
                    }
                    if(posy/45==13 && posx/45==2 || posy/45==13 && posx/45==3 || posy/45==13 && posx/45==4 || posy/45==13 && posx/45==5){
                        if(contadorTextM1==1){
                            contadorTextM1=2;
                        }
                    }
                    if (posy/45==4 && posx/45==19 || posy/45==4 && posx/45==20){
                        if(contadorText2M1==1){
                            contadorText2M1=2;
                            haHabladoEspigaOp1=true;
                            deMapa1A4=true;
                        }
                    }
                    //los tres chicos que no quieren hablar
                    if(posy/45==19 && posx/45==15 || posy/45==19 && posx/45==14){
                        if(contadorText3M1==1){
                            contadorText3M1=2;
                        }
                    }
                    if(posy/45==17 && posx/45==30|| posy/45==17 && posx/45==31){
                        if(contadorText7M1==1){
                            contadorText7M1=2;
                        }
                    }
                    if(posy/45==17 && posx/45==35|| posy/45==17 && posx/45==36){
                        if(contadorText8M1==1){
                            contadorText8M1=2;
                        }
                    }
                    /////////////////////
                    if(posy/45==3 && posx/45==5 || posy/45==4 && posx/45==5 || posy/45==2 && posx/45==5){
                        if(contadorText4M1==1){
                            contadorText4M1=2;
                        }
                    }
                    //chico rio
                    if(tieneLaLlave==1){
                        if(posy/45==17 && posx/45==38 || posy/45==17 && posx/45==39){
                            if(contadorText9M1==1){
                                contadorText9M1=2;
                                tieneLaLlave=1;
                            }
                        }

                    }
                    if(tieneLaLlave==2){
                        if(posy/45==17 && posx/45==38 || posy/45==17 && posx/45==39){
                            if(contadorText5M1==1){
                                contadorText5M1=2;
                                tieneLaLlave=3;
                            }
                        }
                    }
                    //mensaje al apretar la palanca
                    if(palancaOn){
                        if( posPalanca==1 && filaY==9 && columnaX==34 || posPalanca==1 && filaY==9 && columnaX==35 || posPalanca==1 && filaY==9 && columnaX==33){
                            if(contadorText6M1==1){
                                contadorText6M1=2;
                            }
                        }
                    }
                    ////////////////////mapa 1 hasta aqui///////////////////
                    //////////////mapa 4////////////
                    if( filaY==8 && columnaX==24 || filaY==8 && columnaX==25){
                        if(contadorText1M4==1){
                            contadorText1M4=2;
                        }
                    }
                    if( filaY==8 && columnaX==16 || filaY==8 && columnaX==15){
                        if(contadorText2M4==1){
                            contadorText2M4=2;
                        }
                    }
                    if( filaY==8 && columnaX==20){
                        if(contadorText3M4==1){
                            contadorText3M4=2;
                        }
                    }
                    ////////////////////////////hasat aqui map4
                }else{
                    sprites.get(0).caminarPresion(x,y);
                }
//                if (isCollitionMap(event.getX(), event.getY())) {
//                    //sprites.remove(sprite);
//                }else{
//                  float x = event.getX();
//                  float y = event.getY();
//                  sprites.get(0).caminarPresion(x,y);
//                }

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
        canvas.drawBitmap(bmpAmiga1PosD, 65, 630, null);

        //7
        canvas.drawBitmap(bmpArbustoH, 310, 500, null);
        canvas.drawBitmap(bmpArbustoH, 400, 500, null);
        //sota el 7 posem cofre
        if(posCofre==2){
            bmpCofre = BitmapFactory.decodeResource(getResources(), R.drawable.cofre_abierto); }
        canvas.drawBitmap(bmpCofre, 320, 545, null);
        //8
        for(int x=1055;x<1235;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 660, null);
        }
        canvas.drawBitmap(bmpCajitas, 1100, 705, null);
        //9
        for(int x=585;x<765;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 675, null);
        }
        //10
        canvas.drawBitmap(bmpCasa1, 585, 675, null);
        //nino davant la porta
        canvas.drawBitmap(bmpAmiga1PosC, 665, 840, null);
        //extra caja normal debajo esta casa
        canvas.drawBitmap(bmpCajaNormal, 585, 975, null);
        //15
        for(int y=45;y<315;y=y+90){
            canvas.drawBitmap(bmpArbustoV, 750, y, null);
        }
        //16 casa
        canvas.drawBitmap(bmpCasa1, 795, 15, null);
        //nino de la casa
        canvas.drawBitmap(bmpAmiga2PosC, 810, 185, null);

        //17 em dona molts problemes
        canvas.drawBitmap(bmpArbolCortado,720,495,null);
        //18
        for(int y=300;y<481;y=y+90) {
            canvas.drawBitmap(bmpArbustoV, 1415, y, null);
        }
        //19
        for(int x=1460;x<1810;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 390, null);
        }

        //20 extras de piedras numero 3
        canvas.drawBitmap(bmpPiedra3, 1340, 510, null);
        canvas.drawBitmap(bmpPiedra3, 680, 50, null);
        canvas.drawBitmap(bmpPiedra3, 1740, 50, null);

        //la valla lateral al lado del agua
        for(int y=0;y<height-60;y=y+60){
            if (y!=240 && y!=300 && y!=360) {
                canvas.drawBitmap(bmpVallaV, 1810, y, null);
            }
        }
        ///////////////////////////////////////////////////////////////////////////////
        //puente
        if(posPont>1 && posPont<13){
            if(posPontX!=1820){
                //ha de arribar a 1820
                posPontX = posPontX -5;
                posPont++;
            }else{
                //sprites.get(0).modiMapa1Pont();
                this.deMapa1A2=true;
//                this.generadorMatrizes.modiPuenteMapa1();
//                this.matrizMapa = this.generadorMatrizes.getMatrizMapa1();
//                sprites.get(0).setMatrizMapa(matrizMapa);
            }
        }else if(posPont==1){
            posPontX=1880;
        }else{
            posPontX=1820;
        }
        canvas.drawBitmap(bmpPuente, posPontX, 270, null);
        //21 fuente y entras en la esquina superior
        canvas.drawBitmap(bmpFuente1, 70, 60, null);
        canvas.drawBitmap(bmpCajitas, 70, 250, null);
        canvas.drawBitmap(bmpCajitas, 190, 250, null);

        //cesped encima de las casas -  12
        for(int x=1280;x<1730;x=x+90){
            canvas.drawBitmap(bmpArbustoH, x, 570, null);
        }
        //cesped rodea casa -- 11
        for(int y=570;y<840;y=y+90){
            canvas.drawBitmap(bmpArbustoV, 1235, y, null);
        }
        //casa1 i 2 estan juntes  -- 13 i 14
        canvas.drawBitmap(bmpCasa1, 1285, 590, null);
        canvas.drawBitmap(bmpCasa1, 1520, 590, null);
        //personajes delante de la casa
        canvas.drawBitmap(bmpAmiga1PosC, 1360, 750, null);
        canvas.drawBitmap(bmpAmiga1PosC, 1595, 750, null);
        canvas.drawBitmap(bmpCajitas, 1660, 980, null);

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
        canvas.drawBitmap(bandera, 1030, 130, null);
        canvas.drawBitmap(bmpPiedra1, 1040, 230, null);
        canvas.drawBitmap(bmpPiedra2, 1020, 230, null);

        /////////////////////////
        //Boton
        canvas.drawBitmap(botonAccion, 1780, 940, null);

        //VIDA DEL PERSONAJE

        if(vidaJugador==100)
            canvas.drawBitmap(bmpvida100, height/2, 50,null);
        if(vidaJugador==75)
            canvas.drawBitmap(bmpvida75, height/2, 50,null);
        if(vidaJugador==50)
            canvas.drawBitmap(bmpvida50, height/2, 50,null);
        if(vidaJugador==25)
            canvas.drawBitmap(bmpvida25, height/2, 50,null);

        //COLISION KEKO VS MALO!!!!!!!!!!!!!!!!!!
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
        //mensajess/////////////////////////////////////////////////////////////////////////////////////////
        int posx = sprites.get(0).getX();
        int posy = sprites.get(0).getY();
        //mensaje primero del mapa
        if(posy/45==13 && posx/45==2 || posy/45==13 && posx/45==3 || posy/45==13 && posx/45==4 || posy/45==13 && posx/45==5){
            if(contadorTextM1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoM1_1, 550, 680, null);
                canvas.drawBitmap(pensaExclamacio, 90, 600, null);
            }
            if(contadorTextM1==2){
                //canvas.drawBitmap(textoM1_1, 2000, 600, null);
                //canvas.drawBitmap(pensaExclamacio, 2000, 680, null);
            }

        }
        //mensaje del chico de la casa con bandera "Espiga"
        if(posy/45==4 && posx/45==19 || posy/45==4 && posx/45==20){
            if(contadorText2M1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoEspigaOp1, 550, 680, null);
                canvas.drawBitmap(pensaExclamacio, 825, 165, null);
            }
            if(haHabladoEspigaOp1){
                deMapa1A4=true;
            }
        }
        //mensaje de los 3 muñecos que no te dejan entrar en casa
        //y=19 x=15 casa 10
        //y=17 x=30 casa 13
        //y=17 x=36 casa 14
        //afegeixo un a la esquerra o dreta per aproximació
        if(posy/45==19 && posx/45==15 || posy/45==19 && posx/45==14){
            if(contadorText3M1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoNoPuedesPasar, 550, 680, null);
                //canvas.drawBitmap(pensaExclamacio, 825, 180, null);
            }
            if(contadorText3M1==2){
                //canvas.drawBitmap(textoM1_1, 2000, 600, null);
                //canvas.drawBitmap(pensaExclamacio, 2000, 680, null);
            }
        }
        if( posy/45==17 && posx/45==30|| posy/45==17 && posx/45==31){
            if(contadorText7M1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoNoPuedesPasar, 550, 680, null);
            }
        }
        if(posy/45==17 && posx/45==35|| posy/45==17 && posx/45==36){
            if(contadorText8M1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoNoPuedesPasar, 550, 680, null);
            }
        }
        ///////////////////////
        //llave de la fuente
        //y=3 x=5 o y=4 x=5

        if(posy/45==3 && posx/45==5 || posy/45==4 && posx/45==5 || posy/45==2 && posx/45==5){
            //
            if(!hablarConChicoRio){
                if(contadorText4M1==1) {
                    sprites.get(0).caminarPresion(posx, posy);
                    canvas.drawBitmap(textoFuenteM1Op1, 550, 680, null);
                }
                if(contadorText4M1==2){
                    llaveX=2000;
                    llaveY=2000;
                    tieneLaLlave=2;

                }
            }
            if(hablarConChicoRio){
                if(contadorText4M1==1) {
                    sprites.get(0).caminarPresion(posx, posy);
                    canvas.drawBitmap(textoFuenteM1Op2, 550, 680, null);
                }
                if(contadorText4M1==2){
                    tieneLaLlave=2;
                    llaveX=2000;
                    llaveY=2000;
                }
            }
        }
        canvas.drawBitmap(bmpLlave, llaveX, llaveY, null);
        //palancas dos posiciones
        if(posPalanca==1){
            palancaOn=true;
            palanca = BitmapFactory.decodeResource(getResources(), R.drawable.palanca1);
            if(contadorText6M1==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textodespuesPalanca, 550, 680, null);
            }
        }
        canvas.drawBitmap(palanca, 1490, 460, null);

        ///////////////////////////
        //si queiro puedo poner un mensaje al mirar las cajitas del medio
        //columna 24 fila 15
        ///////////////////////////

        //Chico del rio dos mensajes tiene la llave de la palanca
        //2 tiene la llave, 1 no la tiene
        if(tieneLaLlave==2){
            if(posy/45==17 && posx/45==38 || posy/45==17 && posx/45==39){
                if(contadorText5M1==1){
                    sprites.get(0).caminarPresion(posx, posy);
                    canvas.drawBitmap(textoChicoPuenteOp2, 550, 680, null);
                    spriteChicoRio.caminar(1740,930);
                }
            }
        }else if(tieneLaLlave==1){
            if(contadorText9M1==1) {
                if (posy / 45 == 17 && posx / 45 == 38 || posy / 45 == 17 && posx / 45 == 39) {
                    sprites.get(0).caminarPresion(posx, posy);
                    canvas.drawBitmap(textoChicoPuenteOp1, 550, 680, null);
                    hablarConChicoRio=true;
                }
            }
        }
        if(posy / 45 == 17 && posx / 45 == 37 || posy / 45 == 18 && posx / 45 == 37 || posy / 45 == 19 && posx / 45 == 37 || posy / 45 == 20 && posx / 45 == 37 || posy / 45 == 21 && posx / 45 == 37){
            contadorText9M1=1;
        }


        //Que es este mensaje??????????????????????????????????????????????????????? David yo creo que no lo he puesto
        if ((posx>70)&&(posy>630)&&(posx<300)&&(posy<750))
            canvas.drawBitmap(textoM1_1, 2000, 680, null);

        Log.d("POSICION X bueno:", ""+sprites.get(0).getX());

        Log.d("POSICION X malo:", ""+spriteMalo1.x);
        if ((posx>spriteMalo1.x-50)&&(posx<spriteMalo1.x+50)&&(posy>spriteMalo1.y-50)&&(posy<spriteMalo1.y+50)&&patrol1vivo1==true)
        {  patrol1vivo1=false;
            vidaJugador=vidaJugador-25;
        }
        if ((posx>spriteMalo2.x-50)&&(posx<spriteMalo2.x+50)&&(posy>spriteMalo2.y-50)&&(posy<spriteMalo2.y+50)&&patrol1vivo2==true)
        {  patrol1vivo2=false;
            vidaJugador=vidaJugador-25;
        }if ((posx>spriteMalo3.x-50)&&(posx<spriteMalo3.x+50)&&(posy>spriteMalo3.y-50)&&(posy<spriteMalo3.y+50)&&patrol1vivo3==true)
        {  patrol1vivo3=false;
            vidaJugador=vidaJugador-25;
        }
        /*
        if((spriteMalo1.x<50+posx)&&(spriteMalo1.y<50+posy)||(spriteMalo1.x>50+posx)&&(spriteMalo1.y>50+posy)||(spriteMalo1.x<50+posx)&&(spriteMalo1.y>posy+50)||(spriteMalo1.x>50+posx)&&(spriteMalo1.y<posy+50)) {
            patrol1vivo=false;
            if (vida == 100)
                vida = 75;
            else if (vida == 75)
                vida = 50;
            else if (vida == 50)
                vida = 25;
            else if (vida == 25)
                vida = 0;
        }*/

        //////////////Pasar del mapa 1 al 2 animacion de movimiento////////////////
        if(posy/45==5 && posx/45==39 && deMapa1A2) {
            //borro el muñeco y pongo un sprite del tipo malo pero con el personaje principal
            //con la posicion suya que se marcha por el puente
            accionPuente=true;
            pasarAMapa2=true;
        }
//        if(accionPuente){
//            //sprites.get(0).iniciNino(2000,2000);
//            //sprites.get(0).caminarPresion(2000,2000);
//            spritePrincipalPuente.iniciNino(posx,posy);
//            spritePrincipalPuente.caminar(1900,posy);
//            if(spritePrincipalPuente.getX() >= 1900){
//                pasarAMapa2=true;
//            }
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
            canvas.drawBitmap(bmpAgua, 0, y2, null);
            canvas.drawBitmap(bmpAgua, 45, y2, null);
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

        /////////////////////////
        //Boton
        canvas.drawBitmap(botonAccion, 1780, 940, null);


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
        /////////////////////////
        //Boton
        canvas.drawBitmap(botonAccion, 1780, 940, null);

    }
    protected void dibujaMapa4 (Canvas canvas, int height, int width){
        //dibujamos el mapa
        int posx = sprites.get(0).getX();
        int posy = sprites.get(0).getY();
        int matrizX = sprites.get(0).getMatrizX();
        int matrizY = sprites.get(0).getMatrizY();
        //suelo
        for(int y=405;y<=810;y=y+45)
        {
            for(int x=630;x<=1260;x=x+45)
            {
                canvas.drawBitmap(bmpSueloCasa, x, y, null);
            }
        }
        //pared
        for(int y=180;y<=360;y=y+45)
        {
            for(int x=630;x<=1260;x=x+45)
            {
                canvas.drawBitmap(pared, x, y, null);
            }
        }
        canvas.drawBitmap(planta, 850, 775, null);
        canvas.drawBitmap(planta, 1060, 775, null);
        canvas.drawBitmap(alfombra, 910, 795, null);
        canvas.drawBitmap(silla, 1185, 538, null);
        canvas.drawBitmap(silla, 1185, 590, null);
        canvas.drawBitmap(mesa, 1090, 560, null);
        canvas.drawBitmap(libreria, 700, 335, null);
        canvas.drawBitmap(libreria, 1100, 335, null);
        canvas.drawBitmap(logo, 940, 245, null);
        canvas.drawBitmap(luz, 1150, 255, null);
        canvas.drawBitmap(luz, 750, 255, null);
        canvas.drawBitmap(pared_falsa, 630, 560, null);
        canvas.drawBitmap(cama, 630, 660, null);

        if(matrizX==24 && matrizY==8 || matrizX==25 && matrizY==8){
            if(contadorText1M4==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoM4_1, 550, 680, null);
            }

        }else{
            contadorText1M4=1;
        }
        if(matrizX==16 && matrizY==8 || matrizX==15 && matrizY==8) {
            if(contadorText2M4==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoM4_2, 550, 680, null);
            }
        }else{
            contadorText2M4=1;
        }
        if(matrizX==20 && matrizY==8){
            if(contadorText3M4==1) {
                sprites.get(0).caminarPresion(posx, posy);
                canvas.drawBitmap(textoM4_3, 550, 680, null);
            }
        }else{
            contadorText3M4=1;
        }
        if(!haLlegado){
            if(!soloUnaVez){
                sprites.get(0).caminarPresion(945,580);
                soloUnaVez=true;
            }
            if(posx>=945 && posy>=540){
                haLlegado=true;
            }
        }


        if(haLlegado){
            if(matrizX==21 && matrizY==17 || matrizX==21 && matrizY==16 || matrizX==20 && matrizY==17 || matrizX==20 && matrizY==16){
                deMapa4A1=true;
            }
        }

        /////////////////////////
        //Boton
        canvas.drawBitmap(botonAccion, 1780, 940, null);


    }

}
