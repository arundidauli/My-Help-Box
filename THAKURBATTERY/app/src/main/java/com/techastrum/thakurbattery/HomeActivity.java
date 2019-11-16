package com.techastrum.thakurbattery;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private ProgressDialog progressDialog;
    private Context mContext;
    private WebView wb;
    private LinearLayout no_internet;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    WebViewer("http://thakurbatteryagency.techastrum.in/Master/Default.aspx");
                    return true;
                case R.id.navigation_purchase:
                    WebViewer("http://thakurbatteryagency.techastrum.in/Master/Purchase.aspx");
                    return true;
                case R.id.navigation_stock_transfer:
                    WebViewer("http://thakurbatteryagency.techastrum.in/Master/BranchStockTransfer.aspx");
                    return true;
                case R.id.navigation_transaction:
                    WebViewer("http://thakurbatteryagency.techastrum.in/Master/Transaction.aspx");
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = HomeActivity.this;
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressDialog = new ProgressDialog(mContext);
        wb = findViewById(R.id.webview);
        no_internet = findViewById(R.id.no_internet);
        if (isNetworkAvailable()) {
            no_internet.setVisibility(View.GONE);
            WebViewer("http://thakurbatteryagency.techastrum.in/Master/Default.aspx");
        } else {
            no_internet.setVisibility(View.VISIBLE);
        }


        findViewById(R.id.button_try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (wb.canGoBack()) {
            wb.goBack();
        } else {
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
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void WebViewer(String url) {
        progressDialog.setMessage(mContext.getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setSupportZoom(false);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                no_internet.setVisibility(View.VISIBLE);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
