package com.example.user.groceryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListUpdatePrice extends AppCompatActivity {
    EditText price;
    Button submit;
    DBConnection dbc;
    ProductList plist;
    ArrayList<String> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprice);

        dbc = new DBConnection(this);

        products = dbc.getProductList();
        plist = (ProductList) findViewById(R.id.pname);
        plist.setItems(products);
        price = (EditText) findViewById(R.id.updateprice);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    updatePrice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updatePrice() {
        //read values from fields
        List<String> selected = plist.getSelectedStrings();
        String s1 = price.getText().toString();
        if (selected.size() == 0) {
            Toast.makeText(ListUpdatePrice.this, "Please choose products", Toast.LENGTH_LONG).show();
            plist.requestFocus();
            return;
        }

        if (s1.trim().length() == 0 || s1 == null) {
            Toast.makeText(ListUpdatePrice.this, "Please enter new price", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        if (!s1.startsWith("$")) {
            Toast.makeText(ListUpdatePrice.this, "Please prefix price with $", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        s1 = s1.trim();
        String price_value = s1.substring(1,s1.length());
        double pricedata = 0;
        try{
            pricedata = Double.parseDouble(price_value);
        }catch(NumberFormatException nfe){
            Toast.makeText(ListUpdatePrice.this, "Price value must be numeric only", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<selected.size();i++){
            buffer.append(selected.get(i)+",");
        }
        if(buffer.length() > 0)
            buffer.deleteCharAt(buffer.length()-1);
        boolean flag_status = dbc.updatePrice(s1,buffer.toString());
        if (flag_status) {
            Toast.makeText(ListUpdatePrice.this, "Price details updated", Toast.LENGTH_LONG).show();
            price.setText("");
        } else {
            //show login fail
            Toast.makeText(ListUpdatePrice.this, "Error in updating price", Toast.LENGTH_LONG).show();
        }
    }
}


