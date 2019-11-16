package com.diggingquiz.myquiz.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.activities.UserLoginActivity;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.diggingquiz.myquiz.base.BaseActivity;


/**
 * Created by Tanuj yadav on 16/03/2018.
 */

public class SignUpActivity extends BaseActivity {
    EditText edit_emailid, edit_username, edit_password, edit_Mobileno, edit_companynam, edit_confirmpassword, edit_companyname,edit_address,edit_website,edit_GstNo;
    Button btn_create;
    PrefManager prefManager;
    TextView tvschoollogo,tv_serviceType;
    Bitmap bmp, newbitmap;
    ImageView img_profile_edit, imageView_change_image;

    private byte[] bytes;
    String formattedDate = "";
    int maxid;
    TextView tv_schoolid;
    String imageurl = "";
    String update = "";
    Dialog dialog;
    Context context;
    ToggleButton toggleGst;
    LinearLayout linerGst;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        prefManager = new PrefManager(SignUpActivity.this);
        initViews();
        Button btn_create=findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, UserLoginActivity.class));
            }
        });


    }

    private void initViews() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);


        if (v.getId() == R.id.btn_create) {



        }

    }





}