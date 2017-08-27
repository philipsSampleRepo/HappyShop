package com.luxola.sephora.happyshop.network;

import com.luxola.sephora.happyshop.models.Description;
import com.luxola.sephora.happyshop.models.Product;
import com.luxola.sephora.happyshop.models.Products;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pradeep on 8/21/2017.
 */

public interface NetworkServices {

    @GET("products.json")
    Call<Products> getProducts();

    @GET("products/{id}")
    Call<Description> getDescription(@Path("id") String id);


    //    http://sephora-mobile-takehome-apple.herokuapp.com/api/v1/products?category=Makeup&page=2
    @GET("products")
    Call<Products> getProductsByCategory(@Query("category") String category,
                                        @Query("page") int page);

}