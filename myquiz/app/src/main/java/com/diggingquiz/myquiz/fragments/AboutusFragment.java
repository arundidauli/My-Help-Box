package com.diggingquiz.myquiz.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.base.BaseFragment;


/**
 * Created by Tanuj yadav on 18/03/2018.
 */

public class AboutusFragment extends BaseFragment {

    private Context context;
    TextView textviewheader,tvstate1,tvstate2,tv_cancel,tvtermcondition,tvhowtouse;
    ImageView imageback;
    LinearLayout linerState,tvclassedit;
    EditText editpreferstate;
     Dialog dialog;
    Rect displayRectangle;
    Window window;
    View layout;
    TextView  textviewdialogcencel;
    TextView  textviewdialogsubmit,tvstatement;
    final ViewGroup nullParent = null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        /*getActivity().setTitle("Home");*/
        context = getActivity();
        initViews(view);


               return view;
    }
    private void initViews(View v) {

       // imageback = (ImageView) v.findViewById(R.id.imageback);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {





        }
    }

}
