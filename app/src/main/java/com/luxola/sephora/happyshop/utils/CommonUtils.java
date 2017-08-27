package com.luxola.sephora.happyshop.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.base.Strings;
import com.luxola.sephora.happyshop.logger.CommonLogger;
import com.luxola.sephora.happyshop.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pradeep on 8/22/2017.
 */

public final class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    public static String getProductDescriptionPath(int id) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(id));
        return builder.toString();
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            CommonLogger.e(TAG, e.getMessage());
            return connected;
        }
    }
}
