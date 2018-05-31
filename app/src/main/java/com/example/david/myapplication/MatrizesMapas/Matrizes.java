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

    public Matrizes() {
    }

    public Celda[][] getMatrizMapa1() {
        return matrizMapa1;
    }
    public void setMatrizMapa1(Celda[][] matrizMapa1) {
        this.matrizMapa1 = matrizMapa1;
    }

    public void generarMapa (int mapa){
        if(mapa==1){
            for(int y=0;y<BMP_ROWS;y++){
                for(int x=0;x<BMP_COLUMNS;x++){
                    this.matrizMapa1 [y][x] = this.celdaHierba;
                }
            }
            for(int x=0; x<BMP_COLUMNS; x++ ){
                this.matrizMapa1 [0][x] = this.celdaArbusto;
            }
            for(int x=1; x<10; x++ ){
                this.matrizMapa1 [7][x] = this.celdaArbusto;
            }
            for(int y=0; y<BMP_ROWS; y++ ){
                this.matrizMapa1 [y][0] = this.celdaArbusto;
            }
            for(int x=0; x<BMP_COLUMNS; x++ ){
                this.matrizMapa1 [23][x] = this.celdaArbusto;
            }
            for(int y=0;y<BMP_ROWS;y++) {
                this.matrizMapa1[y][42] = this.celdaAgua;
                this.matrizMapa1[y][41] = this.celdaAgua;
            }
        }
        if(mapa==2){

        }
        if(mapa==3){

        }


    }
}
