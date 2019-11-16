package com.diggingquiz.myquiz.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OldDateSelect extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    String dateString,dateStringYear;
    Context context;
    DatePickerDialog datepic;
    DateFlagInterface dateSelectinterface;
    String flag="";
public void setContext(Context context,DateFlagInterface dateinterface,String flag){
    this.context=context;
    this.dateSelectinterface=dateinterface;
    this.flag=flag;



}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datepic = new DatePickerDialog(context, this, year, month, day);
        //setMinimumDate(c,datepic);
        setMaximumDate(c,datepic);
        return datepic;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //displayCurrentTime.setText("Selected date: " + String.valueOf(year) + " - " + String.valueOf(month) + " - " + String.valueOf(day));
        dateString = "" + String.valueOf(day) + " " + String.valueOf(month) + " ";
        //formate Date  in Day Month Formate
        // Date d = new Date(year, month, day);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        Date d = calendar.getTime();

        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "dd/MMM/yyyy");
        //this formate for get Day Like Sunday
          /*  SimpleDateFormat dateForDay = new SimpleDateFormat(
                    "EEEE");*/


        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //cal.add(Calendar.DATE, -1);

        dateString = dateFormatter.format(d);
        dateStringYear = dateString + " " + year;

        dateSelectinterface.getSelectDate(dateString,flag);
        //textviewSelectdate.setText(dateString);

    }
        public void setMinimumDate (Calendar minDate,DatePickerDialog datepic){
            //minDate.add(Calendar.DATE, 1);
            datepic.getDatePicker().setMinDate(minDate.getTimeInMillis());
        }

        public void setMaximumDate (Calendar maxtime,DatePickerDialog datepic){
            //maxtime.add(Calendar.YEAR, 1);
            //datepic.getDatePicker().setMaxDate(maxtime.getTimeInMillis());
            datepic.getDatePicker().setMaxDate(maxtime.getTimeInMillis());
        }

        public String getDate(){
        return dateString;

        }

    }


