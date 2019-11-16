package com.example.user.groceryapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
public class ViewShopList extends AppCompatActivity {
    ArrayList<ShopData> shoplist;
    ListView list;
    DBConnection dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcoupon);
        String budget =  getIntent().getExtras().getString("budget");
        double budget_amount = Double.parseDouble(budget.trim());
        dbc = new DBConnection(this);
        shoplist = dbc.getShopList(budget_amount);
        java.util.Collections.sort(shoplist,new ShopData());
        String arr[] = new String[shoplist.size()];
        int k = 0;
        for (int i = shoplist.size()-1; i >= 0; i--) {
            ShopData sd = shoplist.get(i);
            arr[k] = sd.getProductList()+"; $"+sd.getDiscount()+" Off";
            k = k + 1;
        }
        CouponList adapter = new CouponList(ViewShopList.this, arr);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopData sd = shoplist.get(position);
                StringBuilder sb = new StringBuilder();
                sb.append("Product List : " + sd.getProductList() + "\n");
                sb.append("Total Amount : " + sd.getTotal() + "\n");
                sb.append("Discount     : " + sd.getDiscount() + "\n");
                sb.append("Price after discount : " + (sd.getTotal() - sd.getDiscount()));
                Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
