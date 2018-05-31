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
    private Celda celdaActuar = new Celda(1);

    public Celda[][] getMatrizMapa1() {
        return matrizMapa1;
    }
    public void setMatrizMapa1(Celda[][] matrizMapa1) {
        this.matrizMapa1 = matrizMapa1;
    }

    public Celda[][] generarMapa1 (){
        for(int y=45;y<1035;y=y+45){
            for(int x=45;x<1830;x=x+45){
                this.matrizMapa1 [x][y] = this.celdaHierba;
            }
        }
        for(int x=0; x<1935; x=x+45 ){
            this.matrizMapa1 [x][0] = this.celdaArbusto;
        }
        for(int x=45; x<450; x=x+45 ){
            this.matrizMapa1 [x][315] = this.celdaArbusto;
        }
        for(int y=0; y<1080; y=y+45 ){
            this.matrizMapa1 [0][y] = this.celdaArbusto;
        }
        for(int x=0; x<1935; x=x+45 ){
            this.matrizMapa1 [x][1035] = this.celdaArbusto;
        }
        for(int y=0;y<1080;y=y+45){
            this.matrizMapa1 [1875][y] = this.celdaAgua;
            this.matrizMapa1 [1830][y] = this.celdaAgua;
        }

        return matrizMapa1;
    }
}
