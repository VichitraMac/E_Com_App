package com.manya.tech.ecomapp.data.repos.product;

import com.manya.tech.ecomapp.data.OnRequestResponseListener;
import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.data.model.product.Product;

import java.util.List;

public interface ProductRepo {
    void fetchProductCategories(OnRequestResponseListener<List<Category>> requestResponseListener);
    void fetchLatestProducts(OnRequestResponseListener<List<Product>> requestResponseListener);
}
