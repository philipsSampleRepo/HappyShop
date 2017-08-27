package com.luxola.sephora.happyshop.utils;

import com.luxola.sephora.happyshop.network.NetworkServices;
import com.luxola.sephora.happyshop.network.RetrofitClient;

import static com.luxola.sephora.happyshop.constants.Constants.BASE_URL;


/**
 * Created by Pradeep on 8/21/2017.
 */
public final class NetworkUtils {

    private NetworkUtils() {
    }

    public static NetworkServices getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(NetworkServices.class);
    }
}
