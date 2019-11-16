package com.netkosh.orakart.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.netkosh.orakart.R;
import com.netkosh.orakart.fragment.Attendancefragment;
import com.netkosh.orakart.fragment.Homefragment;
import com.netkosh.orakart.fragment.Homeworkfragment;
import com.netkosh.orakart.fragment.Noticefragment;


public class HomeMain extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Context mContext = this;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    setFragment(0);
                    return true;
                case R.id.navigation_gifts:
                    setFragment(1);
                    return true;
                case R.id.navigation_cart:
                    setFragment(2);
                    return true;

            }

            return false;
        }
    };

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressDialog = new ProgressDialog(mContext, R.style.Custom);
        setFragment(0);

    }

  /*  @SuppressLint("SetJavaScriptEnabled")
    public void WebViewer(String url){

       // progressDialog.setTitle(R.string.loading);
        progressDialog.show();
        progressDialog.setCancelable(false);
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressDialog.dismiss();

                Toast.makeText(HomeMain.this, "Your Internet Connection May not be active Or " + description , Toast.LENGTH_LONG).show();

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

        if (!isConnected(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "You are offline ", Toast.LENGTH_SHORT).show();

        }
    }*/

    protected void setFragment(int fragmentno) {
        Fragment fragment = null;
        switch (fragmentno) {
            case 0:
                fragment = new Homefragment();
                break;
            case 1:
                fragment = new Attendancefragment();
                break;
            case 2:
                fragment = new Noticefragment();
                break;

            default:
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        }

    }

}
