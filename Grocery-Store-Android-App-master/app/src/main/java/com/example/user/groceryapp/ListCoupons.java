package com.example.user.groceryapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class ListCoupons extends AppCompatActivity {
    ArrayList<String> coupons;
    ListView list;
    DBConnection dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcoupon);
        dbc = new DBConnection(this);
        coupons = dbc.getCoupons();
        String arr[] = new String[coupons.size()];
        for (int i = 0; i < coupons.size(); i++) {
            String temp[] = coupons.get(i).split("#");
            arr[i] = temp[1];
        }
        CouponList adapter = new CouponList(ListCoupons.this, arr);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp[] = coupons.get(position).split("#");
                StringBuilder buffer = new StringBuilder();
                buffer.append("Unique ID : "+temp[0]+"\n");
                buffer.append("Coupon : "+temp[1]+"\n");
                buffer.append("Product List : "+temp[2]+"\n");
                buffer.append("Total Amount : "+temp[3]+"\n");
                buffer.append("Discount : "+temp[4]+"\n");
                Toast.makeText(getBaseContext(),buffer.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
