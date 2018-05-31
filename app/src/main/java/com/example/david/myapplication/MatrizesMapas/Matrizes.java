package com.example.david.myapplication.MatrizesMapas;

import com.example.david.myapplication.Clases.Celda;

public class Matrizes {
    //alto1080-ancho1920
    //1920 son 42,666 cogemos 43
    //para 43 exactos son 1935

    private static final int BMP_ROWS = 24;
    private static final int BMP_COLUMNS = 43;
    private Celda matrizMapa1 [][] = new Celda[24][43];
    private Celda celdaArbusto = new Celda (2);
    private Celda celdaAgua = new Celda(3);
    private Celda celdaHierba = new Celda(0);
    private Celda celdaExtras = new Celda(4);
    private Celda celdaActuar = new Celda(1);

    public Matrizes() {
    }

    public Celda[][] getMatrizMapa1() {
        return matrizMapa1;
    }
    public void setMatrizMapa1(Celda[][] matrizMapa1) {
        this.matrizMapa1 = matrizMapa1;
    }

    public void generarMapa (int mapa){
        //el muñeco solo avanza si es 0 esta puesto en el Sprite funcion update
        if(mapa==1){
            //declaro primero tod el mapa como hierba que significa pisable
            for(int y=0;y<BMP_ROWS;y++){
                for(int x=0;x<BMP_COLUMNS;x++){
                    this.matrizMapa1 [y][x] = this.celdaHierba;
                }
            }
            //declaro el contorno de arbustos y el de agua
            for(int x=0; x<BMP_COLUMNS; x++ ){
                this.matrizMapa1 [0][x] = this.celdaArbusto;
            }
            for(int y=0; y<BMP_ROWS; y++ ){
                this.matrizMapa1 [y][0] = this.celdaArbusto;
            }
            for(int x=0; x<BMP_COLUMNS; x++ ){
                this.matrizMapa1 [23][x] = this.celdaArbusto;
            }
            for(int y=0;y<BMP_ROWS;y++) {
                this.matrizMapa1[y][42] = this.celdaAgua;
                //this.matrizMapa1[y][41] = this.celdaAgua;
            }
            //extras // linea de 5 arbustos
            for(int x=1; x<11; x++ ){
                this.matrizMapa1 [6][x] = this.celdaArbusto;
            }
            //casetas
            for(int x= 28; x<39;x++){
                //this.matrizMapa1 [11][x] = this.celdaExtras;
                this.matrizMapa1 [12][x] = this.celdaExtras;
                this.matrizMapa1 [13][x] = this.celdaExtras;
                this.matrizMapa1 [14][x] = this.celdaExtras;
                this.matrizMapa1 [15][x] = this.celdaExtras;
            }
        }
        if(mapa==2){
            for(int y=0;y<BMP_ROWS;y++){
                for(int x=0;x<BMP_COLUMNS;x++){
                    this.matrizMapa1 [y][x] = this.celdaHierba;
                }
            }
        }
        if(mapa==3){

        }


    }
}
