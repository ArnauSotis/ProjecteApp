package com.example.david.myapplication;

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

    public Usuario(String nombre, String password, int vida, int ataque, int defensa, int resistencia) {
        this.nombre = nombre;
        this.password = password;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
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
