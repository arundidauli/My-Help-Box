package com.diggingquiz.myquiz.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;


import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.diggingquiz.myquiz.Utils.Constants.*;


/**
 * Created by Ankit Chaudhary on 8/9/2017.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private ProgressDialog progressDialog;

    PrefManager prefManager;





    @Override
    public void onClick(View v) {

    }

    /**
     * Called for showing Progress dialog
     */
    public void showProgressDialog(String text) {
        progressDialog = new ProgressDialog(BaseActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage(text);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * Dismiss Progress dialog
     */

    public void dismissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * Show alert dialog
     *
     * @param title
     * @param message
     */



    public void showAlertDialog(String title, String message ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        // builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        //2. now setup to change color of the button
        AlertDialog alert = builder.create();



        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        //Set positive button background
        //pbutton.setBackgroundColor(Color.YELLOW);
        //Set positive button text color
        pbutton.setTextColor(Color.MAGENTA);
    }

    /**
     * Show alert dialog with positive button clicked
     *
     * @param title
     * @param message
     */
    public void showAlertDialogWithFinish(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        // builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void showToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    public boolean checkLocationPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;


    }

    /**
     * Requesting for runtime Location permissions
     */
    public void requestLocationPermission() {

        ActivityCompat.requestPermissions(BaseActivity.this, new
                String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }


    /**
     * Converting bitmap into base 64 string.
     *
     * @param bmp
     * @return
     */
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    /**
     * Checking permission for READ External Storage and Camera
     *
     * @return
     */
    public boolean checkImagePermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                CAMERA);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requesting for runtime permissions
     */
    public void requestImagePermission() {
        ActivityCompat.requestPermissions(BaseActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, CAMERACONS);
    }

    /**
     * Click on change profile
     */

    public void changeProfileImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle("Profile Photo");
        builder.setItems(new CharSequence[]
                        {"Choose from gallery", "Capture from camera", "Cancel"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                Intent i = new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                startActivityForResult(i, GALLERY);

                                break;
                            case 1:
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERACONS);
                                break;
                            case 2:
                                break;

                        }
                    }
                });
        builder.create().show();
    }

    /**
     * Click on change profile
     */

    public void openImageCameraGallery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile Photo");
        builder.setItems(new CharSequence[]
                        {"Choose from gallery", "Capture from camera", "Cancel"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                Intent i = new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(i, GALLERY);

                                break;
                            case 1:
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERACONS);
                                break;
                            case 2:
                                break;

                        }
                    }
                });
        builder.create().show();
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = "";
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        } catch (Exception r) {
            r.getMessage();
        }

        return Uri.parse(path);


    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                this.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    //This Methhod for send Notification using Firebase Database Data Send Data in bundel
/*    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");*/


    public void requestSmsPermission() {
        String permission = Manifest.permission.READ_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, SMSPERMISSION);
        }
    }

    public boolean isSmsPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestSmsSendPermission() {
        String permission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, SMSSENDPERMISSION);
        }
    }

    public boolean isSmsSendPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERACONS) {
            if (grantResults.length > 0) {
                boolean StoragePermission = grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED;
                boolean RecordPermission = grantResults[1] ==
                        PackageManager.PERMISSION_GRANTED;

                if (StoragePermission && RecordPermission) {
                    openImageCameraGallery();
                } else {
                    showAlertDialog("Permission Requested", "Please allow these permission");

                }
            }

        }

    }


    public boolean CheckDate(Date min, Date max, Date FilterDate) {
        // assume these are set to something
        // the date in question
        Calendar cal = Calendar.getInstance();
        cal.setTime(min);
        cal.add(Calendar.DATE, -1);
        Date oneBeforeDays = cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(max);
        cal2.add(Calendar.DATE, 1);
        Date NextDays = cal2.getTime();

        return FilterDate.after(oneBeforeDays) && FilterDate.before(NextDays);
    }
    public static boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
