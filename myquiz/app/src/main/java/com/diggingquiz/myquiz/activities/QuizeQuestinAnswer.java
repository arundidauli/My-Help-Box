package com.diggingquiz.myquiz.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.diggingquiz.myquiz.base.BaseActivity;
import com.diggingquiz.myquiz.beans.QuestionAnswerData;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class QuizeQuestinAnswer extends BaseActivity implements ApiCallbackListener {

    Context context;

    EditText edit_username, editEmail, edit_mobile;

    TextView tv_time, tv_question, tv_op1, tv_op2, tv_op3, tv_next;
    CountDownTimer yourCountDownTimer;
    ArrayList<QuestionAnswerData> questionAnswerData;
    int count = 0;
    ArrayList<Date> satArraylist;
    ArrayList<Date> sunArraylist;
    RelativeLayout relative_question;
    int currectQuestioncount = 0;
    PrefManager prefManager;
    String quizeId = "";
    private ProgressDialog progressDialog;

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = QuizeQuestinAnswer.this;

        questionAnswerData = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("quizeId")) {
            quizeId = intent.getExtras().getString("quizeId");


        }
        prefManager = new PrefManager(QuizeQuestinAnswer.this);
        satArraylist = new ArrayList<>();
        sunArraylist = new ArrayList<>();

        setContentView(R.layout.activity_questionans);
        init();
        setData();


        getDateRange();


        //int color = Color.parseColor(Color.parseColor("#AA3456"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_op1:
                tv_op1.setBackground(getResources().getDrawable(
                        R.drawable.lighte_green_bacground));
                tv_op1.setTextColor(getResources().getColor(R.color.black));

                tv_op2.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op2.setTextColor(getResources().getColor(R.color.black));
                tv_op3.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op3.setTextColor(getResources().getColor(R.color.black));

                questionAnswerData.get(count).setUserSelectAns("1");
                if (questionAnswerData.get(count).getCurrectAns().equalsIgnoreCase("1")) {
                    currectQuestioncount++;
                }

                break;
            case R.id.tv_op2:
                tv_op2.setBackground(getResources().getDrawable(
                        R.drawable.lighte_green_bacground));
                tv_op2.setTextColor(getResources().getColor(R.color.black));

                tv_op1.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op1.setTextColor(getResources().getColor(R.color.black));
                tv_op3.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op3.setTextColor(getResources().getColor(R.color.black));
                questionAnswerData.get(count).setUserSelectAns("2");
                if (questionAnswerData.get(count).getCurrectAns().equalsIgnoreCase("2")) {
                    currectQuestioncount++;
                }
                break;
            case R.id.tv_op3:
                tv_op3.setBackground(getResources().getDrawable(
                        R.drawable.lighte_green_bacground));
                tv_op3.setTextColor(getResources().getColor(R.color.black));

                tv_op2.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op2.setTextColor(getResources().getColor(R.color.black));
                tv_op1.setBackground(getResources().getDrawable(
                        R.drawable.whitebackground));
                tv_op1.setTextColor(getResources().getColor(R.color.black));
                questionAnswerData.get(count).setUserSelectAns("3");
                if (questionAnswerData.get(count).getCurrectAns().equalsIgnoreCase("3")) {
                    currectQuestioncount++;
                }
                break;
            case R.id.tv_next:


                //yourCountDownTimer.cancel();
                yourCountDownTimer.onFinish();
                //yourCountDownTimer.start();
                break;

        }
    }

    private void init() {
        tv_time = findViewById(R.id.tv_time);
        tv_question = findViewById(R.id.tv_question);
        tv_op1 = findViewById(R.id.tv_op1);
        tv_op1.setOnClickListener(this);
        tv_op2 = findViewById(R.id.tv_op2);
        tv_op2.setOnClickListener(this);
        tv_op3 = findViewById(R.id.tv_op3);
        tv_op3.setOnClickListener(this);
        relative_question = findViewById(R.id.relative_question);
        tv_next = findViewById(R.id.tv_next);
        tv_next.setOnClickListener(this);

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

    private void setData() {
        progressDialog = new ProgressDialog(QuizeQuestinAnswer.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Searching...");

        progressDialog.show();
        String strurl = Config.topfiveQuestion;
        ApiCallingMethods.requestForGet(strurl, QuizeQuestinAnswer.this, this, progressDialog, "getQuestion");

        /*questionAnswerData=new ArrayList<>();
        QuestionAnswerData questinAnswer=new QuestionAnswerData();
        questinAnswer.setQuestion("who is prime minister of India? ");
        questinAnswer.setId(0);
        questinAnswer.setOp1("Narender Modi");
        questinAnswer.setOp2("Manmohan Singh");
        questinAnswer.setOp3("Rahul Gandhi");
        questinAnswer.setCurrectAns("1");
        questionAnswerData.add(questinAnswer);
        QuestionAnswerData questinAnswer2=new QuestionAnswerData();
        questinAnswer2.setQuestion("Deepika is from which city ?");
        questinAnswer2.setId(0);
        questinAnswer2.setOp1("Delhi");
        questinAnswer2.setOp2("Mumbai");
        questinAnswer2.setOp3("Chenai");
        questinAnswer2.setCurrectAns("3");
        questionAnswerData.add(questinAnswer2);
        QuestionAnswerData  questinAnswer3=new QuestionAnswerData();
        questinAnswer3.setQuestion("Rahul Gandhi belongs to which party ?");
        questinAnswer3.setId(0);
        questinAnswer3.setOp1("NDA");
        questinAnswer3.setOp2("UPA");
        questinAnswer3.setOp3("Both");
        questinAnswer3.setCurrectAns("2");
        questionAnswerData.add(questinAnswer3);
        QuestionAnswerData  questinAnswer4=new QuestionAnswerData();
        questinAnswer4.setQuestion("Dargleening are  in which state?");
        questinAnswer4.setId(0);
        questinAnswer4.setOp1("Bangal");
        questinAnswer4.setOp2("Sikkim");
        questinAnswer4.setOp3("Bihar");
        questinAnswer4.setCurrectAns("2");
        questionAnswerData.add(questinAnswer4);
        QuestionAnswerData  questinAnswer5=new QuestionAnswerData();
        questinAnswer5.setQuestion("asdfasdfasdf are  in which state?");
        questinAnswer5.setId(0);
        questinAnswer5.setOp1("Bangal");
        questinAnswer5.setOp2("Sikkim");
        questinAnswer5.setOp3("Bihar");
        questinAnswer5.setCurrectAns("2");
        questionAnswerData.add(questinAnswer5);
        showExam();*/
    }

    public void showExam() {
        tv_op2.setBackground(getResources().getDrawable(
                R.drawable.whitebackground));
        tv_op2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        tv_op1.setBackground(getResources().getDrawable(
                R.drawable.whitebackground));
        tv_op1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tv_op3.setBackground(getResources().getDrawable(
                R.drawable.whitebackground));
        tv_op3.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        relative_question.setVisibility(View.VISIBLE);
        int qusesnumber = count + 1;
        tv_question.setText("Q" + qusesnumber + " " + questionAnswerData.get(qusesnumber - 1).getQuestion());
        tv_op1.setText(" " + questionAnswerData.get(qusesnumber - 1).getOp1());
        tv_op2.setText(" " + questionAnswerData.get(qusesnumber - 1).getOp2());
        tv_op3.setText(" " + questionAnswerData.get(qusesnumber - 1).getOp3());

        yourCountDownTimer = new CountDownTimer(25000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_time.setVisibility(View.VISIBLE);
                tv_time.setText("00:00:" + millisUntilFinished / 1000);
                Drawable img = context.getResources().getDrawable(R.drawable.watch);
                int pixelDrawableSize = (int) Math.round(tv_time.getLineHeight() * 0.9); // Or the percentage you like (0.8, 0.9, etc.)
                img.setBounds(0, 0, pixelDrawableSize, pixelDrawableSize); // setBounds(int left, int top, int right, int bottom), in this case, drawable is a square image
                tv_time.setCompoundDrawables(
                        img, //left
                        null, //top
                        null, //right
                        null //bottom
                );
                //tv_time.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                //here you can have your logic to set text to edittext

            }

            public void onFinish() {
                count++;
                yourCountDownTimer.cancel();
                relative_question.setVisibility(View.GONE);
                //tv_question.setText("Q"+count);
                if (count == 5) {
                    saveApihit();

                } else {

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            showExam();
                        }
                    }, 1000);
                }
                tv_time.setVisibility(View.GONE);

            }

        }.start();
    }

    public void getDateRange() {
        Date begining, end;

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        //weekEndCount(begining,end);
        weekEndCount(begining, end);
    }

    //this Method For count Saturday Sunday Count Beetween two date

    public int weekEndCount(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int sundays = 0;
        int saturday = 0;

        while (!c1.after(c2)) {
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                saturday++;
                satArraylist.add(c1.getTime());
            }
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays++;
                satArraylist.add(c1.getTime());
            }

            c1.add(Calendar.DATE, 1);
        }


        return saturday + sundays;
    }


    @Override
    public void onResultCallback(String response, String flag) {
        dismissDialog();
        try {
            JSONObject objMain = new JSONObject(response);
            String merssge = objMain.getString("message");
            // showToast(merssge);
            if (flag.equalsIgnoreCase("getQuestion")) {

                JSONArray jsonArray = new JSONArray(objMain.getString("questionlist"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    QuestionAnswerData questionAnswerDataArrayList = new QuestionAnswerData();
                    JSONObject obj = jsonArray.getJSONObject(i);

                    questionAnswerDataArrayList.setId(Integer.valueOf(obj.getString("Questionid")));
                    questionAnswerDataArrayList.setQuestion(obj.getString("Question"));
                    questionAnswerDataArrayList.setCurrectAns(obj.getString("CorrectAnswer"));
                    questionAnswerDataArrayList.setOp1(obj.getString("Option1"));
                    questionAnswerDataArrayList.setOp2(obj.getString("Option2"));
                    questionAnswerDataArrayList.setOp3(obj.getString("Option3"));


                    questionAnswerData.add(questionAnswerDataArrayList);
                }

                showExam();
            } else if (flag.equalsIgnoreCase("Submit")) {
                Intent intent = new Intent(context, HomeMainActivity.class);


                startActivity(intent);
                finish();
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

    private void saveApihit() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c);
        //showToast(formattedDate);

        showProgressDialog("Submit...");
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();
        listKey.add("id");
        listKey.add("userid");
        listKey.add("amount");
        listKey.add("noofquestion");
        listKey.add("noofcorrectanswer");
        listKey.add("quizdate");
        listKey.add("IsPass");
        listKey.add("QuizId");
        listValue.add("");
        listValue.add(UserLoginActivity.userProfileData.getUserName());
        if (currectQuestioncount >= UserLoginActivity.userProfileData.getNoofcorrrectquestionforpass()) {
            listValue.add(String.valueOf(UserLoginActivity.userProfileData.getWinningamount()));
        } else {
            listValue.add(String.valueOf(0));
        }

        listValue.add("" + questionAnswerData.size());
        listValue.add("" + currectQuestioncount);
        listValue.add(formattedDate);
        listValue.add(currectQuestioncount >= UserLoginActivity.userProfileData.getNoofcorrrectquestionforpass() ? "true" : "false");
        listValue.add(quizeId);

        System.out.println(listValue);


        ApiCallingMethods.requestForPost(listKey, listValue, Config.SubmitQuiZE, QuizeQuestinAnswer.this, this, "Submit");

    }
}
