package com.example.user.groceryapp;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class AdminPage extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initClickListner();
    }
    private void initClickListner()	{
        Button addproduct = (Button) findViewById(R.id.addproduct);
        addproduct.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AddProduct.class);
                startActivity(intent);
            }
        });
        Button addcoupon = (Button) findViewById(R.id.addcoupon);
        addcoupon.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,AddCoupon.class);
                startActivity(intent);
            }
        });

        Button listproducts = (Button) findViewById(R.id.listproducts);
        listproducts.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,ListUpdatePrice.class);
                startActivity(intent);
            }
        });

        Button listcoupons = (Button) findViewById(R.id.listcoupons);
        listcoupons.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,ListCoupons.class);
                startActivity(intent);
            }
        });

        Button deletecoupons = (Button) findViewById(R.id.deletecoupons);
        deletecoupons.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,DeleteCoupons.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               Intent intent = new Intent(AdminPage.this,MainActivity.class);
               startActivity(intent);
            }
        });

    }




}
