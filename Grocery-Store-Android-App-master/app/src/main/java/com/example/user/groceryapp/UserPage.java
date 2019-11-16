package com.example.user.groceryapp;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UserPage extends AppCompatActivity {
    EditText budget;
    Button submit;
    DBConnection dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        dbc = new DBConnection(this);
        budget = (EditText) findViewById(R.id.budget);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    startShop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startShop() {
        //read values from fields
        String s1 = budget.getText().toString();
        if (s1.trim().length() == 0 || s1 == null) {
            Toast.makeText(UserPage.this, "Please enter budget amount", Toast.LENGTH_LONG).show();
            budget.requestFocus();
            return;
        }
        if (!s1.startsWith("$")) {
            Toast.makeText(UserPage.this, "Please prefix budget with $", Toast.LENGTH_LONG).show();
            budget.requestFocus();
            return;
        }
        s1 = s1.trim();
        String budget_value = s1.substring(1,s1.length());
        double budgetdata = 0;
        try{
            budgetdata = Double.parseDouble(budget_value);
        }catch(NumberFormatException nfe){
            Toast.makeText(UserPage.this, "Budget value must be numeric only", Toast.LENGTH_LONG).show();
            budget.requestFocus();
            return;
        }
        ArrayList<ShopData> list = dbc.getShopList(budgetdata);
        if(list.size() > 0){
            Intent intent = new Intent(UserPage.this,ViewShopList.class);
            intent.putExtra("budget",budgetdata+"");
            startActivity(intent);
        }else{
            Toast.makeText(UserPage.this, "No shopping list found for given budget", Toast.LENGTH_LONG).show();
        }
    }
}

