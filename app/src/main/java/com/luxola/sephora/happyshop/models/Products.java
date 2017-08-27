package com.luxola.sephora.happyshop.models;

/**
 * Created by Pradeep on 8/21/2017.
 */

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public List<Product> getProducts() {
        return products;
    }
}