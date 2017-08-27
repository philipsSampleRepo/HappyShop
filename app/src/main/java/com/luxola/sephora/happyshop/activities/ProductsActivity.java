package com.luxola.sephora.happyshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.common.base.Strings;
import com.luxola.sephora.happyshop.R;
import com.luxola.sephora.happyshop.fragments.ProductsFragment;
import com.luxola.sephora.happyshop.models.Product;


/**
 * Created by Pradeep on 8/26/2017.
 */
public class ProductsActivity extends AppCompatActivity implements ProductsFragment.OnListFragmentInteractionListener {

    private int requestCode = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        String category = getIntent().getStringExtra("category");
        if (!Strings.isNullOrEmpty(category)) {
            getSupportActionBar().setTitle(category);
        }

        Log.d("Products", "Category name : " + category);

        ProductsFragment fragment = ProductsFragment.newInstance(2, category);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onListFragmentInteraction(Product item) {
        Log.d("Products", item.toString());

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product", item);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                String category = getIntent().getStringExtra("category");
                getSupportActionBar().setTitle(category);
            }
        }
    }

}
