package com.tylersuehr.ovaltabsexample;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tylersuehr.ovaltabs.OvalTabLayout;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * @author Tyler Suehr
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup pager
        final ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new ColoredPagerAdapter(getSupportFragmentManager(),
                ColoredFragment.create(Color.CYAN),
                ColoredFragment.create(Color.GREEN)));

        // Setup tabs
        final OvalTabLayout tabs = (OvalTabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
    }

    /**
     * Subclass of {@link Fragment} that displays the given color.
     */
    public static final class ColoredFragment extends Fragment {
        private int color;

        public static ColoredFragment create(@ColorInt int color) {
            ColoredFragment frag = new ColoredFragment();
            frag.color = color;
            return frag;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final FrameLayout fl = new FrameLayout(getContext());
            fl.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            fl.setBackgroundColor(color);
            return fl;
        }
    }


    /**
     * Subclass of {@link FragmentStatePagerAdapter} to manage a small array of fragments.
     */
    private static final class ColoredPagerAdapter extends FragmentStatePagerAdapter {
        private final String[] titles = { "For", "Search", "Here", "Cool", "New" };
        private final Fragment[] frags;

        ColoredPagerAdapter(FragmentManager fm, Fragment... frags) {
            super(fm);
            this.frags = frags;
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }
}