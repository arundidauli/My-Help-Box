package com.techtutz.ytd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.commit451.youtubeextractor.YouTubeExtractionResult;
import com.commit451.youtubeextractor.YouTubeExtractor;
import com.jacksonandroidnetworking.JacksonParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    ProgressBar pb;
    Dialog dialog;
    int downloadedSize = 0;
    int totalSize = 0;
    TextView txtdown;
    String url;
    String dwnload_file_path = "http://coderzheaven.com/sample_folder/sample_file.png";

    private static final String YOUTUBE_ID = "ea4-5mrpGfE";

    private final YouTubeExtractor mExtractor = YouTubeExtractor.create();


    private Callback<YouTubeExtractionResult> mExtractionCallback = new Callback<YouTubeExtractionResult>() {
        @Override
        public void onResponse(Call<YouTubeExtractionResult> call, Response<YouTubeExtractionResult> response) {
            bindVideoResult(response.body());
        }

        @Override
        public void onFailure(Call<YouTubeExtractionResult> call, Throwable t) {
            try{


                onError(t);
            }catch (Exception e){
                e.printStackTrace();

            }


        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtdown=findViewById(R.id.download);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        mExtractor.extract(YOUTUBE_ID).enqueue(mExtractionCallback);


        pb=new ProgressBar(this);
       // File file=new File("/mnt/sdcard/...");

       // new DownloadTask(this,file,"Success");
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
                /*showProgress(dwnload_file_path);

                new Thread(new Runnable() {
                    public void run() {
                        downloadFile();
                    }
                }).start();*/
            }
        });


    }

    private void onError(Throwable t) {
        t.printStackTrace();
        Toast.makeText(MainActivity.this, "It failed to extract. So sad", Toast.LENGTH_SHORT).show();
    }
    private void bindVideoResult(YouTubeExtractionResult result) {

//        Here you can get download url link
        Log.d("OnSuccess", "Got a result with the best url: " + result.getBestAvailableQualityVideoUri());

        Toast.makeText(this, "result : " + result.getSd360VideoUri(), Toast.LENGTH_SHORT).show();
    }




    public void download(){

        String youtubeLink = "https://www.youtube.com/watch?v=W_B2UZ_ZoxU";

        @SuppressLint("StaticFieldLeak")
        YouTubeUriExtractor ytEx = new YouTubeUriExtractor(this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                if (ytFiles != null) {
                    int itag = 22;
                    url = ytFiles.get(itag).getUrl();
                    Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
                    Log.e("download",url);
                    txtdown.setText(url);
                   /* try {
                        URL u = new URL(url);
                        HttpURLConnection c = (HttpURLConnection) u.openConnection();
                        c.setRequestMethod("GET");
*//*              c.setRequestProperty("Youtubedl-no-compression", "True");
              c.setRequestProperty("User-Agent", "YouTube");*//*

                        c.setDoOutput(true);
                        c.connect();

                        FileOutputStream f=new FileOutputStream(new File("/sdcard/3.flv"));

                        InputStream in=c.getInputStream();
                        byte[] buffer=new byte[1024];
                        int sz = 0;
                        while ( (sz = in.read(buffer)) > 0 ) {
                            f.write(buffer,0, sz);
                        }
                        f.close();
                    } catch (MalformedURLException e) {
                        new RuntimeException();
                    } catch (IOException e) {
                        new RuntimeException();
                    }
*/



                }
            }
        };
        ytEx.execute(youtubeLink);


    }

}











 /*   public void douwloadfile(){

        AndroidNetworking.download(url,dirPath,fileName)
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    public void uploadfile(){
        AndroidNetworking.upload(url)
                .addMultipartFile("image",file)
                .addMultipartParameter("key","value")
                .setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
*/
  /*  void downloadFile(){

        try {
            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection)
                    url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();

            //set the path where we want to save the file
            File SDCardRoot = Environment.getExternalStorageDirectory();
            //create a new file, to save the downloaded file
            File file = new File(SDCardRoot,"downloaded_file.png");

            FileOutputStream fileOutput = new FileOutputStream(file);

            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file which we are downloading
            totalSize = urlConnection.getContentLength();

            runOnUiThread(new Runnable() {
                public void run() {
                    pb.setMax(totalSize);
                }
            });

            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                // update the progressbar //
                runOnUiThread(new Runnable() {
                    public void run() {
                        pb.setProgress(downloadedSize);
                        float per = ((float)downloadedSize/totalSize) *
                                100;
                        cur_val.setText("Downloaded " + downloadedSize +

                                "KB / " + totalSize + "KB (" + (int)per + "%)" );
                    }
                });
            }
            //close the output stream when complete //
            fileOutput.close();
            runOnUiThread(new Runnable() {
                public void run() {
                    // pb.dismiss(); // if you want close it..
                }
            });

        } catch (final MalformedURLException e) {
            showError("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            showError("Error : IOException " + e);
            e.printStackTrace();
        }
        catch (final Exception e) {
            showError("Error : Please check your internet connection " +
                    e);
        }
    }

    void showError(final String err){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), err,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    void showProgress(String file_path){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  dialog.setContentView(R.layout.myprogressdialog);
        dialog.setTitle("Download Progress");

        TextView text = (TextView) dialog.findViewById(R.id.tv);
        text.setText("Downloading file from ... " + file_path);
      //  cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
        text.setText("Starting download...");
        dialog.show();

      //  pb = (ProgressBar)dialog.findViewById(R.id.progress_bar);
       // pb.setProgress(0);
       // pb.setProgressDrawable(
              //  getResources().getDrawable(R.drawable.green_progress));
    }







    public static class DownloadTask extends AsyncTask<String, Integer, String> {
        private ProgressDialog mPDialog;
        private Context mContext;
        private PowerManager.WakeLock mWakeLock;
        private File mTargetFile;
        //Constructor parameters :
        // @context (current Activity)
        // @targetFile (File object to write,it will be overwritten if exist)
        // @dialogMessage (message of the ProgresDialog)
        public DownloadTask(Context context,File targetFile,String dialogMessage) {
            this.mContext = context;
            this.mTargetFile = targetFile;
            mPDialog = new ProgressDialog(context);

            mPDialog.setMessage(dialogMessage);
            mPDialog.setIndeterminate(true);
            mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mPDialog.setCancelable(true);
            // reference to instance to use inside listener
            final DownloadTask me = this;
            mPDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    me.cancel(true);
                }
            });
            Log.i("DownloadTask","Constructor done");
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }
                Log.i("DownloadTask","Response " + connection.getResponseCode());

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(mTargetFile,false);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        Log.i("DownloadTask","Cancelled");
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();

            mPDialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mPDialog.setIndeterminate(false);
            mPDialog.setMax(100);
            mPDialog.setProgress(progress[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("DownloadTask", "Work Done! PostExecute");
            mWakeLock.release();
            mPDialog.dismiss();
            if (result != null)
                Toast.makeText(mContext,"Download error: "+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mContext,"File Downloaded", Toast.LENGTH_SHORT).show();
        }
    }*/

