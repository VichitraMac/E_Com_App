package com.manya.tech.ecomapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.manya.tech.ecomapp.R;
import com.manya.tech.ecomapp.data.model.product.Product;
import com.manya.tech.ecomapp.databinding.ItemProductBinding;

import java.util.List;

public class LatestProductAdapter extends RecyclerView.Adapter<LatestProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public LatestProductAdapter(List<Product> list) {
        this.productList = list;
    }

    public void update(List<Product> list) {
        this.productList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        Glide.with(holder.binding.image.getContext())
                .load(product.image)
                .thumbnail(Glide.with(holder.binding.image.getContext()).load(R.drawable.loader))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.image);

        holder.binding.name.setText(product.name);
        holder.binding.price.setText("$" + product.price);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    protected static class ProductViewHolder extends RecyclerView.ViewHolder {

        public ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
