package com.example.user.groceryapp;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.annotation.SuppressLint;
import android.widget.TextView;

public class CouponList extends ArrayAdapter<String> {
    private final Activity context;
    String names[];
    public CouponList(Activity context,String names[]) {
        super(context, R.layout.activity_couponlist,names);
        //local to global value initialization
        this.context = context;
        this.names=names;
    }
    //automatically call this method to show entire list of array in list view
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.activity_couponlist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(names[position]);
        return rowView;
    }
}
