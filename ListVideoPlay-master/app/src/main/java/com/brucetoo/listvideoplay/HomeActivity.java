package com.brucetoo.listvideoplay;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;

import com.brucetoo.listvideoplay.demo.ListViewFragment;
import com.brucetoo.listvideoplay.videomanage.ui.VideoPlayerView;
import com.brucetoo.videoplayer.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AbsListView.OnScrollListener, View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private VideoPlayerView mVideoPlayerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_home);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mVideoPlayerView = findViewById(R.id.video_player_view);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Tracker.onConfigurationChanged(this, newConfig);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFrag(new ListViewFragment(), "ALL STATUS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.super.onBackPressed();
                        mVideoPlayerView.getMediaPlayer().pause();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // mVideoPlayerView.getMediaPlayer().isPlaying();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  mVideoPlayerView.getMediaPlayer().pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayerView.getMediaPlayer().pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoPlayerView.getMediaPlayer().pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
