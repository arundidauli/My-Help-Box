package com.techtutz.lovecalcuator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import pl.droidsonroids.gif.GifTextView;

public class UsernameActivity extends AppCompatActivity {
    private EditText phoneIDTextInputEditText1,phoneIDTextInputEditText;
    private TextView Spinner;
    private GifTextView gif;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
       // gif

        phoneIDTextInputEditText1=findViewById(R.id.phoneIDTextInputEditText1);
        phoneIDTextInputEditText=findViewById(R.id.phoneIDTextInputEditText);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String yourname= Objects.requireNonNull(phoneIDTextInputEditText.getText()).toString();
                final String partnername= Objects.requireNonNull(phoneIDTextInputEditText1.getText()).toString();

                if (yourname.length()<4 && partnername.length()<4){
                    Toast.makeText(getApplicationContext(),"Enter Valid Name",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("name1", yourname);
                    intent.putExtra("name2", partnername);
                    startActivity(intent);
                }

            }
        });
    }
}
