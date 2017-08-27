package com.luxola.sephora.happyshop.fragments.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luxola.sephora.happyshop.R;
import com.luxola.sephora.happyshop.fragments.ProductsFragment.OnListFragmentInteractionListener;
import com.luxola.sephora.happyshop.models.Product;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by Pradeep on 8/26/2017.
 */

public class ProductItemViewAdapter extends RecyclerView.Adapter<ProductItemViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProductItemViewAdapter(List<Product> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mPriceView.setText("S$" + mValues.get(position).price);
        holder.mSaleView.setText(mValues.get(position).underSale ? "On Sale" : "");

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

        imageLoader.loadImage(mValues.get(position).imgUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.mProductImageView.setImageBitmap(loadedImage);
                holder.loadingStatusTextView.setVisibility(View.INVISIBLE);

            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mPriceView;
        public final TextView mSaleView;
        public final ImageView mProductImageView;
        public final TextView loadingStatusTextView;

        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProductImageView = (ImageView) view.findViewById(R.id.productImage);
            mNameView = (TextView) view.findViewById(R.id.productName);
            mPriceView = (TextView) view.findViewById(R.id.productPrice);
            mSaleView = (TextView) view.findViewById(R.id.productSale);
            loadingStatusTextView = (TextView) view.findViewById(R.id.loadingStatus);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPriceView.getText() + "'";
        }
    }
}
