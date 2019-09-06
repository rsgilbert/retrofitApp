package com.monstercode.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public String API_BASE_URL = "http://names01.herokuapp.com";
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder.client(httpClient.build()).build();


        MyClient client = retrofit.create(MyClient.class);


        // Fetch a the name at id=1
        Call<Name> call = client.getName(1);

        call.enqueue(new Callback<Name>() {
            @Override
            public void onResponse(Call<Name> call, Response<Name> response) {
                Toast.makeText(MainActivity.this, "SUCCESS: got at at index 1: " + response.body().getFirstName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Name> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed, " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to make request", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
