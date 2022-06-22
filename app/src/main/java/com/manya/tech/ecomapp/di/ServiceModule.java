package com.manya.tech.ecomapp.di;


import com.manya.tech.ecomapp.data.source.remote.services.ProductService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class ServiceModule {

    @Provides
    @Singleton
    public ProductService providesUsersService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

}
