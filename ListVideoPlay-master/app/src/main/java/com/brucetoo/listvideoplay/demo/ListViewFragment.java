package com.brucetoo.listvideoplay.demo;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.animation.VideoList;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import com.brucetoo.listvideoplay.R;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Bruce Too
 * On 10/19/16.
 * At 17:59
 */

public class ListViewFragment extends Fragment implements AbsListView.OnScrollListener, View.OnClickListener {

    public static final String TAG = ListViewFragment.class.getSimpleName();
    public ArrayList<DataList> list;
    String WritePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    String ReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    byte[] imageBytes;
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
    private ArrayList<VideoList> myvideolist;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
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
            return getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    || getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void toggleFullScreen() {
            if (isFullScreen()) {
                Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                Objects.requireNonNull(getActivity()).setRequestedOrientation(Build.VERSION.SDK_INT < 19 ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        }

        @Override
        public void exit() {
            //TODO to handle exit status
            if (isFullScreen()) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    private void fetchdata() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());


                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = iterator.next();

                    String Image = (String) next.child("imageUrl").getValue();
                    String Video = (String) next.child("videoUrl").getValue();
                    String key = next.getKey();
                    Log.i(TAG, "Image Url = " + Image);
                    Log.i(TAG, "Video Url = " + Video);
                    myvideolist.add(new VideoList(Image, Video, Integer.parseInt(key)));

                }


                for (int i = 0; i < myvideolist.size(); i++) {
                    Log.e(TAG, "============================" + myvideolist.get(i).getCoverImage());
                    Log.e(TAG, "============================" + myvideolist.get(i).getVideoUrl());


                }


//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    DataList post = postSnapshot.getValue(DataList.class);
//                   // universityList.add(post);
//                   /* Log.i(TAG,"Image Url = " + post.imageUrl);
//                    Log.i(TAG,"Video Url = " + post.videoUrl);*/
//                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("url");
        myvideolist = new ArrayList<>();
        myvideolist.clear();
        myvideolist.add(new VideoList("https://i.postimg.cc/PrctSnd7/32884db200e642543687fd5430fd01ca.jpg","https://r4---sn-qxa7snee.googlevideo.com/videoplayback?expire=1573140507&ei=uuPDXezrPJPA1AaFjZ7ABA&ip=103.199.158.206&id=o-AIE9SR8vglMkwRqTxJ2q5vilFVQ-k9zfqJPxeDnZm-e2&itag=242&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&mm=31%2C29&mn=sn-qxa7snee%2Csn-qxaeen7l&ms=au%2Crdu&mv=m&mvi=3&pl=24&initcwndbps=356250&mime=video%2Fwebm&gir=yes&clen=552889&dur=35.000&lmt=1572251653696036&mt=1573118809&fvip=4&keepalive=yes&fexp=23842630&beids=9466586&c=WEB&txp=5535432&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cmime%2Cgir%2Cclen%2Cdur%2Clmt&sig=ALgxI2wwRQIgUP-Me36lIe4xRBbMxeBgACtpOSd_Yx2Q4lVP4QjfTuwCIQCb4-vUo4Zf7ssWbETZSXN-Sq6McmNICDs4B_Yl5cSyVw%3D%3D&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRgIhAJzl8gICeKOwF-xAVsxL-ywJmMxrl97PdCYjxWonfPFRAiEAh5HXMK7fVUACoRo9xuGEjsgb5lsDSX43mmYakKt7Y4w%3D",1));

        mListView = view.findViewById(R.id.list_view);
        mVideoFloatContainer = view.findViewById(R.id.layout_float_video_container);
        mVideoPlayerBg = view.findViewById(R.id.video_player_bg);
        mVideoCoverMask = view.findViewById(R.id.video_player_mask);
        mVideoPlayerView = view.findViewById(R.id.video_player_view);
        mVideoLoadingView = view.findViewById(R.id.video_progress_loading);
        mVideoProgressBar = view.findViewById(R.id.video_progress_bar);

        mListView.setAdapter(new ListViewAdapter(this));
        mListView.setOnScrollListener(this);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myvideolist.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = iterator.next();
                    String Image = (String) next.child("imageUrl").getValue();
                    String Video = (String) next.child("videoUrl").getValue();
                    String key = next.getKey();
                    Log.i(TAG, "Image Url = " + Image);
                    Log.i(TAG, "Video Url = " + Video);
                    myvideolist.add(new VideoList(Image,Video,1));

                }




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
        if (ActivityCompat.checkSelfPermission(getActivity(), WritePermission) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), ReadPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{WritePermission, ReadPermission}, 1);
        }


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
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

    public void YouTubeVideoDownloadF(int iTag, String url1) {

        if (ActivityCompat.checkSelfPermission(getActivity(), WritePermission) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), ReadPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{WritePermission, ReadPermission}, 1);
        } else {

            /*YTDownload(iTag, url1);*/
        }

    }

   /* public String YTDownload(final int itag, String VideoURLDownload) {
        // String VideoURLDownload = "https://www.youtube.com/watch?v=XRtaIYg8z8I";
        @SuppressLint("StaticFieldLeak") YouTubeUriExtractor youTubeUriExtractor = new YouTubeUriExtractor(getActivity()) {
            @Override
            public void onUrisAvailable(String videoId, final String videoTitle, SparseArray<YtFile> ytFiles) {
                if ((ytFiles != null)) {
                    String downloadURL = ytFiles.get(itag).getUrl();
                    Log.e("Download URL: ", downloadURL);
                   // DataLoader.loaddata(getContext(), "url", downloadURL);

                    if (itag == 18 || itag == 22) {
                        String mp4 = ".mp4";
                        //  DownloadManagingF(downloadURL, videoTitle,mp4);
                     //   DataLoader.loaddata(getContext(), "title", videoTitle);

                    } else if (itag == 251) {
                        String mp3 = ".mp3";
                        //  DownloadManagingF(downloadURL,videoTitle,mp3);
                    }

                } else Toast.makeText(getActivity(), "Error With URL", Toast.LENGTH_LONG).show();
            }

        };
        youTubeUriExtractor.execute(VideoURLDownload);
        return DataLoader.deleverdata(getActivity(), "url");
    }*/

    public void DownloadManagingF(String downloadURL, String videoTitle, String extentiondwn) {
        if (downloadURL != null) {
            DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadURL));
            request.setTitle(videoTitle);
            request.setDestinationInExternalPublicDir("/Download/YouTube-Downloader/", videoTitle + extentiondwn);
            if (downloadManager != null) {
                Toast.makeText(getActivity(), "Downloading...", Toast.LENGTH_SHORT).show();
                downloadManager.enqueue(request);
            }
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                public void onReceive(Context ctxt, Intent intent) {
                    Toast.makeText(getContext(), "Download Completed", Toast.LENGTH_SHORT).show();

                    Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() + "/Download/YouTube-Downloader/");
                    Intent intentop = new Intent(Intent.ACTION_VIEW);
                    intentop.setDataAndType(selectedUri, "resource/folder");


                    if (intentop.resolveActivityInfo(getActivity().getPackageManager(), 0) != null) {
                        startActivity(intentop);
                    } else {
                        Toast.makeText(getContext(), "Saved on: Download/YouTube-Downloader", Toast.LENGTH_LONG).show();
                        //restartApp();
                    }
                    getActivity().unregisterReceiver(this);
                    getActivity().finish();
                }
            };
            getActivity().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        }
    }

    public void viewdownloadsbtn() {
        openFolder();
    }

    /* public void urlmaker(final int itag, String youTubeURL) {
         if (youTubeURL.contains("http")) {

             if (ActivityCompat.checkSelfPermission(getActivity(), WritePermission) != PackageManager.PERMISSION_GRANTED ||
                     ActivityCompat.checkSelfPermission(getActivity(), ReadPermission) != PackageManager.PERMISSION_GRANTED) {
                 ActivityCompat.requestPermissions(getActivity(), new String[]{WritePermission, ReadPermission}, 1);
             } else {

                 @SuppressLint("StaticFieldLeak") YouTubeUriExtractor youTubeUriExtractor = new YouTubeUriExtractor(getActivity()) {
                     @Override
                     public void onUrisAvailable(String videoId, final String videoTitle, SparseArray<YtFile> ytFiles) {
                         if ((ytFiles != null)) {

                             List<Integer> iTags = Arrays.asList(18, 22, 137);

                             for (Integer iTag : iTags) {
                                 String downloadURL = ytFiles.get(iTag).getUrl();
                                 Log.e(TAG, "*********Download Url***********" + downloadURL + "\n" + iTag);
                                 if (downloadURL != null) {
                                     DataLoader.loaddata(getContext(), "url", downloadURL);
                                     DataLoader.loaddata(getContext(), "title", videoTitle);
                                     break;
                                 } else {
                                     Toast.makeText(getActivity(), "Error With URL", Toast.LENGTH_LONG).show();

                                 }

                             }


                            *//* if (itag == 18 || itag == 22) {
                                String mp4 = ".mp4";
                                //  DownloadManagingF(downloadURL, videoTitle,mp4);
                                DataLoader.loaddata(getContext(), "title", videoTitle);
                            } else if (itag == 251) {
                                String mp3 = ".mp3";
                                //  DownloadManagingF(downloadURL,videoTitle,mp3);
                            }
*//*
                        } else
                            Toast.makeText(getActivity(), "Error With URL", Toast.LENGTH_LONG).show();
                    }
                };
                youTubeUriExtractor.execute(youTubeURL);
            }

        } else {
            Toast.makeText(getActivity(), "Enter URL First", Toast.LENGTH_LONG).show();
        }

    }
*/
    public void ytvdownloadhd(View view, String youTubeURL) {
        //youTubeURL = editText.getText().toString();

        if (youTubeURL.contains("http"))
            YouTubeVideoDownloadF(22, youTubeURL);
        else Toast.makeText(getActivity(), "Enter URL First", Toast.LENGTH_LONG).show();


    }

    public void ytvdownload(View view, String youTubeURL) {
        //youTubeURL = editText.getText().toString();

        if (youTubeURL.contains("http"))
            YouTubeVideoDownloadF(18, youTubeURL);

        else {
            Toast.makeText(getActivity(), "Enter URL First", Toast.LENGTH_LONG).show();
        }


    }

    public void ytvdownloadhdp(View view, String youTubeURL) {
        // youTubeURL = editText.getText().toString();
        if (youTubeURL.contains("http")) {

            YouTubeVideoDownloadF(140, youTubeURL);
        } else Toast.makeText(getActivity(), "Enter URL First", Toast.LENGTH_LONG).show();
    }

    public void ytvdownloadaudio(View view, String youTubeURL) {
        //  youTubeURL = editText.getText().toString();
        if (youTubeURL.contains("http"))
            YouTubeVideoDownloadF(251, youTubeURL);
        else Toast.makeText(getActivity(), "Enter URL First", Toast.LENGTH_LONG).show();
    }

    public void openFolder() {
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }

    private void createVideoControllerView() {

        if (mCurrentVideoControllerView != null) {
            mCurrentVideoControllerView.hide();
            mCurrentVideoControllerView = null;
        }

        mCurrentVideoControllerView = new VideoControllerView.Builder(getActivity(), mPlayerControlListener)
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
            layoutParams.width = Utils.getDeviceWidth(getActivity());

            ViewAnimator.putOn(mVideoFloatContainer).translationY(mOriginalHeight);

            // Show status bar
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mListView.setEnableScroll(true);

        } else {

            layoutParams.height = Utils.getDeviceHeight(getActivity());
            layoutParams.width = Utils.getDeviceWidth(getActivity());

            ViewAnimator.putOn(mVideoFloatContainer).translationY(0);

            // Hide status
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mListView.setEnableScroll(false);

        }
        mVideoFloatContainer.setLayoutParams(layoutParams);
    }

    public class ListViewAdapter extends BaseAdapter {

        View.OnClickListener listener;
        private int mLastPosition = -1;

        public ListViewAdapter(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public int getCount() {
            return myvideolist.size();
        }

        @Override
        public Object getItem(int i) {
            return myvideolist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder = ViewHolder.newInstance(getContext(), view, viewGroup, R.layout.item_list_view);

            Picasso.with(getActivity()).load(myvideolist.get(i).getCoverImage())
                    .placeholder(R.drawable.shape_place_holder)
                    .into((ImageView) holder.getView(R.id.img_cover));

            holder.getView(R.id.layout_play_area).setTag(i);
            holder.getView(R.id.layout_play_area).setOnClickListener(listener);


            holder.getView(R.id.layout_play_area).setTag(i);
            holder.getView(R.id.layout_play_area).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //   urlmaker(18, myvideolist.get(i).getVideoUrl());
                    //  Log.e(TAG, "********************Youtube Video Url*******************" + myvideolist.get(i).getVideoUrl());

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
                    mVideoPlayerManager.playNewVideo(new CurrentItemMetaData(i, v), mVideoPlayerView, myvideolist.get(i).getVideoUrl());


                }
            });

            holder.getView(R.id.share).setTag(i);
            holder.getView(R.id.share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Click Share", Toast.LENGTH_LONG).show();
                }
            });
            holder.getView(R.id.download).setTag(i);
            holder.getView(R.id.download).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Click Download", Toast.LENGTH_LONG).show();
                    // DownloadManagingF(DataLoader.deleverdata(getContext(), "url"), DataLoader.deleverdata(getContext(), "title"), ".mp4");
                }
            });
            holder.getView(R.id.like).setTag(i);
            holder.getView(R.id.like).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Liked", Toast.LENGTH_LONG).show();
                    holder.getView(R.id.like_pink).setVisibility(View.VISIBLE);
                    holder.getView(R.id.like).setVisibility(View.GONE);
                }
            });
            Animation animation = AnimationUtils.loadAnimation(getContext(), (i > mLastPosition) ? R.anim.left_from_right : R.anim.down_from_top);
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
