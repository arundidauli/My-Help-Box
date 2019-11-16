package com.diggingquiz.myquiz.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;
import com.diggingquiz.myquiz.base.BaseActivity;
import com.diggingquiz.myquiz.beans.QuizeListData;
import com.diggingquiz.myquiz.fragments.CompleteQuizFragment;
import com.diggingquiz.myquiz.fragments.CurrentQuizFragment;
import com.diggingquiz.myquiz.fragments.UpcomingQuiz;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class HomeMainActivity extends BaseActivity implements ApiCallbackListener {

 Context context;
TextView tv_logout,tv_myaccount;
Button buttonLogin;
EditText edit_username,editEmail,edit_mobile;
    TextInputLayout textInput_mobile,textInput_email,textInput_name,tv_my_account;
    TabLayout tabLayout;
    ViewPager viewPager;
    private Integer positiion = 0;
    LinearLayout linermyAcount;
    ImageView img_edit;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomeMainActivity.this;

        prefManager=new PrefManager(HomeMainActivity.this);

        setContentView(R.layout.activity_homemain);

        init();


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {
                positiion = LayoutTab.getPosition();
                viewPager.setCurrentItem(positiion);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {
            }
        });
        if(!isNetworkAvailable()){
            showAlertDialogwithAction("Alert","Please check your Internet connection.", Settings.ACTION_DATA_USAGE_SETTINGS);
            return;
        }
        if(!isTimeAutomatic(HomeMainActivity.this)){
            showAlertDialogwithAction("Alert","Please Set Date Time same As Network.", Settings.ACTION_DATE_SETTINGS);

        }
        //int color = Color.parseColor(Color.parseColor("#AA3456"));
        //currentQuizeApi();
       /// String sjnsd= UserLoginActivity.userProfileData.getAcc_Name();
       // showToast(sjnsd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit:
                startActivity(new Intent(HomeMainActivity.this, UserAccount.class));

                break;
            case R.id.linermyAcount:
                startActivity(new Intent(HomeMainActivity.this, UserAccount.class));

                break;
            case R.id.tv_logout:
                doLogOut();

                break;
        }
    }

    private void init() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        System.out.println("Current date => " + formattedDate);

       /* edit_username=findViewById(R.id.edit_username);
        editEmail=findViewById(R.id.editEmail);
        edit_mobile=findViewById(R.id.edit_mobile);

        textInput_mobile=findViewById(R.id.textInput_mobile);
        textInput_email=findViewById(R.id.textInput_email);
        textInput_name=findViewById(R.id.textInput_name);*/

        img_edit=findViewById(R.id.img_edit);
        img_edit.setOnClickListener(this);
        tabLayout =  findViewById(R.id.tab_layout);
        viewPager =  findViewById(R.id.pager);

        linermyAcount=  findViewById(R.id.linermyAcount);
        tv_logout=  findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(this);

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==150){
            if(!isTimeAutomatic(HomeMainActivity.this)){
                showAlertDialogwithAction("Alert","Please Set Date Time same As Network.", Settings.ACTION_DATE_SETTINGS);

            }

        }

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
       /* if (v.getId() == R.id.edit_username || v.getId() == R.id.editEmail || v.getId() == R.id.edit_mobile) {

            enableFocusOfEditTexts();
        }*/

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
    private void setupViewPager(ViewPager viewPager) {
        Fragment fragment1,fragment2,fragment3,fragment4,fragment5 = null;


        fragment1=new CurrentQuizFragment();
        fragment2=new UpcomingQuiz();
        fragment3=new CompleteQuizFragment();


        HomeMainActivity.ViewPagerAdapter adapter = new HomeMainActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragment1, "Current");
        adapter.addFragment(fragment2, "Upcoming");
        adapter.addFragment(fragment3, "Complete");


/*
        CoachingProfileFragment.ViewPagerAdapter adapter = new CoachingProfileFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(fragment1, "About Us");
        adapter.addFragment(fragment2, "Gallery");
        adapter.addFragment(fragment3, "Courses");
        adapter.addFragment(fragment4, "Contact Us");*/


        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResultCallback(String response, String flag) {
        Log.e("Responce",response);
        dismissDialog();
        try {
            if(flag!=null && flag.equalsIgnoreCase("CurentQuiz")){
                JSONObject objMain = null;

                objMain = new JSONObject(response);

                String merssge = objMain.getString("message");

                //Toast.makeText(getActivity(), objMain.getString("message"), Toast.LENGTH_SHORT).show();
                JSONArray jsoncurrentquiz = new JSONArray(objMain.getString("CurrentQuizList"));
                for (int i = 0; i < jsoncurrentquiz.length(); i++) {
                    JSONObject obj = jsoncurrentquiz.getJSONObject(i);
                    QuizeListData quizeListData=new QuizeListData();
                    quizeListData.setDate(obj.getString("date"));
                    quizeListData.setExpireTime(Long.valueOf(obj.getString("expireTime")));
                    quizeListData.setQuizeDateTime(obj.getString("quizeDateTime"));
                    quizeListData.setQuizeName(obj.getString("quizeName"));
                    quizeListData.setWinningamount(Double.valueOf(obj.getString("winningamount")));
                    quizeListData.setId(Integer.valueOf(obj.getString("id")));
                    //quizeListDataArrayList.add(quizeListData);
                }
               // adapter = new CurrentExamQuizeAdapter(getActivity(), quizeFilterArrayList,0);
                //rv_currentQuiz.setAdapter(adapter);
                //adapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
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

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void showAlertDialogwithAction(String title, String message, final String action) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeMainActivity.this);
        // builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(action);
                startActivityForResult(intent,150);
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

    private void currentQuizeApi() {

        showProgressDialog("fetch Quiz...");
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();

        listKey.add("UserId");

        listValue.add(prefManager.getUserName());


        ApiCallingMethods.requestForPost(listKey, listValue, Config.CurrentQuiz, this, this, "CurentQuiz");

    }
    private void doLogOut() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeMainActivity.this);

        builder.setTitle("Exit?");
        builder.setMessage("Do you want to log out?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(HomeMainActivity.this);
                SharedPreferences.Editor e = prefs.edit();
                e.clear();
                e.commit();
                SharedPreferences settings = getSharedPreferences("MyQuiz", MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(HomeMainActivity.this, UserLoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        //Set positive button background
        //pbutton.setBackgroundColor(Color.YELLOW);
        //Set positive button text color
        pbutton.setTextColor(Color.MAGENTA);

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        //Set positive button background
        //pbutton.setBackgroundColor(Color.YELLOW);
        //Set positive button text color
        nbutton.setTextColor(Color.MAGENTA);


    }
}
