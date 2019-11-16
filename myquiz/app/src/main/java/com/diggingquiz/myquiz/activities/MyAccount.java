package com.diggingquiz.myquiz.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.diggingquiz.myquiz.base.BaseActivity;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyAccount extends BaseActivity {

 Context context;
TextView tv_Usermobile,tv_Username;
Button buttonLogin;
EditText edit_username,editEmail,edit_mobile;
    TextInputLayout textInput_mobile,textInput_email,textInput_name;
    LinearLayout liner_myWallet;
    CircleImageView img_profile_edit;
    public PrefManager prefManager;
    TextView tv_myWallet,tv_planName,tv_noofQuize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MyAccount.this;



        setContentView(R.layout.activity_myaccount);

        init();

        SetData();

        //int color = Color.parseColor(Color.parseColor("#AA3456"));

    }
    public void SetData(){
        Picasso.with(context).load(prefManager.getImageUrl()).placeholder(R.drawable.girl).into(img_profile_edit);
        tv_Username.setText(prefManager.getUserName());
        tv_Usermobile.setText(prefManager.getUserMobile());
        tv_planName.setText(prefManager.getPlanName());
        tv_noofQuize.setText("Total quize : "+prefManager.getNoOfQuiz());
        tv_myWallet.setText(prefManager.getWalletAmount());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.liner_myWallet:
                startActivity(new Intent(MyAccount.this, MyWallet.class));

                break;
        }
    }

    private void init() {

        img_profile_edit=findViewById(R.id.img_profile_edit);



        tv_Username=findViewById(R.id.tv_Username);

        tv_Usermobile=findViewById(R.id.tv_Usermobile);

        liner_myWallet=findViewById(R.id.liner_myWallet);
        liner_myWallet.setOnClickListener(this);

        tv_myWallet=findViewById(R.id.tv_myWallet);
        tv_planName=findViewById(R.id.tv_planName);
        tv_noofQuize=findViewById(R.id.tv_noofQuize);;
         prefManager=new PrefManager(MyAccount.this);


    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {


    }
    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "New to Goldurs ?  ");
        spanTxt.append("SIGN UP");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
              // startActivity(new Intent(context, Main2Activity.class));
               // overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.statusbar));
            }
        }, spanTxt.length() - "SIGN UP".length(), spanTxt.length(), 0);
        //spanTxt.append(" and");
        //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        //spanTxt.append(" Privacy policy");
       /* spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(context, PrivacyPolicy.class));
                overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.statusbar));
            }
        }, spanTxt.length() - " Privacy Policy".length(), spanTxt.length(), 0);*/
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {


        return super.onTouch(v, event);
    }
    private void enableFocusOfEditTexts() {
        edit_username.setFocusable(true);
        edit_username.setFocusableInTouchMode(true);
        edit_username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        editEmail.setFocusable(true);
        editEmail.setFocusableInTouchMode(true);

        edit_mobile.setFocusable(true);
        edit_mobile.setFocusableInTouchMode(true);


    }
}
