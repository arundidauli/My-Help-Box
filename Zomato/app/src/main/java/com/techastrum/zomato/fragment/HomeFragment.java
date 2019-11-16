package com.techastrum.zomato.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.techastrum.zomato.R;
import com.techastrum.zomato.adapter.ProductListAdapter;
import com.techastrum.zomato.model.ProductListData;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private ProductListAdapter productListAdapter;
    private ArrayList<ProductListData> productListData;
    private Context context;
    private SwipeRefreshLayout pullToRefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        productListData = new ArrayList<>();
        pullToRefresh = root.findViewById(R.id.swipe_refresh);
        pullToRefresh.setOnRefreshListener(this);
        pullToRefresh.setRefreshing(false);
        pullToRefresh.post(new Runnable() {
                               @Override
                               public void run() {
                                   pullToRefresh.setRefreshing(true);
                                   refreshData();
                               }
                           }
        );
        recyclerView = root.findViewById(R.id.productListData);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(context);
        layoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager4);
        productListAdapter = new ProductListAdapter(context, productListData);
        recyclerView.setAdapter(productListAdapter);
        data();

        return root;
    }

    private void refreshData() {

        pullToRefresh.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pullToRefresh.setRefreshing(false);

            }
        }, 2000);

    }


    @Override
    public void onRefresh() {
        refreshData();

    }

    private void snackBar(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void data() {
        productListData.add(new ProductListData("Pizza", "314"));
        productListData.add(new ProductListData("Pizza", "193"));
        productListData.add(new ProductListData("Pizza", "314"));
        productListData.add(new ProductListData("Pizza", "213"));


    }
}