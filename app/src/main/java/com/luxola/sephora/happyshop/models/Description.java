package com.luxola.sephora.happyshop.models;

/**
 * Created by Pradeep on 8/22/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("product")
    @Expose
    private Product product;

    public Product getProduct() {
        return product;
    }
}