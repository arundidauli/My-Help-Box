package com.brucetoo.listvideoplay;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.animation.DataLoader;
import android.support.animation.VideoList;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.brucetoo.listvideoplay.demo.DisableListView;
import com.brucetoo.listvideoplay.demo.ListViewFragment;
import com.brucetoo.listvideoplay.demo.Utils;
import com.brucetoo.listvideoplay.videomanage.controller.ListScrollDistanceCalculator;
import com.brucetoo.listvideoplay.videomanage.controller.VideoControllerView;
import com.brucetoo.listvideoplay.videomanage.controller.ViewAnimator;
import com.brucetoo.listvideoplay.videomanage.manager.SingleVideoPlayerManager;
import com.brucetoo.listvideoplay.videomanage.manager.VideoPlayerManager;
import com.brucetoo.listvideoplay.videomanage.meta.CurrentItemMetaData;
import com.brucetoo.listvideoplay.videomanage.meta.MetaData;
import com.brucetoo.listvideoplay.videomanage.ui.MediaPlayerWrapper;
import com.brucetoo.listvideoplay.videomanage.ui.VideoPlayerView;
import com.brucetoo.listvideoplay.videomanage.utils.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.brucetoo.listvideoplay.R.layout.item_list_view;

public class RecyclerViewActivity extends AppCompatActivity implements AbsListView.OnScrollListener, View.OnClickListener {
    public static final String TAG = "RecyclerViewActivity";
    String WritePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    String ReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private DisableListView mListView;
    private FrameLayout mVideoFloatContainer;
    private View mVideoPlayerBg;
    private ImageView mVideoCoverMask;
    private VideoPlayerView mVideoPlayerView;
    private View mVideoLoadingView;
    private ProgressBar mVideoProgressBar;
    private View mCurrentPlayArea;
    private VideoControllerView mCurrentVideoControllerView;
    private int mCurrentActiveVideoItem = -1;
    private int mCurrentBuffer;
    private ArrayList<VideoList> videoLists;
    private TableLayout tabLayout;


    /**
     * Prevent {@link #stopPlaybackImmediately} be called too many times
     */
    private boolean mCanTriggerStop = true;

    private VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(null);

    /**
     * To detect ListView scroll delta
     */
    private ListScrollDistanceCalculator mDistanceListener = new ListScrollDistanceCalculator();

    /**
     * ListView's onScroll callback is super class {@link AbsListView}
     * to control,so let the page itself to determine whether scroll or
     * not by using {@link AbsListView.OnScrollListener#onScrollStateChanged(AbsListView, int)}
     */
    private boolean mUserTouchHappened;

    /**
     * Stop video have two scenes
     * 1.click to stop current video and start a new video
     * 2.when video item is dismiss or ViewPager changed ? tab changed ? ...
     */
    private boolean mIsClickToStop;

