package com.example.user.groceryapp;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddCoupon extends AppCompatActivity {
    EditText coupon,discount;
    Button submit;
    DBConnection dbc;
    ProductList plist;
    LinkedHashMap<String,String> hm;
    ArrayList<String> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcoupon);

        dbc = new DBConnection(this);

        products = dbc.getProductList();
        hm = dbc.checkCoupon();
        coupon = (EditText) findViewById(R.id.coupon);
        plist = (ProductList) findViewById(R.id.plist);
        plist.setItems(products);
        discount = (EditText) findViewById(R.id.discount);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    saveCoupon();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveCoupon() {
        //read values from fields
        String s1 = coupon.getText().toString();
        List<String> selected = plist.getSelectedStrings();
        String s3 = discount.getText().toString();
        if (s1.trim().length() == 0 || s1 == null) {
            Toast.makeText(AddCoupon.this, "Please enter coupon", Toast.LENGTH_LONG).show();
            coupon.requestFocus();
            return;
        }
        if (selected.size() == 0) {
            Toast.makeText(AddCoupon.this, "Please choose products", Toast.LENGTH_LONG).show();
            plist.requestFocus();
            return;
        }
        boolean flag = false;
        String name = "none";
        for(Map.Entry<String,String> me : hm.entrySet()){
            String key = me.getKey();
            String value = me.getValue();
            String arr[] = value.split(",");
            for(int i=0;i<arr.length;i++){
                if(selected.contains(arr[i])){
                    flag = true;
                    name = arr[i];
                    break;
                }
            }
        }
        if (flag) {
            Toast.makeText(AddCoupon.this, "Choosen product "+name+" already exists in other coupon", Toast.LENGTH_LONG).show();
            plist.requestFocus();
            return;
        }
        if (s3.trim().length() == 0 || s3 == null) {
            Toast.makeText(AddCoupon.this, "Please enter discount amount", Toast.LENGTH_LONG).show();
            discount.requestFocus();
            return;
        }
        if (!s3.startsWith("$")) {
            Toast.makeText(AddCoupon.this, "Please prefix discount with $", Toast.LENGTH_LONG).show();
            discount.requestFocus();
            return;
        }
        s3 = s3.trim();
        String discount_value = s3.substring(1,s3.length());
        double discountdata = 0;
        try{
            discountdata = Double.parseDouble(discount_value);
        }catch(NumberFormatException nfe){
            Toast.makeText(AddCoupon.this, "Discount value must be numeric only", Toast.LENGTH_LONG).show();
            discount.requestFocus();
            return;
        }
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<selected.size();i++){
            buffer.append(selected.get(i)+",");
        }
        if(buffer.length() > 0)
            buffer.deleteCharAt(buffer.length()-1);
        boolean flag_status = dbc.addCoupon(s1,buffer.toString(),s3);
        if (!flag_status) {
            Toast.makeText(AddCoupon.this, "Coupon details added", Toast.LENGTH_LONG).show();
            coupon.setText("");
            discount.setText("");
        } else {
            //show login fail
            Toast.makeText(AddCoupon.this, "Entered coupon already exists", Toast.LENGTH_LONG).show();
        }
    }
}

