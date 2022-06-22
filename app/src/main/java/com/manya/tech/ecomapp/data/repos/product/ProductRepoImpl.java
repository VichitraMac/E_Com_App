package com.manya.tech.ecomapp.data.repos.product;

import com.manya.tech.ecomapp.data.OnRequestResponseListener;
import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.data.model.product.Product;
import com.manya.tech.ecomapp.data.source.remote.services.ProductService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepoImpl implements ProductRepo {

    private final ProductService remoteProductService;

    @Inject
    public ProductRepoImpl(ProductService productService){
        this.remoteProductService = productService;
    }

    @Override
    public void fetchProductCategories(OnRequestResponseListener<List<Category>> requestResponseListener) {
        remoteProductService.getProductCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                requestResponseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                requestResponseListener.onFailed(t);
            }
        });
    }

    @Override
    public void fetchLatestProducts(OnRequestResponseListener<List<Product>> requestResponseListener) {
        remoteProductService.getLatestProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                requestResponseListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                requestResponseListener.onFailed(t);
            }
        });
    }
}
