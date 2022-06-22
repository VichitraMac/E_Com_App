package com.manya.tech.ecomapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.manya.tech.ecomapp.R;
import com.manya.tech.ecomapp.data.model.product.Category;
import com.manya.tech.ecomapp.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;

    public CategoryAdapter(List<Category> list) {
        this.categoryList = list;
    }

    public void update(List<Category> list) {
        this.categoryList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        Glide.with(holder.binding.ivCategory.getContext())
                .load(category.image)
                .thumbnail(Glide.with(holder.binding.ivCategory.getContext()).load(R.drawable.loader))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.ivCategory);

        holder.binding.name.setText(category.Name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    protected static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public ItemCategoryBinding binding;

        public CategoryViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
