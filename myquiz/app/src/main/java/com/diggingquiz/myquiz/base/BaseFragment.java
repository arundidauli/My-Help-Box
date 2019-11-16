package com.diggingquiz.myquiz.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.beans.QuizeListData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ranjeet_awaaz on 8/9/2017.
 */

public class BaseFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    private ProgressDialog progressDialog;
    ArrayList<Date> satSunArraylist;
    ArrayList<QuizeListData> quizeListDataArrayList;
    public static ArrayList<QuizeListData> quizeFilterArrayList;
    ArrayList<QuizeListData> deletedArrayList;

    int QuizeNo = 0;

    int count = 0;
    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    /**
     * This method will update the color of status bar, when we will call this method then we need to pass the value of hexa color code.
     *
     * @param color
     */

    public void updateStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

  /*  *//**
     * This method will update the color of toolbar
     *//*
*/


 /*   public boolean checkLocationPermission() {
        // displayLocationSettingsRequest(getActivity());
        int result = ContextCompat.checkSelfPermission(getActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED &&
            result1 == PackageManager.PERMISSION_GRANTED;


    }*/

    /**
     * Show alert dialog
     *
     * @param title
     * @param message
     */
    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
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
        AlertDialog alert = builder.create();
        alert.show();
    }

   /* *//**
     * Requesting for runtime Location permissions
     *//*
    public void requestLocationPermission() {

        ActivityCompat.requestPermissions(getActivity(), new
            String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }*/


  /*  *//**
     * Requesting for runtime Call permissions
     *//*
    public void requestCallPermission() {

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 200);
    }*/

/*    *//**
     * Check for Call Permission
     *
     * @return
     *//*


    public boolean checkCallPermission() {
        // displayLocationSettingsRequest(getActivity());
        int result = ContextCompat.checkSelfPermission(getActivity(),
            Manifest.permission.CALL_PHONE);

        return result == PackageManager.PERMISSION_GRANTED;


    }*/

    //this method for location setting
/*    public void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
            .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        //Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(getActivity(), 0x1);
                        } catch (IntentSender.SendIntentException e) {
                            // Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }*/


    /**
     * Called for showing Progress dialog
     */
    public void showProgressDialog(String text) {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage(text);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * Called for showing Progress dialog
     */
    public void showProgressDialogWithTheme(String text, int theme) {
        progressDialog = new ProgressDialog(getActivity(), theme);
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

    public   Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public   void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    public ArrayList<QuizeListData> weekEndCount(Date d1, Date d2,Integer totalQuize) {
        satSunArraylist=new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int sundays = 0;
        int saturday = 0;

        while (!c1.after(c2)) {
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                saturday++;
                try {
                    DateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                    Date d = readFormat.parse(readFormat.format(c1.getTime()));

                    satSunArraylist.add(d);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays++;
                try {
                    DateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                    Date d = readFormat.parse(readFormat.format(c1.getTime()));

                    satSunArraylist.add(d);
                } catch (Exception e) {
                    e.getMessage();
                }

            }

            c1.add(Calendar.DATE, 1);

        }

        //setQuizeList();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 0);
        dt = c.getTime();

        Calendar calendar = getCalendarForNow();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setTimeToEndofDay(calendar);
        Date firstdate = calendar.getTime();
        quizeListDataArrayList=new ArrayList<>();
        count=0;
        QuizeNo=totalQuize;
      setQuizeList(satSunArraylist);

        //FilterData(simpleDateFormat.format(firstdate),simpleDateFormat.format(dt));
        return quizeListDataArrayList;
    }

    public ArrayList<QuizeListData> setQuizeList(ArrayList<Date> satSunArraylists) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        SimpleDateFormat dfTime=new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
        for (Date date : satSunArraylists) {
            try {
            count++;
            QuizeListData quizeListData=new QuizeListData();
            quizeListData.setQuizeName("Quiz");
            quizeListData.setWinningamount(300);
            quizeListData.setDate(simpleDateFormat.format(date));
            quizeListData.setQuizeDateTime(simpleDateFormat.format(date)+" 06:00:00 PM");

                Date dateTime=dfTime.parse(simpleDateFormat.format(date)+" 06:30:00 PM");
                long millis = dateTime.getTime();
                quizeListData.setExpireTime(millis);

            quizeListDataArrayList.add(quizeListData);
            if (QuizeNo == count) {
                break;
            }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (QuizeNo > count) {
            setQuizeList( satSunArraylists);
        }
        return  quizeListDataArrayList;
    }


    public   void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
