package com.techastrum.zomato.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.techastrum.zomato.R;


public class SearchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private Context context;
    private SwipeRefreshLayout pullToRefresh;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        context = getActivity();
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
}