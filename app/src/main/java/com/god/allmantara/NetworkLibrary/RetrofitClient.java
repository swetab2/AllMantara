package com.god.allmantara.NetworkLibrary;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    public static String BaseUrl = "http://www.fonocashpro.com/";

    //public static String BaseUrl = "http://127.0.0.1/";


    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private Context mContext;


/*    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();*/



    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
           // .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS);


    OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new NetworkConnectionInterceptor(mContext));
    // Adding NetworkConnectionInterceptor with okHttpClientBuilder.

            //oktHttpClient.addInterceptor(logging)





    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(oktHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {

        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }


    public Api getApi() {

        return retrofit.create(Api.class);
    }

}