    private float mOriginalHeight;
    private VideoControllerView.MediaPlayerControlListener mPlayerControlListener = new VideoControllerView.MediaPlayerControlListener() {
        @Override
        public void start() {
            if (checkMediaPlayerInvalid())
                mVideoPlayerView.getMediaPlayer().start();
        }

        @Override
        public void pause() {
            mVideoPlayerView.getMediaPlayer().pause();
        }

        @Override
        public void hide() {
            mVideoPlayerView.getMediaPlayer().isPlaying();
        }

        @Override
        public int getDuration() {
            if (checkMediaPlayerInvalid()) {
                return mVideoPlayerView.getMediaPlayer().getDuration();
            }
            return 0;
        }

        @Override
        public int getCurrentPosition() {
            if (checkMediaPlayerInvalid()) {
                return mVideoPlayerView.getMediaPlayer().getCurrentPosition();
            }
            return 0;
        }

        @Override
        public void seekTo(int position) {
            if (checkMediaPlayerInvalid()) {
                mVideoPlayerView.getMediaPlayer().seekToPosition(position);
            }
        }

        @Override
        public boolean isPlaying() {
            if (checkMediaPlayerInvalid()) {
                return mVideoPlayerView.getMediaPlayer().isPlaying();
            }
            return false;
        }


        @Override
        public boolean isComplete() {
            return false;
        }

        @Override
        public int getBufferPercentage() {
            return mCurrentBuffer;
        }

        @Override
        public boolean isFullScreen() {
            return getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    || getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void toggleFullScreen() {
            if (isFullScreen()) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(Build.VERSION.SDK_INT < 19 ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        }

        @Override
        public void exit() {
            //TODO to handle exit status
            if (isFullScreen()) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    };


    /**
     * Runnable for update current video progress
     * 1.start this runnable in {@link MediaPlayerWrapper.MainThreadMediaPlayerListener#onInfoMainThread(int)}
     * 2.stop(remove) this runnable in {@link MediaPlayerWrapper.MainThreadMediaPlayerListener#onVideoStoppedMainThread()}
     * {@link MediaPlayerWrapper.MainThreadMediaPlayerListener#onVideoCompletionMainThread()}
     * {@link MediaPlayerWrapper.MainThreadMediaPlayerListener#onErrorMainThread(int, int)} ()}
     */
    private Runnable mProgressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPlayerControlListener != null) {

                if (mCurrentVideoControllerView.isShowing()) {
                    mVideoProgressBar.setVisibility(View.GONE);
                } else {
                    mVideoProgressBar.setVisibility(View.VISIBLE);
                }

                int position = mPlayerControlListener.getCurrentPosition();
                int duration = mPlayerControlListener.getDuration();
                if (duration != 0) {
                    long pos = 1000L * position / duration;
                    int percent = mPlayerControlListener.getBufferPercentage() * 10;
                    mVideoProgressBar.setProgress((int) pos);
                    mVideoProgressBar.setSecondaryProgress(percent);
                    mHandler.postDelayed(this, 1000);
                }
            }
        }
    };

