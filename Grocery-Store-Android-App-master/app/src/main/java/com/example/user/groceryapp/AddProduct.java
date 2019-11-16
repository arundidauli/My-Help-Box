package com.example.user.groceryapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddProduct extends AppCompatActivity {
    EditText pname, price;
    Button submit;
    DBConnection dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        dbc = new DBConnection(this);
        pname = (EditText) findViewById(R.id.pname);
        price = (EditText) findViewById(R.id.price);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    addProduct();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addProduct() {
        //read values from fields
        String s1 = pname.getText().toString();
        String s2 = price.getText().toString();
        if (s1.trim().length() == 0 || s1 == null) {
            Toast.makeText(AddProduct.this, "Please enter product name", Toast.LENGTH_LONG).show();
            pname.requestFocus();
            return;
        }
        if (s2.trim().length() == 0 || s2 == null) {
            Toast.makeText(AddProduct.this, "Please enter product price", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        if (!s2.startsWith("$")) {
            Toast.makeText(AddProduct.this, "Please prefix price with $", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        s2 = s2.trim();
        String price_value = s2.substring(1,s2.length());
        double pricedata = 0;
        try{
            pricedata = Double.parseDouble(price_value);
        }catch(NumberFormatException nfe){
            Toast.makeText(AddProduct.this, "Price value must be numeric only", Toast.LENGTH_LONG).show();
            price.requestFocus();
            return;
        }
        boolean flag = dbc.addProduct(s1,s2);
        if (!flag) {
            Toast.makeText(AddProduct.this, "Product details added", Toast.LENGTH_LONG).show();
            pname.setText("");
            price.setText("");
        } else {
            //show login fail
            Toast.makeText(AddProduct.this, "Product already exists", Toast.LENGTH_LONG).show();
        }
    }
}

