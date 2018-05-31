package com.example.david.myapplication.Juego;

import com.example.david.myapplication.Clases.Login;
import com.example.david.myapplication.Clases.Objeto;
import com.example.david.myapplication.Clases.Usuario;

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

}
