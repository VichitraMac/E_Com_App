package com.manya.tech.ecomapp.di;

import com.manya.tech.ecomapp.data.repos.product.ProductRepo;
import com.manya.tech.ecomapp.data.repos.product.ProductRepoImpl;
import com.manya.tech.ecomapp.data.source.remote.services.ProductService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {

    @Singleton
    @Provides
    public ProductRepo providesUsersRepo(ProductService service) {
        return new ProductRepoImpl(service);
    }
}
