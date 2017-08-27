package com.luxola.sephora.happyshop.network;

import android.content.Context;

import com.luxola.sephora.happyshop.models.Description;
import com.luxola.sephora.happyshop.models.Products;
import com.luxola.sephora.happyshop.utils.CommonUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Senanayake on 8/22/2017.
 */

public final class NetworkHandler {

    private static NetworkHandler networkHandler = null;

    public static NetworkHandler getNetworkHandler() {
        if (networkHandler == null) {
            networkHandler = new NetworkHandler();
        }
        return networkHandler;
    }

    public interface DataCallback {

        interface LoadDataCallback<T> {
            void onDataLoaded(T data);
        }
    }

    public void getProductByCategory(Context context, NetworkServices services,
                                     String category, int page,
                                     final DataCallback.LoadDataCallback callback) {

        if (!CommonUtils.isNetworkAvailable(context)) {
            throw new RuntimeException("No internet");
        }

        Call<Products> call = services.getProductsByCategory(category, page);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response != null && response.isSuccessful() && !response.body().getProducts().isEmpty()) {
                    callback.onDataLoaded(response.body().getProducts());
                } else {
                    callback.onDataLoaded(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                callback.onDataLoaded(null);
            }
        });
    }

    public void getProductDetail(Context context, NetworkServices services, int id, final DataCallback.LoadDataCallback callback) {

        if (!CommonUtils.isNetworkAvailable(context)) {
            throw new RuntimeException("No internet");
        }

        Call<Description> call = services.getDescription(CommonUtils.getProductDescriptionPath(id));
        call.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                if (response != null && response.isSuccessful() && response.body().getProduct() != null) {
                    callback.onDataLoaded(response.body().getProduct());
                } else {
                    callback.onDataLoaded(null);
                }
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                callback.onDataLoaded(null);
            }
        });
    }

}


