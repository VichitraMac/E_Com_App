package com.manya.tech.ecomapp.data.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("productname")
    @Expose
    public String name;

    @SerializedName("image")
    @Expose
    public String image;

    @SerializedName("Description")
    @Expose
    public String description;

    @SerializedName("category")
    @Expose
    public String category;

    @SerializedName("price")
    @Expose
    public String price;

    @SerializedName("id")
    @Expose
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
