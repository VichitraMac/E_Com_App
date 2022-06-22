package com.manya.tech.ecomapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.data.model.product.Product;
import com.manya.tech.ecomapp.databinding.LayoutDashboardBinding;
import com.manya.tech.ecomapp.ui.home.adapter.CategoryAdapter;
import com.manya.tech.ecomapp.ui.home.adapter.LatestProductAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragHome extends Fragment {

    private LayoutDashboardBinding binding;

    private FragHomeViewModel viewModel;

    private CategoryAdapter categoryAdapter;

    private LatestProductAdapter latestProductAdapter;

    private List<Product> productList;

    private List<Category> categoryList;


    public static FragHome newInstance() {
        return new FragHome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FragHomeViewModel.class);

        initRecycler();

        setupObservers();

        fetchData();
    }

    private void fetchData() {
        viewModel.getLatestProducts();
        viewModel.getProductCategories();
    }

    private void initRecycler() {
        List<Category> categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryList);
        binding.rcCategory.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.rcCategory.setAdapter(categoryAdapter);

        List<Product> productList = new ArrayList<>();
        latestProductAdapter = new LatestProductAdapter(productList);
        binding.rcNewProduct.setLayoutManager(new GridLayoutManager(requireContext(),2));
        binding.rcNewProduct.setAdapter(latestProductAdapter);
    }

    private void setupObservers() {
        viewModel.product.observe(getViewLifecycleOwner(), data -> {
            switch (data.getStatus()) {
                case LOADING:
                    showLoader(true);
                    binding.tvTitleLatest.setVisibility(View.GONE);
                    break;

                case SUCCESS:
                    showLoader(false);
                    if (data.getData() != null){
                        productList = data.getData();
                        latestProductAdapter.update(productList);
                        binding.tvTitleLatest.setVisibility(View.VISIBLE);
                    }
                    break;

                case ERROR:
                    showLoader(false);
                    showToast("Problem while loading latest product");
                    break;
            }
        });

        viewModel.category.observe(getViewLifecycleOwner(), data ->{
            switch (data.getStatus()) {
                case LOADING:
                    binding.tvTitleCategories.setVisibility(View.GONE);
                    showLoader(true);
                    break;

                case SUCCESS:
                    showLoader(false);
                    if (data.getData() != null){
                        categoryList = data.getData();
                        categoryAdapter.update(categoryList);
                        binding.tvTitleCategories.setVisibility(View.VISIBLE);
                    }
                    break;

                case ERROR:
                    showLoader(false);
                    showToast("Problem while loading categories");
                    break;
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showLoader(boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
