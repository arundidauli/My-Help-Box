package com.example.user.groceryapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBConnection dbc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbc = new DBConnection(this);
        initClickListner();
    }

    private void initClickListner() {
        Button director = (Button) findViewById(R.id.admin);
        director.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        Button students = (Button) findViewById(R.id.user);
        students.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserPage.class);
                startActivity(intent);
            }
        });
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                dbc.reset();
                Toast.makeText(MainActivity.this, "Database reset", Toast.LENGTH_LONG).show();
            }
        });
    }
}
