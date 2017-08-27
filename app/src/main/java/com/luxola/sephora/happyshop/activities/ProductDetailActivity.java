package com.luxola.sephora.happyshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luxola.sephora.happyshop.R;
import com.luxola.sephora.happyshop.models.Product;
import com.luxola.sephora.happyshop.network.NetworkHandler;
import com.luxola.sephora.happyshop.utils.NetworkUtils;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by Pradeep on 8/26/2017.
 */
public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView saleTextView;
    private TextView descriptionTextView;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        product = getIntent().getParcelableExtra("product");
        Log.d("Product", "Product : " + product);
        getSupportActionBar().setTitle(product.name);

        nameTextView = (TextView) findViewById(R.id.productName);
        imageView = (ImageView) findViewById(R.id.productImageView);
        priceTextView = (TextView) findViewById(R.id.productPrice);
        saleTextView = (TextView) findViewById(R.id.productSale);
        descriptionTextView = (TextView) findViewById(R.id.productDescription);

        nameTextView.setText(product.name);

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(product.imgUrl, imageView);

        priceTextView.setText("S$" + product.price);
        saleTextView.setText(product.underSale ? "On Sale" : "");
        descriptionTextView.setText("");

        NetworkHandler.getNetworkHandler().getProductDetail(getApplicationContext(), NetworkUtils.getAPIService(), product.id,
                new NetworkHandler.DataCallback.LoadDataCallback<Product>() {

                    @Override
                    public void onDataLoaded(Product data) {
                        descriptionTextView.setText(data.description);
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void addCartClicked(View view) {
        Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("category", product.category);
        setResult(Activity.RESULT_OK, data);
        finish();
        super.onBackPressed();
    }
}
