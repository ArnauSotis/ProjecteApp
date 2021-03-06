package com.example.david.myapplication.Clases;

import com.example.david.myapplication.Clases.Objeto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Usuario implements Serializable {

    @SerializedName("nombre")
    @Expose
    private  String nombre;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("vida")
    @Expose
    private int vida;

    @SerializedName("ataque")
    @Expose
    private int ataque;

    @SerializedName("defensa")
    @Expose
    private int defensa;

    @SerializedName("resistencia")
    @Expose
    private int resistencia;

    @SerializedName("money")
    @Expose
    private int money;

    @SerializedName("posX")
    @Expose
    private int posX;

    @SerializedName("posY")
    @Expose
    private int posY;

    @SerializedName("mapaActual")
    @Expose
    private int mapaActual;

    public Usuario(String nombre, String password, int vida, int ataque, int defensa, int resistencia, int money, int posX, int posY, int mapaActual) {
        this.nombre = nombre;
        this.password = password;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
        this.money = money;
        this.posX = posX;
        this.posY = posY;
        this.mapaActual = mapaActual;
    }
    public Usuario(String nombre, int vida, int posx, int posy, int mapa ) {
        this.nombre = nombre;
        this.vida = vida;
        this.posX = posx;
        this.posY = posy;
        this.mapaActual = mapa;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", vida='" + vida + '\'' +
                ", ataque='" + ataque + '\'' +
                ", defensa='" + defensa + '\'' +
                ", resistencia='" + resistencia + '\'' +
                '}';
    }

    private List<Objeto> objetoList = new LinkedList<Objeto>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
}
