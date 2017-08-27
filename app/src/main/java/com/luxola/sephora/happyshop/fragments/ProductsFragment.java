package com.luxola.sephora.happyshop.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luxola.sephora.happyshop.R;
import com.luxola.sephora.happyshop.fragments.adapters.ProductItemViewAdapter;
import com.luxola.sephora.happyshop.models.Product;
import com.luxola.sephora.happyshop.network.NetworkHandler;
import com.luxola.sephora.happyshop.network.NetworkServices;
import com.luxola.sephora.happyshop.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pradeep on 8/26/2017.
 */
public class ProductsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private String mCategory;
    private List<Product> products = new ArrayList<>();
    private ProductItemViewAdapter viewAdapter;

    int pageIndex = 1;
    private boolean mIsLoading = false;

    public ProductsFragment() {

    }

    public static ProductsFragment newInstance(int columnCount, String category) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mCategory = getArguments().getString("category");
        }

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setMessage(getString(R.string.loading_message));
        dialog.setCancelable(false);
        dialog.show();

        NetworkServices mAPIService = NetworkUtils.getAPIService();
        NetworkHandler.getNetworkHandler().getProductByCategory(getActivity(), mAPIService, mCategory, 1,
                new NetworkHandler.DataCallback.LoadDataCallback<List<Product>>() {
                    @Override
                    public void onDataLoaded(List<Product> data) {
                        products.clear();
                        products.addAll(data);
                        viewAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            final GridLayoutManager layoutManager = new GridLayoutManager(context, mColumnCount);
            recyclerView.setLayoutManager(layoutManager);

            viewAdapter = new ProductItemViewAdapter(products, mListener);
            recyclerView.setAdapter(viewAdapter);

            RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    if (mIsLoading)
                        return;

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        mIsLoading = true;
                        pageIndex++;

                        final ProgressDialog dialog = new ProgressDialog(getActivity());
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setIndeterminate(true);
                        dialog.setMessage(getString(R.string.loading_message));
                        dialog.setCancelable(false);
                        dialog.show();

                        NetworkHandler.getNetworkHandler().getProductByCategory(getActivity(),
                                NetworkUtils.getAPIService(), mCategory, pageIndex,
                                new NetworkHandler.DataCallback.LoadDataCallback<List<Product>>() {

                                    @Override
                                    public void onDataLoaded(List<Product> data) {
                                        products.addAll(data);
                                        mIsLoading = false;

                                        viewAdapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                    }
                                });
                    }
                }
            };
            recyclerView.addOnScrollListener(mScrollListener);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Product item);
    }
}
