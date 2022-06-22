package com.manya.tech.ecomapp.data.source.remote.services;

import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.data.model.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("api/secure/v1/postlist")
    Call<List<Product>> getLatestProducts();

    @GET("api/secure/v1/categories")
    Call<List<Category>> getProductCategories();
}
