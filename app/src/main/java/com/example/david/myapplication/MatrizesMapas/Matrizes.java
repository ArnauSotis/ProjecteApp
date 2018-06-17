package com.example.david.myapplication.MatrizesMapas;

import com.example.david.myapplication.Clases.Celda;

public class Matrizes {
    //alto1080-ancho1920
    //1920 son 42,666 cogemos 43
    //para 43 exactos son 1935
    private static final int BMP_ROWS = 24;
    private static final int BMP_COLUMNS = 43;
    private Celda matrizMapa1 [][] = new Celda[24][43];
    private Celda matrizMapa2 [][] = new Celda[24][43];
    private Celda matrizMapa3 [][] = new Celda[24][43];
    private Celda matrizMapa4 [][] = new Celda[24][43];
    private Celda celdaArbusto = new Celda (2);
    private Celda celdaAgua = new Celda(3);
    private Celda celdaHierba = new Celda(0);
    private Celda celdaExtras = new Celda(4);
    private Celda celdaActuar = new Celda(1);
    private Celda celdaInteractuar = new Celda(5);

    public Matrizes() {
    }

    public Celda[][] getMatrizMapa1() {
        return matrizMapa1;
    }
    public void setMatrizMapa1(Celda[][] matrizMapa1) {
        this.matrizMapa1 = matrizMapa1;
    }
    public Celda[][] getMatrizMapa2() {
        return matrizMapa2;
    }
    public void setMatrizMapa2(Celda[][] matrizMapa2) {
        this.matrizMapa2 = matrizMapa2;
    }
    public Celda[][] getMatrizMapa3() {
        return matrizMapa3;
    }
    public void setMatrizMapa3(Celda[][] matrizMapa3) {
        this.matrizMapa3 = matrizMapa3;
    }
    public Celda[][] getMatrizMapa4() {
        return matrizMapa4;
    }
    public void setMatrizMapa4(Celda[][] matrizMapa3) {
        this.matrizMapa4 = matrizMapa4;
    }


    public void generarMapa1 (){
        //el muñeco solo avanza si es 0 esta puesto en el Sprite funcion update
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
                this.matrizMapa1 [22][x] = this.celdaArbusto;
            }
            for(int y=0;y<BMP_ROWS;y++) {
                    this.matrizMapa1[y][42] = this.celdaAgua;
                    this.matrizMapa1[y][41] = this.celdaAgua;
                    this.matrizMapa1[y][40] = this.celdaAgua;
            }
            ///////segunda manera de hacer lo del puente
//            for(int y=0;y<6;y++) {
//                    this.matrizMapa1[y][42] = this.celdaAgua;
//                    this.matrizMapa1[y][41] = this.celdaAgua;
//                    this.matrizMapa1[y][40] = this.celdaAgua;
//            }
//            //puente
//            for(int x=39; x<43; x++ ){
//                this.matrizMapa1 [6][x] = this.celdaHierba;
//                this.matrizMapa1 [7][x] = this.celdaHierba;
//            }
//
//            for(int y=8;y<BMP_ROWS;y++) {
//                this.matrizMapa1[y][42] = this.celdaAgua;
//                this.matrizMapa1[y][41] = this.celdaAgua;
//                this.matrizMapa1[y][40] = this.celdaAgua;
//            }
            //1
            for(int x=0; x<6; x++ ){
                this.matrizMapa1 [19][x] = this.celdaArbusto;
            }
            //2
            for(int y=14; y<22; y++ ){
                this.matrizMapa1 [y][12] = this.celdaArbusto;
            }
            //3
            for(int x=6; x<12; x++ ){
                this.matrizMapa1 [14][x] = this.celdaArbusto;
            }
            //4
            for(int y=10; y<15; y++ ){
                this.matrizMapa1 [y][5] = this.celdaArbusto;
                this.matrizMapa1 [y][6] = this.celdaArbusto;
            }
            //5
            for(int x=1; x<11; x++ ){
                this.matrizMapa1 [6][x] = this.celdaArbusto;
            }
            //6
            this.matrizMapa1 [12][2] = this.celdaArbusto;
            this.matrizMapa1 [12][1] = this.celdaArbusto;
            this.matrizMapa1 [13][2] = this.celdaArbusto;
            this.matrizMapa1 [13][1] = this.celdaArbusto;
            //personatje sota el 6
            this.matrizMapa1 [14][2] = this.celdaArbusto;
            this.matrizMapa1 [14][1] = this.celdaArbusto;
            //7
            for(int x=6; x<10; x++ ){
                this.matrizMapa1 [10][x] = this.celdaArbusto;
            }
            //cofre sota el 7
            //declaro las tres casillas contiguas como interactuables
            this.matrizMapa1 [11][7] = this.celdaInteractuar;
            this.matrizMapa1 [12][7] = this.celdaInteractuar;
            //this.matrizMapa1 [11][8] = this.celdaInteractuar;
            //this.matrizMapa1 [12][8] = this.celdaInteractuar;
            //8 no crec que cuadri
            for(int x=22; x<28; x++ ){
                this.matrizMapa1 [14][x] = this.celdaArbusto;
            }
            //9
            for(int x=13; x<17; x++ ){
                this.matrizMapa1 [15][x] = this.celdaArbusto;
            }
            //10 casa
            for(int x= 13; x<17;x++){
                this.matrizMapa1 [14][x] = this.celdaExtras;
                this.matrizMapa1 [15][x] = this.celdaExtras;
                this.matrizMapa1 [16][x] = this.celdaExtras;
                this.matrizMapa1 [17][x] = this.celdaExtras;
                this.matrizMapa1 [18][x] = this.celdaExtras;
            }
            //caja-normal debajo de la casa
            this.matrizMapa1 [21][13] = this.celdaExtras;

