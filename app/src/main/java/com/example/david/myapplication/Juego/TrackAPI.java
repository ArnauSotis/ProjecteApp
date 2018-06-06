package com.example.david.myapplication.Juego;

import com.example.david.myapplication.Clases.Login;
import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackAPI {

    @GET("json/consultarUsuarioDAO/{user}")
    Call<Usuario> consultarUsuario(@Path("user") String nombre);

    @GET("json/consultarObjetoDAO/{obj}")
    Call<Objeto> consultarObjeto (@Path("obj") int nomObj);

    @POST("json/login")
    Call<Boolean> login (@Body Login login);

    @POST("json/newUser")
    Call<Boolean> register (@Body Usuario user);

    @GET("json/listaObjetosUsuario/{user}")
    Call <List<Objeto>> listaObjetosUser (@Path("user") String usuarios);

    @GET("json/damePosYMapa/{user}")
    Call <Usuario> getPosYmapa (@Path("user") String usuarios);

    @POST("json/ponPosYmapa")
    Call<Boolean> postPosYmapa (@Body Usuario usuario);
}
