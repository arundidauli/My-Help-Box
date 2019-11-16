package com.example.user.groceryapp;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends AppCompatActivity {
    EditText user, pass;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        sign = (Button) findViewById(R.id.signin);
        sign.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login() {
        //read values from fields
        String s1 = user.getText().toString();
        String s2 = pass.getText().toString();
        if (s1.trim().length() == 0 || s1 == null) {
            Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        if (s2.trim().length() == 0 || s2 == null) {
            Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        if (s1.equals("admin") && s2.equals("admin")) {
            Intent intent = new Intent(Login.this,AdminPage.class);
            startActivity(intent);
        } else {
            //show login fail
            Toast.makeText(Login.this, "login failed", Toast.LENGTH_LONG).show();
        }
    }
}

