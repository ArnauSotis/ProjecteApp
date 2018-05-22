package com.example.david.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