            //15
            for(int y=1; y<7; y++ ){
                this.matrizMapa1 [y][16] = this.celdaArbusto;
            }
            //16 casa
            for(int x= 17; x<23;x++){
                this.matrizMapa1 [1][x] = this.celdaExtras;
                this.matrizMapa1 [2][x] = this.celdaExtras;
                this.matrizMapa1 [3][x] = this.celdaExtras;
            }
            //17
            this.matrizMapa1 [11][16] = this.celdaArbusto;
            this.matrizMapa1 [10][16] = this.celdaArbusto;
            this.matrizMapa1 [9][16] = this.celdaArbusto;

            //18
            for(int y=6; y<11; y++ ){
                this.matrizMapa1 [y][31] = this.celdaArbusto;
            }
            //19
            for(int x= 32; x<41;x++){
                this.matrizMapa1 [8][x] = this.celdaArbusto;
            }
            //palanca
            this.matrizMapa1[9][33] = this.celdaArbusto;

            //casetas 13 14 he intent fr 11 y 12
            for(int x= 27; x<38;x++){
                this.matrizMapa1 [11][x] = this.celdaExtras;
                this.matrizMapa1 [12][x] = this.celdaExtras;
                this.matrizMapa1 [13][x] = this.celdaExtras;
                this.matrizMapa1 [14][x] = this.celdaExtras;
                this.matrizMapa1 [15][x] = this.celdaExtras;
                this.matrizMapa1 [16][x] = this.celdaExtras;
            }
            //21
            for(int y=1;y<5;y++) {
                for (int x = 1; x < 4; x++) {
                    this.matrizMapa1[y][x] = this.celdaArbusto;
                }
            }
            for (int x = 1; x < 6; x++) {
                this.matrizMapa1[5][x] = this.celdaArbusto;
            }
            //llave 1 mapa 1
            this.matrizMapa1[2][4] = this.celdaExtras;
            this.matrizMapa1[3][4] = this.celdaExtras;
            //puente
//            this.matrizMapa1[4][38] = this.celdaHierba;
//            this.matrizMapa1[4][41] = this.celdaHierba;
//            this.matrizMapa1[4][40] = this.celdaHierba;
//            this.matrizMapa1[4][39] = this.celdaHierba;
//        this.matrizMapa1[5][41] = this.celdaHierba;
//        this.matrizMapa1[5][40] = this.celdaHierba;
//        this.matrizMapa1[5][39] = this.celdaHierba;
//        this.matrizMapa1[5][38] = this.celdaHierba;

    }
    public void generarMapa2(){

        for(int y=0;y<BMP_ROWS;y++){
            for(int x=0;x<BMP_COLUMNS;x++){
                this.matrizMapa2 [y][x] = this.celdaHierba;
            }
        }
        //Limite superior
        for(int x=0; x<BMP_COLUMNS; x++ ){
            this.matrizMapa2 [0][x] = this.celdaArbusto;
        }
        //Valla horizontal
        for(int x=3; x<BMP_COLUMNS-6; x++ ) {
            this.matrizMapa2[3][x] = this.celdaArbusto;
        }


        //Limite derecho
        for(int y=0; y<BMP_ROWS; y++ ){
            this.matrizMapa2 [y][BMP_COLUMNS-2] = this.celdaArbusto;
        }

        //Limite inferior
        for(int x=0; x<BMP_COLUMNS; x++ ){
           if((x>8)||(x<6))
               this.matrizMapa2 [BMP_ROWS-2][x] = this.celdaArbusto;
        }
        //Agua de la izquierda
        for(int y=3; y<BMP_ROWS; y++ ){
            this.matrizMapa2 [y][1] = this.celdaAgua;
        }
        //Casas
        for(int x=3; x<BMP_COLUMNS-6; x++ ) {
            this.matrizMapa2[6][x] = this.celdaArbusto;
        }
        //Casas pequeñas
        for(int x=17; x<19; x++ ) {
            this.matrizMapa2[14][x] = this.celdaArbusto;
            this.matrizMapa2[13][x] = this.celdaArbusto;
        }
        for(int x=19; x<21; x++ ) {
            this.matrizMapa2[17][x] = this.celdaArbusto;
            this.matrizMapa2[18][x] = this.celdaArbusto;
        }

        //Valla vertical
        for(int y=3; y<BMP_ROWS-6; y++ ){
            this.matrizMapa2 [y][BMP_COLUMNS-6] = this.celdaArbusto;
        }



        /*
        for(int x=0; x<BMP_COLUMNS; x++ ){
            this.matrizMapa1 [BMP_ROWS][x] = this.celdaArbusto;
        }
        for(int y=0; y<BMP_ROWS; y++ ){
            this.matrizMapa1 [y][BMP_COLUMNS] = this.celdaArbusto;
        }*/

        /*
        for(int x=0;x<BMP_COLUMNS;x=x++){
            for(int y=0;y<45;y++)
            this.matrizMapa2 [y][x] = this.celdaHierba;
        }

*/


    }
    public void generarMapa3(){

        for(int y=0;y<BMP_ROWS;y++){
            for(int x=0;x<BMP_COLUMNS;x++){
                this.matrizMapa3 [y][x] = this.celdaHierba;
            }
        }
        //declaro el contorno de arbustos y el de agua
        for(int x=0; x<BMP_COLUMNS; x++ ){
            if(x!=1&&x!=2&&x!=3){
                this.matrizMapa3 [0][x] = this.celdaArbusto;
            }
        }
        for(int y=0; y<BMP_ROWS; y++ ){
            this.matrizMapa3 [y][0] = this.celdaArbusto;
        }
        for(int x=0; x<BMP_COLUMNS; x++ ){
            this.matrizMapa3 [22][x] = this.celdaArbusto;
        }
        for(int y=0; y<BMP_ROWS; y++ ){
            this.matrizMapa3 [y][41] = this.celdaArbusto;
        }



        //arbustos por el camino mas granja vertical
        for(int y=1; y<15; y++ ){
            this.matrizMapa3 [y][7] = this.celdaArbusto;
            this.matrizMapa3 [y][6] = this.celdaArbusto;
        }
        for(int y=1; y<10; y++ ){
            this.matrizMapa3 [y][5] = this.celdaArbusto;
        }
        //arbusto y casa
        for(int x=8; x<14; x++ ){
            this.matrizMapa3 [8][x] = this.celdaArbusto;
            this.matrizMapa3 [9][x] = this.celdaArbusto;
            this.matrizMapa3 [10][x] = this.celdaArbusto;
            this.matrizMapa3 [11][x] = this.celdaArbusto;
        }
        //lago
        for(int y=0; y<17; y++ ){
            this.matrizMapa3 [y][21] = this.celdaAgua;
            this.matrizMapa3 [y][22] = this.celdaAgua;
            this.matrizMapa3 [y][23] = this.celdaAgua;
        }
        for(int y=0; y<17; y++ ){
            this.matrizMapa3 [y][41] = this.celdaAgua;
            this.matrizMapa3 [y][40] = this.celdaAgua;
        }
        for(int x=24; x<31; x++ ){
            this.matrizMapa3 [14][x] = this.celdaAgua;
            this.matrizMapa3 [15][x] = this.celdaAgua;
            this.matrizMapa3 [16][x] = this.celdaAgua;
        }
        for(int x=33; x<BMP_COLUMNS; x++ ){
            this.matrizMapa3 [14][x] = this.celdaAgua;
            this.matrizMapa3 [15][x] = this.celdaAgua;
            this.matrizMapa3 [16][x] = this.celdaAgua;
        }
        //torre
        for(int y=0; y<8; y++ ){
            this.matrizMapa3 [y][30] = this.celdaArbusto;
            this.matrizMapa3 [y][31] = this.celdaArbusto;
            this.matrizMapa3 [y][32] = this.celdaArbusto;
        }

        //huerto y muñeco
        this.matrizMapa3 [2][16] = this.celdaArbusto;
        this.matrizMapa3 [2][17] = this.celdaArbusto;
        this.matrizMapa3 [2][18] = this.celdaArbusto;

        //granja
        for(int y=0; y<11; y++ ){
            this.matrizMapa3 [y][10] = this.celdaArbusto;
            this.matrizMapa3 [y][9] = this.celdaArbusto;
            this.matrizMapa3 [y][8] = this.celdaArbusto;
        }

        //arbustos mas la casita
        for(int y=16; y<BMP_ROWS; y++ ){
            this.matrizMapa3 [y][11] = this.celdaArbusto;
        }
        for(int x=12; x<17; x++ ){
            this.matrizMapa3 [16][x] = this.celdaArbusto;
        }
        for(int y=17; y<21; y++ ){
            this.matrizMapa3 [y][13] = this.celdaArbusto;
            if(y!=20 && y!=19){
                this.matrizMapa3 [y][14] = this.celdaArbusto;
            }
        }

        //granja abajo
        for(int x=1; x<11; x++ ){
            this.matrizMapa3 [19][x] = this.celdaArbusto;
            this.matrizMapa3 [20][x] = this.celdaArbusto;
            this.matrizMapa3 [21][x] = this.celdaArbusto;
        }
        //cofre x=1215, 270
        this.matrizMapa3 [6][27] = this.celdaArbusto;
        this.matrizMapa3 [6][28] = this.celdaArbusto;
        this.matrizMapa3 [6][26] = this.celdaArbusto;





    }
    public void generarMapa4(){
        for(int y=7;y<18;y++){
            for(int x=14;x<29;x++){
                this.matrizMapa4 [y][x] = this.celdaHierba;
            }
        }
        for (int y=7;y<18;y++){
            this.matrizMapa4 [y][13] = this.celdaArbusto;
        }
        for (int y=7;y<18;y++){
            this.matrizMapa4 [y][28] = this.celdaArbusto;
        }
        for (int x=14;x<29;x++){
            this.matrizMapa4 [7][x] = this.celdaArbusto;
        }
        for (int x=14;x<29;x++){
            this.matrizMapa4 [18][x] = this.celdaArbusto;
        }
        this.matrizMapa4 [13][24] = this.celdaArbusto;
        this.matrizMapa4 [13][25] = this.celdaArbusto;
        this.matrizMapa4 [12][24] = this.celdaArbusto;
        this.matrizMapa4 [12][25] = this.celdaArbusto;
        this.matrizMapa4 [16][18] = this.celdaArbusto;
        this.matrizMapa4 [16][22] = this.celdaArbusto;
        this.matrizMapa4 [14][15] = this.celdaArbusto;
        this.matrizMapa4 [13][15] = this.celdaArbusto;
        this.matrizMapa4 [12][15] = this.celdaArbusto;

    }

//    public void modMapa1(){
//        this.matrizMapa1[5][42] = this.celdaHierba;
//        this.matrizMapa1[5][41] = this.celdaHierba;
//        this.matrizMapa1[5][40] = this.celdaHierba;
//        this.matrizMapa1[5][39] = this.celdaHierba;
//    }

}
