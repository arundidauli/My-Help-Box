package com.netkosh.orakart.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netkosh.orakart.R;

public class Homeworkfragment extends Fragment {
    private static final String TAG= Homeworkfragment.class.getSimpleName();
    private Context context;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homework, container, false);
        context = getActivity();
        initViews(view);
        return view;
    }

    private void initViews(View view) {

    }

}