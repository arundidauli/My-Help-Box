package com.maxtyoutube_dl.youtubedl;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    EditText LinkEdit;
    EditText NameEdit;
    CheckBox Checkbox;
    private Context mContext=MainActivity.this;

    private static final int REQUEST = 112;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkEdit = findViewById(R.id.textUrl);
        NameEdit = findViewById(R.id.textName);
        Checkbox = findViewById(R.id.checkBox);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                //do here
            }
        } else {
            //do here
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void DownloadVid(String link, String filename, String path) throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest(link, path);
        if(Checkbox.isChecked()){request.setOption("extract-audio"); }
        YoutubeDL.execute(request);

    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void convert(View v) throws YoutubeDLException {
        String youtubeLink = LinkEdit.getText().toString();
        if ((youtubeLink != null)
                && (youtubeLink.contains("://youtu.be/") || youtubeLink.contains("youtube.com/watch?v="))) {

            String filename = NameEdit.getText().toString();
            String path=null;
            if(PackageManager.PERMISSION_GRANTED==ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                path = String.valueOf(new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)),"/"));
                Log.i("Path",path);
            }else{
                hasPermissions(mContext);
            }

          //  String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            /*String storageDir = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //RUNTIME PERMISSION Android M
                if(PackageManager.PERMISSION_GRANTED==ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    storageDir = String.valueOf(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "myPhoto"));
                    Log.i("Path",storageDir);
                }else{
                    hasPermissions(mContext);
                }

            }
*/
            DownloadVid(youtubeLink, filename, path);
        }

        else {
                Context context = getApplicationContext();
                CharSequence text = "Bad URL!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }


}