    private void startMoveFloatContainer(boolean click) {

        if (mVideoFloatContainer.getVisibility() != View.VISIBLE) return;
        final float moveDelta;

        if (click) {
            Log.e(TAG, "startMoveFloatContainer > mFloatVideoContainer getTranslationY:" + mVideoFloatContainer.getTranslationY());
            ViewAnimator.putOn(mVideoFloatContainer).translationY(0);

            int[] playAreaPos = new int[2];
            int[] floatContainerPos = new int[2];
            mCurrentPlayArea.getLocationOnScreen(playAreaPos);
            mVideoFloatContainer.getLocationOnScreen(floatContainerPos);
            mOriginalHeight = moveDelta = playAreaPos[1] - floatContainerPos[1];

            Log.e(TAG, "startMoveFloatContainer > mFloatVideoContainer playAreaPos[1]:" + playAreaPos[1] + " floatContainerPos[1]:" + floatContainerPos[1]);
        } else {
            moveDelta = mDistanceListener.getTotalScrollDistance();
            /**
             * NOTE if ListView has divider,{@link ListScrollDistanceCalculator}
             * can't work perfectly when scroll reach to divider view.So find a
             * another way to get the same effect by divider.
             */
            Log.e(TAG, "ListView moveDelta :" + moveDelta + "");
        }

        float translationY = moveDelta + (!click ? mOriginalHeight : 0);

        Log.e(TAG, "startMoveFloatContainer > moveDelta:" + moveDelta + " before getTranslationY:" + mVideoFloatContainer.getTranslationY()
                + " mOriginalHeight:" + mOriginalHeight + " translationY:" + translationY);

        ViewAnimator.putOn(mVideoFloatContainer).translationY(translationY);

        Log.i(TAG, "startMoveFloatContainer < after getTranslationY:" + mVideoFloatContainer.getTranslationY());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), WritePermission) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getApplicationContext(), ReadPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{WritePermission, ReadPermission}, 1);
        }



        mListView = findViewById(R.id.list_view);
        mVideoFloatContainer = findViewById(R.id.layout_float_video_container);
        mVideoPlayerBg = findViewById(R.id.video_player_bg);
        mVideoCoverMask = findViewById(R.id.video_player_mask);
        mVideoPlayerView = findViewById(R.id.video_player_view);
        mVideoLoadingView = findViewById(R.id.video_progress_loading);
        mVideoProgressBar = findViewById(R.id.video_progress_bar);
        videoLists = new ArrayList<>();

        mListView.setAdapter(new ListViewAdapter1(this));
        mListView.setOnScrollListener(this);
        videoLists.clear();

        videoLists.add(new VideoList("http://android-imgs.25pp.com/fs08/2017/02/09/0/84219701e196f9b98565bd9d1c5072bc.jpg", "https://www.youtube.com/watch?v=y7TC2Ef__zw", 0));
        videoLists.add(new VideoList("http://android-imgs.25pp.com/fs08/2017/02/09/0/84219701e196f9b98565bd9d1c5072bc.jpg", "https://www.youtube.com/watch?v=fSS_R91Nimw", 1));

        mVideoPlayerView.addMediaPlayerListener(new MediaPlayerWrapper.MainThreadMediaPlayerListener() {
            @Override
            public void onVideoSizeChangedMainThread(int width, int height) {

            }

            @Override
            public void onVideoPreparedMainThread() {

                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onVideoPreparedMainThread");
                mVideoFloatContainer.setVisibility(View.VISIBLE);
                mVideoPlayerView.setVisibility(View.VISIBLE);
                mVideoLoadingView.setVisibility(View.VISIBLE);
                //for cover the pre Video frame
                mVideoCoverMask.setVisibility(View.VISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {

                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onVideoCompletionMainThread");

                if (mCurrentPlayArea != null) {
                    mCurrentPlayArea.setClickable(true);
                }

                mVideoFloatContainer.setVisibility(View.INVISIBLE);
                mCurrentPlayArea.setVisibility(View.VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                ViewAnimator.putOn(mVideoFloatContainer).translationY(0);

                //stop update progress
                mVideoProgressBar.setVisibility(View.GONE);
                mHandler.removeCallbacks(mProgressRunnable);
            }

            @Override
            public void onErrorMainThread(int what, int extra) {
                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onErrorMainThread");
                if (mCurrentPlayArea != null) {
                    mCurrentPlayArea.setClickable(true);
                    mCurrentPlayArea.setVisibility(View.VISIBLE);
                }
                mVideoFloatContainer.setVisibility(View.INVISIBLE);

                //stop update progress
                mVideoProgressBar.setVisibility(View.GONE);
                mHandler.removeCallbacks(mProgressRunnable);
            }

            @Override
            public void onBufferingUpdateMainThread(int percent) {
                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onBufferingUpdateMainThread");
                mCurrentBuffer = percent;
            }

            @Override
            public void onVideoStoppedMainThread() {
                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onVideoStoppedMainThread");
                if (!mIsClickToStop) {
                    mCurrentPlayArea.setClickable(true);
                    mCurrentPlayArea.setVisibility(View.VISIBLE);
                }

                //stop update progress
                mVideoProgressBar.setVisibility(View.GONE);
                mHandler.removeCallbacks(mProgressRunnable);
            }

            @Override
            public void onInfoMainThread(int what) {
                Log.e(MediaPlayerWrapper.VIDEO_TAG, "check play onInfoMainThread what:" + what);
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {

                    //start update progress
                    mVideoProgressBar.setVisibility(View.VISIBLE);
                    mHandler.post(mProgressRunnable);

                    mVideoPlayerView.setVisibility(View.VISIBLE);
                    mVideoLoadingView.setVisibility(View.GONE);
                    mVideoCoverMask.setVisibility(View.GONE);
                    mVideoPlayerBg.setVisibility(View.VISIBLE);
                    createVideoControllerView();

                    mCurrentVideoControllerView.showWithTitle("VIDEO TEST - " + mCurrentActiveVideoItem);
                } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    mVideoLoadingView.setVisibility(View.VISIBLE);
                } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    mVideoLoadingView.setVisibility(View.GONE);
                }
            }
        });

    }

    public void openFolder() {
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }

    private void createVideoControllerView() {

        if (mCurrentVideoControllerView != null) {
            mCurrentVideoControllerView.hide();
            mCurrentVideoControllerView = null;
        }

        mCurrentVideoControllerView = new VideoControllerView.Builder((Activity) getApplicationContext(), mPlayerControlListener)
                .withVideoTitle("Hello ji friend")
                .withVideoView(mVideoPlayerView)//to enable toggle display controller view
                .canControlBrightness(true)
                .canControlVolume(true)
                .canSeekVideo(false)
                .exitIcon(R.drawable.video_top_back)
                .pauseIcon(R.drawable.ic_media_pause)
                .playIcon(R.drawable.ic_media_play)
                .shrinkIcon(R.drawable.ic_media_fullscreen_shrink)
                .stretchIcon(R.drawable.ic_media_fullscreen_stretch)
                .build(mVideoFloatContainer);//layout container that hold video play view
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        mDistanceListener.onScrollStateChanged(absListView, scrollState);
        switch (scrollState) {
            case SCROLL_STATE_FLING:
            case SCROLL_STATE_TOUCH_SCROLL:
                mUserTouchHappened = true;
                break;
            case SCROLL_STATE_IDLE:
                mUserTouchHappened = false;
                //if ListView state is idle,adjust originalHeight of mVideoFloatContainer
                mOriginalHeight = mVideoFloatContainer.getTranslationY();
                Log.i(TAG, "startMoveFloatContainer --- onScrollStateChanged originHeight:" + mOriginalHeight);
                break;
            default:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        //User not touch screen but AbsListView callback onScroll
        if (!mUserTouchHappened) return;

        Log.e(TAG, "check stop activeItem:" + mCurrentActiveVideoItem + "  firstVisibleItem:" + firstVisibleItem
                + "  lastVisibleItem:" + (firstVisibleItem + visibleItemCount - 1));

        mDistanceListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);

        startMoveFloatContainer(false);

        //NOTE if ListView has header we need subtract header count,and test here

        //This is just detect the playing item is gone and stop it when scroll ListView
        if (mCurrentActiveVideoItem < firstVisibleItem || mCurrentActiveVideoItem > (firstVisibleItem + visibleItemCount - 1)) {

            //remote msg callback first
            if (mCurrentPlayArea != null && mCurrentVideoControllerView != null)
                mCurrentVideoControllerView.removeAllCallBacks();
            //item invisible so stop all playBack
            if (mCanTriggerStop) {
                Log.e(TAG, "check stop activeItem:" + mCurrentActiveVideoItem + "  firstVisibleItem:" + (firstVisibleItem - 1)
                        + "  lastVisibleItem:" + (firstVisibleItem + visibleItemCount - 1));
                mCanTriggerStop = false;
                stopPlaybackImmediately();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        stopPlaybackImmediately();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlaybackImmediately();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPlaybackImmediately();
    }


    public void stopPlaybackImmediately() {

        mIsClickToStop = false;

        if (mCurrentPlayArea != null) {
            mCurrentPlayArea.setClickable(true);
        }

        if (mVideoPlayerManager != null) {
            Log.e(TAG, "check play stopPlaybackImmediately");
            mVideoFloatContainer.setVisibility(View.INVISIBLE);
            mVideoPlayerManager.stopAnyPlayback();
        }
    }

    @Override
    public void onClick(View v) {


    }

    public void shareVideoWhatsApp(String name) {

        Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() + "/Download/YouTube-Downloader/" + name);

        // Uri uri = Uri.fromFile(v);
        Intent videoshare = new Intent(Intent.ACTION_SEND);
        videoshare.setType("*/*");
        videoshare.setPackage("com.whatsapp");
        videoshare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        videoshare.putExtra(Intent.EXTRA_STREAM, selectedUri);

        startActivity(videoshare);

    }

    private boolean checkMediaPlayerInvalid() {
        return mVideoPlayerView != null && mVideoPlayerView.getMediaPlayer() != null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (mVideoFloatContainer == null) return;

        ViewGroup.LayoutParams layoutParams = mVideoFloatContainer.getLayoutParams();

//        mCurrentVideoControllerView.hide();

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            //200 indicate the height of video play area
            layoutParams.height = (int) getResources().getDimension(R.dimen.video_item_portrait_height);
            layoutParams.width = Utils.getDeviceWidth(getApplicationContext());

            ViewAnimator.putOn(mVideoFloatContainer).translationY(mOriginalHeight);

            // Show status bar
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mListView.setEnableScroll(true);

        } else {

            layoutParams.height = Utils.getDeviceHeight(getApplicationContext());
            layoutParams.width = Utils.getDeviceWidth(getApplicationContext());

            ViewAnimator.putOn(mVideoFloatContainer).translationY(0);

            // Hide status
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mListView.setEnableScroll(false);

        }
        mVideoFloatContainer.setLayoutParams(layoutParams);
    }

    private class ListViewAdapter1 extends BaseAdapter {

        View.OnClickListener listener;
        private int mLastPosition = -1;

        private ListViewAdapter1(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public int getCount() {
            return videoLists.size();
        }

        @Override
        public Object getItem(int i) {
            return videoLists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder = ViewHolder.newInstance(getApplicationContext(), view, viewGroup, R.layout.item_list_view);

            Picasso.with(getApplicationContext()).load(videoLists.get(i).getCoverImage())
                    .placeholder(R.drawable.shape_place_holder)
                    .into((ImageView) holder.getView(R.id.img_cover));

            holder.getView(R.id.layout_play_area).setTag(i);
            holder.getView(R.id.layout_play_area).setOnClickListener(listener);


            holder.getView(R.id.layout_play_area).setTag(i);
            holder.getView(R.id.layout_play_area).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   urlmaker(18, videoLists.get(i).getVideoUrl());
                    Log.e(TAG, "********************Youtube Video Url*******************" + videoLists.get(i).getVideoUrl());

                    mIsClickToStop = true;
                    v.setClickable(false);
                    if (mCurrentPlayArea != null) {
                        if (mCurrentPlayArea != v) {
                            mCurrentPlayArea.setClickable(true);
                            mCurrentPlayArea.setVisibility(View.VISIBLE);
                            mCurrentPlayArea = v;
                        } else {//click same area
                            if (mVideoFloatContainer.getVisibility() == View.VISIBLE) return;
                        }
                    } else {
                        mCurrentPlayArea = v;
                    }

                    //invisible self ,and make visible when video play completely
                    v.setVisibility(View.INVISIBLE);
                    if (mCurrentVideoControllerView != null)
                        mCurrentVideoControllerView.hide();

                    mVideoFloatContainer.setVisibility(View.VISIBLE);
                    mVideoCoverMask.setVisibility(View.GONE);
                    mVideoPlayerBg.setVisibility(View.GONE);

                    //  VideoModel model = (VideoModel) v.getTag();


                    mCurrentActiveVideoItem = i;
                    mCanTriggerStop = true;

                    //move container view
                    startMoveFloatContainer(true);

                    mVideoLoadingView.setVisibility(View.VISIBLE);
                    mVideoPlayerView.setVisibility(View.INVISIBLE);

                    //play video
                    mVideoPlayerManager.playNewVideo(new CurrentItemMetaData(i, v), mVideoPlayerView, DataLoader.deleverdata(getApplicationContext(), "url"));


                }
            });

            holder.getView(R.id.share).setTag(i);
            holder.getView(R.id.share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Click Share", Toast.LENGTH_LONG).show();
                }
            });
            holder.getView(R.id.download).setTag(i);
            holder.getView(R.id.download).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Click Download", Toast.LENGTH_LONG).show();
                    //DownloadManagingF(DataLoader.deleverdata(getContext(), "url"), DataLoader.deleverdata(getContext(), "title"), ".mp4");
                }
            });
            holder.getView(R.id.like).setTag(i);
            holder.getView(R.id.like).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Liked", Toast.LENGTH_LONG).show();
                    holder.getView(R.id.like_pink).setVisibility(View.VISIBLE);
                    holder.getView(R.id.like).setVisibility(View.GONE);
                }
            });
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), (i > mLastPosition) ? R.anim.left_from_right : R.anim.down_from_top);
            holder.getView().startAnimation(animation);
            mLastPosition = i;
            if (i == 1) {
                holder.getView().getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        Rect rect = new Rect();
                        holder.getView().getGlobalVisibleRect(rect);
                        Log.e(TAG, "onScrollChanged rect.top -> " + rect.top + "  rect.bottom -> " + rect.bottom);
                    }
                });
            }
            return holder.getView();
        }
    }
}
