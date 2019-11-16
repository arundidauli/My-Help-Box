package com.diggingquiz.myquiz.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;
import com.diggingquiz.myquiz.base.BaseActivity;

import com.diggingquiz.myquiz.beans.UserProfileData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
public class UserLoginActivity extends BaseActivity implements ApiCallbackListener {

    private static final String TAG=UserLoginActivity.class.getSimpleName();
    Context context;
    TextView tv_firstLeatter,tv_Signup;
    Button buttonLogin;
    EditText edit_username,editPassword,edit_mobile;
    TextInputLayout textInput_mobile,textInput_password,textInput_name;
    PrefManager prefManager;
    public static UserProfileData userProfileData;
    Button buttonsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = UserLoginActivity.this;
        setContentView(R.layout.activity_user_login);
        init();
        if(!isNetworkAvailable()){
            showAlertDialog("Alert","Please check your Internet connection.");
            return;
        }

        edit_username.setOnTouchListener(this);
        editPassword.setOnTouchListener(this);

//edit_username.setText("DivineDrizzle");
//editPassword.setText("12345");
        //int color = Color.parseColor(Color.parseColor("#AA3456"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                if (TextUtils.isEmpty(edit_username.getText().toString())) {

                    textInput_name.setError("Please enter UserName");

                }
                if (TextUtils.isEmpty(editPassword.getText().toString())) {

                    textInput_password.setError("Please enter Password");

                }

                if (!TextUtils.isEmpty(edit_username.getText().toString()) && !TextUtils.isEmpty(editPassword.getText().toString())) {
                    doLogin();
                    //getNetworkTime();
                }

                break;
            case R.id.buttonsignup:
                startActivity(new Intent(context, SignUpActivity.class));

                break;



        }
    }

    private void init() {
        prefManager=new PrefManager(UserLoginActivity.this);

        edit_username=findViewById(R.id.edit_username);
        editPassword=findViewById(R.id.editPassword);

        edit_username.setText(prefManager.Datadeliver("_id"));
        editPassword.setText(prefManager.Datadeliver("_pass"));
        if(!prefManager.Datadeliver("_id").equalsIgnoreCase("") && !prefManager.Datadeliver("_pass").equalsIgnoreCase("") ){
            doLogin();
        }



        textInput_password=findViewById(R.id.textInput_password);
        textInput_name=findViewById(R.id.textInput_name);

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonsignup=findViewById(R.id.buttonsignup);
        buttonsignup.setOnClickListener(this);
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
        if (v.getId() == R.id.edit_username || v.getId() == R.id.editPassword ) {

            enableFocusOfEditTexts();
        }

        return super.onTouch(v, event);
    }
    private void enableFocusOfEditTexts() {
        edit_username.setFocusable(true);
        edit_username.setFocusableInTouchMode(true);
        //edit_username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        editPassword.setFocusable(true);
        editPassword.setFocusableInTouchMode(true);




    }
    /**
     * This method is called to make a hit with web service for login.
     */
    private void doLogin() {

        showProgressDialog("Authenticating...");
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();

        listKey.add("Username");
        listKey.add("password");


        listValue.add(edit_username.getText().toString());
        listValue.add(editPassword.getText().toString());


        ApiCallingMethods.requestForPost(listKey, listValue, Config.USER_LOGIN, UserLoginActivity.this, this, "Login");

    }


    @Override
    public void onResultCallback(String response, String flag) {
        dismissDialog();
        textInput_name.setErrorEnabled(false);
        textInput_password.setErrorEnabled(false);
        Log.i(TAG, " " + response);
        try {

            if (flag != null && flag.equals("Login")) {
                JSONObject objMain = new JSONObject(response);
                String merssge=objMain.getString("message");
                prefManager.Dataloader("_id",edit_username.getText().toString());
                prefManager.Dataloader("_pass",editPassword.getText().toString());
                //showToast(merssge);


                JSONArray jsonArray =  new JSONArray(objMain.getString("UserProfile"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                     userProfileData = new UserProfileData();
                    userProfileData.setEmail(obj.getString("email"));
                    userProfileData.setActivatedate(obj.getString("activatedate"));
                    userProfileData.setAddress(obj.getString("address"));
                    userProfileData.setUserId(obj.getString("UserId"));
                    userProfileData.setExpiredate(obj.getString("expiredate"));
                    userProfileData.setMembername((obj.getString("membername")));

                    userProfileData.setMemberphoto((obj.getString("memberphoto")));
                    userProfileData.setNoOfQuiz(Integer.valueOf(obj.getString("NoOfQuiz")));
                    userProfileData.setNoOfQuestion(Integer.valueOf(obj.getString("NoOfQuestion")));
                    userProfileData.setNoofcorrrectquestionforpass(Integer.valueOf(obj.getString("noofcorrrectquestionforpass")));
                    userProfileData.setPlanduration(Integer.valueOf(obj.getString("planduration")));

                    userProfileData.setPlandurationtype((obj.getString("plandurationtype")));
                    userProfileData.setPin((obj.getString("pin")));
                    userProfileData.setWinningamount(Double.valueOf(obj.getString("winningamount")));
                    userProfileData.setMobileAlias(obj.getString("MobileAlias"));

                    userProfileData.setPin(obj.getString("pin"));
                    userProfileData.setNoOfQuiz(Integer.valueOf(obj.getString("NoOfQuiz")));
                    userProfileData.setActivatedate(obj.getString("activatedate"));

                    userProfileData.setExpiredate(obj.getString("expiredate"));
                    userProfileData.setUserName(obj.getString("UserName"));

                    userProfileData.setAcc_Name(obj.getString("Accountholdername"));
                    userProfileData.setAccountNo(obj.getString("AccountNo"));
                    userProfileData.setIfscCode(obj.getString("IfscCode"));
                    userProfileData.setBrach_Name(obj.getString("BranchName"));
                    userProfileData.setBank_Name(obj.getString("BankName"));

                    userProfileData.setCheckquizalloted((obj.getString("checkquizalloted")));


                    prefManager.setuserid(obj.getString("UserId"));
                    prefManager.setUserName(obj.getString("UserName"));
                    prefManager.setUserMobile(userProfileData.getMobileAlias());
                    prefManager.setImageUrl(userProfileData.getMemberphoto());
                    prefManager.setIS_PROFILE_CREATED(true);
                    prefManager.setPlanName(userProfileData.getPin());
                    if(userProfileData.getNoOfQuiz()==0){

                    }
                    prefManager.setisQuestionSave(obj.getString("checkquizalloted"));
                    prefManager.setNoOfQuiz(String.valueOf(userProfileData.getNoOfQuiz()));
                    prefManager.setActivatedate(userProfileData.getActivatedate());
                    prefManager.setExpireDate(userProfileData.getExpiredate());
                    prefManager.setWalletAmount(obj.getString("WalletAmount"));
                    startActivity(new Intent(context, HomeMainActivity.class));
                    finish();
                    prefManager.setFirstTimeLogin(false);

                     //overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                }
            }

        } catch (Exception d) {
            d.printStackTrace();
        }
    }

    @Override
    public void onErrorCallback(VolleyError error) {
        dismissDialog();
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            showAlertDialog("Ah!", "It seems that your internet connection is too slow or not found.");
        } else if (error instanceof AuthFailureError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof ServerError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof NetworkError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        } else if (error instanceof ParseError) {
            showAlertDialog("Ah!", "Something went wrong, please try again later");
        }
    }

    public void getNetworkTime(){
        TimeZone tz=TimeZone.getTimeZone("GMT+05:30");
        Calendar c=Calendar.getInstance(tz);
        String time=String.format("%02d",c.get(Calendar.HOUR_OF_DAY))+":"+
                String.format("%02d",c.get(Calendar.MINUTE))+":"+
                String.format("%02d",c.get(Calendar.SECOND))+":"+
                String.format("%03d",c.get(Calendar.MILLISECOND));
        showToast(time);
    }
   /* public String getTime() {
        try{
            //Make the Http connection so we can retrieve the time
            HttpClient httpclient = new DefaultHttpClient();
            // I am using yahoos api to get the time
            HttpResponse response = httpclient.execute(new
                    HttpGet("http://developer.yahooapis.com/TimeService/V1/getTime?appid=YahooDemo"));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                // The response is an xml file and i have stored it in a string
                String responseString = out.toString();
                Log.d("Response", responseString);
                //We have to parse the xml file using any parser, but since i have to
                //take just one value i have deviced a shortcut to retrieve it
                int x = responseString.indexOf("<Timestamp>");
                int y = responseString.indexOf("</Timestamp>");
                //I am using the x + "<Timestamp>" because x alone gives only the start value
                Log.d("Response", responseString.substring(x + "<Timestamp>".length(),y) );
                String timestamp =  responseString.substring(x + "<Timestamp>".length(),y);
                // The time returned is in UNIX format so i need to multiply it by 1000 to use it
                Date d = new Date(Long.parseLong(timestamp) * 1000);
                Log.d("Response", d.toString() );
                return d.toString() ;
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        }catch (ClientProtocolException e) {
            Log.d("Response", e.getMessage());
        }catch (IOException e) {
            Log.d("Response", e.getMessage());
        }
        return null;
    }*/
   public void showAlertDialogwithAction(String title, String message, final String action) {
       AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
       // builder.setCancelable(false);
       builder.setTitle(title);
       builder.setMessage(message);
       builder.setCancelable(false);
       builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

           @Override
           public void onClick(DialogInterface dialog, int which) {
               Intent intent=new Intent(action);
               startActivityForResult(intent,160);
               dialog.dismiss();
           }
       });
       //2. now setup to change color of the button
       AlertDialog alert = builder.create();



       alert.show();
       Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
       //Set positive button background
       //pbutton.setBackgroundColor(Color.YELLOW);
       //Set positive button text color
       pbutton.setTextColor(Color.MAGENTA);
   }

}
