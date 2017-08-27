package com.luxola.sephora.happyshop.network;

/**
 * Created by Senanayake on 8/22/2017.
 */

public interface NetworkResponseListener {
    public void onSuccess();

    public void onFailure(Throwable t);
}
