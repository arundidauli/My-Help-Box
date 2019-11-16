package com.example.user.groceryapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class DeleteCoupons extends AppCompatActivity {
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
            arr[i] = temp[1]+" "+temp[2];
        }
        CouponList adapter = new CouponList(DeleteCoupons.this, arr);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp[] = coupons.get(position).split("#");
                boolean flag = dbc.deleteCoupon(temp[0].trim());
                if(flag) {
                    Toast.makeText(getBaseContext(), "Coupon deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeleteCoupons.this,AdminPage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Error in deleteing coupon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
