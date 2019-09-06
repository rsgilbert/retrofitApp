package com.monstercode.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyClient {

    @GET("/names/{nameId}")
    Call<Name> getName (
        @Path("nameId") int nameId // parameter
    );

    @GET("/names/")
    Call<List<Name>> getNames();

}
