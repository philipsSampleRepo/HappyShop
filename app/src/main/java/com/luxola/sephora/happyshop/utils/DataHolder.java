package com.luxola.sephora.happyshop.utils;


import com.luxola.sephora.happyshop.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pradeep on 8/22/2017.
 */

public final class DataHolder {

    private static DataHolder dataHolder = null;

    private DataHolder() {
    }

    public static DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Makeup");
        categories.add("Tools");
        categories.add("Skincare");
        categories.add("Bath & Body");
        categories.add("Nails");
        return categories;
    }
}
