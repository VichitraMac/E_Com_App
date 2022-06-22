package com.manya.tech.ecomapp.ui.home;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.manya.tech.ecomapp.data.OnRequestResponseListener;
import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.data.model.product.Product;
import com.manya.tech.ecomapp.data.repos.product.ProductRepo;
import com.manya.tech.ecomapp.utils.Data;
import com.manya.tech.ecomapp.utils.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragHomeViewModel extends ViewModel {

    private final ProductRepo repo;

    @Inject
    public FragHomeViewModel(SavedStateHandle handle, ProductRepo repo) {
        this.repo = repo;
    }

    private final MutableLiveData<Data<ArrayList<Product>>> _product = new MutableLiveData<>();
    public LiveData<Data<ArrayList<Product>>> product = _product;

    private final MutableLiveData<Data<ArrayList<Category>>> _category = new MutableLiveData<>();
    public LiveData<Data<ArrayList<Category>>> category = _category;


    public void getLatestProducts() {
        _product.postValue(new Data(Status.LOADING));
        repo.fetchLatestProducts(new OnRequestResponseListener<List<Product>>() {
            @Override
            public void onSuccess(List<Product> response) {
                _product.postValue(new Data(Status.SUCCESS, response));
            }

            @Override
            public void onFailed(Throwable e) {
                _product.postValue(new Data(Status.ERROR, new Exception("error")));
            }
        });
    }

    public void getProductCategories() {
        _category.postValue(new Data(Status.LOADING));
        repo.fetchProductCategories(new OnRequestResponseListener<List<Category>>() {
            @Override
            public void onSuccess(List<Category> response) {
                for (int i = 0; i < response.size(); i++) {
                    response.get(i).setImage(generateUniqueUrl(response.get(i).image));
                }
                _category.postValue(new Data(Status.SUCCESS, response));
            }

            @Override
            public void onFailed(Throwable e) {
                _category.postValue(new Data(Status.SUCCESS, new Exception("error")));
            }
        });
    }

    private String generateUniqueUrl(String url) {
        return url + "?temp=" + Math.random();
    }
}
