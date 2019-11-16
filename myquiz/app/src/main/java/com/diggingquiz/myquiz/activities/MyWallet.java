package com.diggingquiz.myquiz.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.adapter.WalletHistoryAdapter;
import com.diggingquiz.myquiz.base.BaseActivity;


public class MyWallet extends BaseActivity {

 Context context;

EditText edit_username,editEmail,edit_mobile;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MyWallet.this;



        setContentView(R.layout.activity_wallet);

        init();



        //int color = Color.parseColor(Color.parseColor("#AA3456"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    private void init() {



        recyclerView=findViewById(R.id.recylerViewHistory);


        LinearLayoutManager layoutManager5 = new LinearLayoutManager(context);
        layoutManager5.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager5);
        adapter = new WalletHistoryAdapter(context);
        recyclerView.setAdapter(adapter);


    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {


    }
    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "New to Goldurs ?  ");
        spanTxt.append("SIGN UP");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
              // startActivity(new Intent(context, Main2Activity.class));
               // overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.statusbar));
            }
        }, spanTxt.length() - "SIGN UP".length(), spanTxt.length(), 0);
        //spanTxt.append(" and");
        //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        //spanTxt.append(" Privacy policy");
       /* spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(context, PrivacyPolicy.class));
                overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.statusbar));
            }
        }, spanTxt.length() - " Privacy Policy".length(), spanTxt.length(), 0);*/
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {


        return super.onTouch(v, event);
    }
    private void enableFocusOfEditTexts() {
        edit_username.setFocusable(true);
        edit_username.setFocusableInTouchMode(true);
        edit_username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        editEmail.setFocusable(true);
        editEmail.setFocusableInTouchMode(true);

        edit_mobile.setFocusable(true);
        edit_mobile.setFocusableInTouchMode(true);


    }
}
