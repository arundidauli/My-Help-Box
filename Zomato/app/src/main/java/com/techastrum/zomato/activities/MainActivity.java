package com.techastrum.zomato.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techastrum.zomato.R;
import com.techastrum.zomato.fragment.AccountFragment;
import com.techastrum.zomato.fragment.CartFragment;
import com.techastrum.zomato.fragment.HomeFragment;
import com.techastrum.zomato.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFragment(0);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    setFragment(0);
                    return true;
                case R.id.search:
                    setFragment(1);
                    return true;
                case R.id.cart:
                    setFragment(2);
                    return true;
                case R.id.account:
                    setFragment(3);
                    return true;

            }

            return false;
        }
    };

    protected void setFragment(int fragmentno) {
        Fragment fragment = null;
        switch (fragmentno) {
            case 0:
                fragment = new HomeFragment();

                break;
            case 1:
                fragment = new SearchFragment();

                break;
            case 2:
                fragment = new CartFragment();

                break;
            case 3:
                fragment = new AccountFragment();

                break;

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
