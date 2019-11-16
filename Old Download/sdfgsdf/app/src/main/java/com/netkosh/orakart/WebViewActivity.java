package com.netkosh.orakart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


public class WebViewActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Context mContext=this;
    private WebView wb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressDialog = new ProgressDialog(mContext, R.style.Custom);
        wb=findViewById(R.id.webview);
        WebViewer("https://www.netkosh.com");

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    WebViewer("https://www.netkosh.com");
                    return true;
                case R.id.shooping:
                    WebViewer("https://www.eguide.netkosh.com/category/shopping");
                    return true;
            /*    case R.id.home:
                    WebViewer("https://www.eguide.netkosh.com/category/digitek/");
                    return true;*/
                case R.id.entertain:
                    WebViewer("https://www.eguide.netkosh.com/category/entertain/");
                    return true;
                case R.id.jobs:
                    WebViewer("https://www.eguide.netkosh.com/category/jobs/");
                    return true;
                case R.id.news:
                    WebViewer("https://www.eguide.netkosh.com/category/news/");
                    return true;
            }

            return false;
        }
    };

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if(wb.canGoBack())
        {
            wb.goBack();
        }else
        {
            super.onBackPressed();
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);


    }
    @SuppressLint("SetJavaScriptEnabled")
    public void WebViewer(String url){

       // progressDialog.setTitle(R.string.loading);
        progressDialog.show();
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setBuiltInZoomControls(false);
        wb.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressDialog.dismiss();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();

            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
                super.onPageStarted(view, url, favicon);
            }
        });
        wb.loadUrl(url);
    }



}
