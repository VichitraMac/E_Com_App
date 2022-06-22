package com.manya.tech.ecomapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manya.tech.ecomapp.data.source.remote.services.RequestInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "https://62b02562b0a980a2ef4b6737.mockapi.io/";

    @Provides
    @Singleton
    public Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, RequestInterceptor requestInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                // TODO: add NO INTERNET interceptor
                .addInterceptor(requestInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor getLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    public RequestInterceptor provideRequestInterceptor(){
        return new RequestInterceptor();
    }
}
