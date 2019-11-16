package com.diggingquiz.myquiz.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.android.volley.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.Interface.ApiCallbackListener;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.quiz.diggingquiz.myquiz.Utils.server.ApiCallingMethods;
import com.quiz.diggingquiz.myquiz.Utils.server.Config;
import com.diggingquiz.myquiz.adapter.StatementAdapter;
import com.diggingquiz.myquiz.adapter.WithdrawListAdapter;
import com.diggingquiz.myquiz.base.BaseActivity;
import com.diggingquiz.myquiz.beans.StatementListData;
import com.diggingquiz.myquiz.beans.WithdrawlistData;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class UserAccount extends BaseActivity implements ApiCallbackListener {

    Context context;
    TextView tv_Usermobile, tv_Username;
    Button buttonLogin;
    EditText edit_username, editEmail, edit_mobile;
    TextInputLayout textInput_mobile, textInput_email, textInput_name;
    LinearLayout liner_myWallet;
    CircleImageView img_profile_edit;
    public PrefManager prefManager;
    TextView tv_myWallet, tv_planName, tv_noofQuize;
    RecyclerView recylerViewStatement, recylerViewwithdrawlist;
    RecyclerView.Adapter adapter, adapterWithdraw;
    Dialog dialogWithdrawal, dialogBank, dialogConfirm;
    LinearLayout liner_withdraw;
    TextView tv_submit, tvheader,tv_quizeCompletevalue,tv_quizeWonvalue,tv_quizelostvalue,tv_quizeremaiinvalue,tv_lapsequiz;
    EditText edit_amount;
    TextView tv_accno, tv_ifsc, tv_accountname, tv_bankname, tv_branchName, tv_bankOk;
    ArrayList<WithdrawlistData> withdrawlistDataArrayList;
    ArrayList<StatementListData> statementArrayListData;
    TextView tv_showstatementlist,tv_show_withdrawlist;

    LinearLayout ln_withdrawheader,ln_statement_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = UserAccount.this;


        setContentView(R.layout.activity_useraccount);

        init();

        SetData();
        withdrawlistDataArrayList = new ArrayList<>();
        statementArrayListData =new ArrayList<>();
        //getWithdrawList();
        getAccountDetails();
        //int color = Color.parseColor(Color.parseColor("#AA3456"));

    }

    public void SetData() {
        Picasso.with(context).load(prefManager.getImageUrl()).placeholder(R.drawable.girl).into(img_profile_edit);
        tv_Username.setText("Name -" + prefManager.getUserName());
        /*        tv_Usermobile.setText(prefManager.getUserMobile());*/
        tv_planName.setText(prefManager.getPlanName());
        tv_noofQuize.setText("Total quize - " + prefManager.getNoOfQuiz());
        tv_myWallet.setText("\u20B9 " + prefManager.getWalletAmount());



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.liner_withdraw:
                String day= giveDate();
                if(day.equalsIgnoreCase("01")){
                    String wallet=prefManager.getWalletAmount();
                    if(wallet!=null && Double.valueOf(wallet)>0) {
                        withdrawalDialog();
                    }else{
                        showToast("Not enough balance yet.");
                    }
                }else{
                    showToast("Withdraw request submit on first date of the month please submit next first day of next month");
                }



                break;
            case R.id.tv_showstatementlist:
                if (ln_statement_header.getVisibility() == View.VISIBLE) {
                    ln_statement_header.setVisibility(View.GONE);
                    recylerViewStatement.setVisibility(View.GONE);
                    tv_showstatementlist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_downward_black_24dp, 0);
                    // Its visible
                } else {
                    ln_statement_header.setVisibility(View.VISIBLE);
                    recylerViewStatement.setVisibility(View.VISIBLE);
                    tv_showstatementlist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_forward_white_24dp, 0);
                    // Either gone or invisible
                }

                break;
            case R.id.tv_show_withdrawlist:
                if (ln_withdrawheader.getVisibility() == View.VISIBLE) {
                    ln_withdrawheader.setVisibility(View.GONE);
                    recylerViewwithdrawlist.setVisibility(View.GONE);

                    tv_show_withdrawlist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_downward_black_24dp, 0);
                    // Its visible
                } else {
                    ln_withdrawheader.setVisibility(View.VISIBLE);
                    recylerViewwithdrawlist.setVisibility(View.VISIBLE);
                    tv_show_withdrawlist.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_forward_white_24dp, 0);
                    // Either gone or invisible
                }

                break;
        }
    }

    private void init() {

        img_profile_edit = findViewById(R.id.img_profile_edit);


        tv_Username = findViewById(R.id.tv_Username);

        tv_Usermobile = findViewById(R.id.tv_Usermobile);


        tv_myWallet = findViewById(R.id.tv_myWallet);
        tv_planName = findViewById(R.id.tv_planName);
        tv_noofQuize = findViewById(R.id.tv_noofQuize);

        prefManager = new PrefManager(UserAccount.this);
        liner_withdraw = findViewById(R.id.liner_withdraw);
        liner_withdraw.setOnClickListener(this);

        tv_quizeCompletevalue= findViewById(R.id.tv_quizeCompletevalue);
        tv_quizeWonvalue= findViewById(R.id.tv_quizeWonvalue);
        tv_quizelostvalue= findViewById(R.id.tv_quizelostvalue);
        tv_quizeremaiinvalue= findViewById(R.id.tv_quizeremaiinvalue);
        tv_lapsequiz= findViewById(R.id.tv_quizlefte);

        ln_statement_header= findViewById(R.id.ln_statement_header);
        tv_showstatementlist= findViewById(R.id.tv_showstatementlist);

        tv_show_withdrawlist= findViewById(R.id.tv_show_withdrawlist);
        ln_withdrawheader= findViewById(R.id.ln_withdrawheader);
        tv_show_withdrawlist.setOnClickListener(this);
        tv_showstatementlist.setOnClickListener(this);

        recylerViewStatement = findViewById(R.id.recylerViewStatement);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(context);
        layoutManager5.setOrientation(LinearLayoutManager.VERTICAL);
        recylerViewStatement.setLayoutManager(layoutManager5);




        recylerViewwithdrawlist = findViewById(R.id.recylerViewwithdrawlist);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(context);
        layoutManager6.setOrientation(LinearLayoutManager.VERTICAL);
        recylerViewwithdrawlist.setLayoutManager(layoutManager6);


        ln_statement_header.setVisibility(View.GONE);
        recylerViewStatement.setVisibility(View.GONE);


        ln_withdrawheader.setVisibility(View.GONE);
        recylerViewwithdrawlist.setVisibility(View.GONE);

        ImageView img_back=findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



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

    public void withdrawalDialog() {
        dialogWithdrawal = new Dialog(context);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWithdrawal.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        /*  dialog.setContentView(R.layout.dialog_preferlanguage_layout);*/
        Rect displayRectangle = new Rect();
        Window window = dialogWithdrawal.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_amount_layout, null);
        //layout.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
        /* layout.setMinimumHeight((int)(displayRectangle.height() * 0.9f));*/
        dialogWithdrawal.setContentView(layout);
        tv_submit = dialogWithdrawal.findViewById(R.id.textviewdialogsubmit);
        edit_amount = dialogWithdrawal.findViewById(R.id.edit_amount);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit_amount.getText().toString())) {
                    showToast("Please enter amount. ");
                } else {
                    withdrawBankDialog(edit_amount.getText().toString());
                }
            }
        });


        dialogWithdrawal.show();
    }

    public void withdrawBankDialog(final String amount) {
        dialogBank = new Dialog(context);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogBank.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        /*  dialog.setContentView(R.layout.dialog_preferlanguage_layout);*/
        Rect displayRectangle = new Rect();
        Window window = dialogBank.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_bank_layout, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
        /* layout.setMinimumHeight((int)(displayRectangle.height() * 0.9f));*/
        dialogBank.setContentView(layout);
        tvheader = dialogBank.findViewById(R.id.tvheader);
        Spannable word = new SpannableString("Are you sure you want to withdraw");

        word.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvheader.setText(word);
        Spannable wordTwo = new SpannableString(" " + "\u20B9 " + amount);

        wordTwo.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.goldentextcolor)), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvheader.append(wordTwo);
        Spannable wordThree = new SpannableString(" in your Bank Account");

        wordThree.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, wordThree.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvheader.append(wordThree);

        tv_accno = dialogBank.findViewById(R.id.tv_accno);
        tv_ifsc = dialogBank.findViewById(R.id.tv_ifsc);
        tv_accountname = dialogBank.findViewById(R.id.tv_accountname);
        tv_bankname = dialogBank.findViewById(R.id.tv_bankname);

        tv_branchName = dialogBank.findViewById(R.id.tv_branchName);

        tv_bankOk = dialogBank.findViewById(R.id.tv_bankOk);

        tv_accno.setText("Account No.-" + UserLoginActivity.userProfileData.getAccountNo());
        tv_ifsc.setText("IFSC Code.-" + UserLoginActivity.userProfileData.getIfscCode());
        tv_accountname.setText("Account Name.-" + UserLoginActivity.userProfileData.getAcc_Name());
        tv_bankname.setText("Bank Name.-" + UserLoginActivity.userProfileData.getBank_Name());
        tv_branchName.setText("Branch Name.-" + UserLoginActivity.userProfileData.getBrach_Name());


        tv_bankOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wallet=prefManager.getWalletAmount();

             if(Double.valueOf(amount)>Double.valueOf(wallet)){
                 showToast("Amount should less then wallet amount.");
                 return;
             }
                    withdrawRequest(amount);
                    dialogBank.dismiss();
                    dialogWithdrawal.dismiss();
                String day=giveDate();


            }
        });


        dialogBank.show();
    }

    public void dialogConfirm() {
        dialogConfirm = new Dialog(context);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        /*  dialog.setContentView(R.layout.dialog_preferlanguage_layout);*/
        Rect displayRectangle = new Rect();
        Window window = dialogConfirm.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_confirm_layout, null);
        //layout.setMinimumWidth((int) (displayRectangle.width() * 0.9f));
        /* layout.setMinimumHeight((int)(displayRectangle.height() * 0.9f));*/
        dialogConfirm.setContentView(layout);
        ImageView img_close = dialogConfirm.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm.dismiss();
            }
        });


        dialogConfirm.show();
    }

    private void withdrawRequest(String amount) {

        showProgressDialog("Saving request....");
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();


        listKey.add("UserId");
        listKey.add("Acc_Holder_Name");
        listKey.add("Acc_No");
        listKey.add("IFSC_Code");
        listKey.add("Branch_Name");
        listKey.add("Bank_Name");
        listKey.add("Status");
        listKey.add("amount");
        listKey.add("WithdrawDate");

        Date c = Calendar.getInstance().getTime();
       /* System.out.println("Current time => " + c);*/

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        listValue.add(prefManager.getUserName());
        listValue.add(UserLoginActivity.userProfileData.getAcc_Name());
        listValue.add(UserLoginActivity.userProfileData.getAccountNo());
        listValue.add(UserLoginActivity.userProfileData.getIfscCode());
        listValue.add(UserLoginActivity.userProfileData.getBrach_Name());
        listValue.add(UserLoginActivity.userProfileData.getBank_Name());
        listValue.add("PENDING");
        listValue.add(amount);
        listValue.add(formattedDate);



        ApiCallingMethods.requestForPost(listKey, listValue, Config.withdrawrequest, UserAccount.this, this, "withdrarrequest");

    }

    private void getWithdrawList() {
        ProgressDialog progressDialog= new ProgressDialog(UserAccount.this);
        //showProgressDialog("Fetchin withdrawlist");
        String strurl = Config.getWithdrawlist +""+ prefManager.getUserName();
        ApiCallingMethods.requestForGet(strurl, UserAccount.this, this, progressDialog, "getwithdrawlist");

    }

    private void userAccountDetails() {
        ProgressDialog progressDialog= new ProgressDialog(UserAccount.this);
        //showProgressDialog("Fetchin withdrawlist");
        String strurl = Config.getUserAcccountDetails +""+ prefManager.getUserName();
        ApiCallingMethods.requestForGet(strurl, UserAccount.this, this, progressDialog, "getuserDetail");

    }


    private void getAccountDetails() {
        ProgressDialog progressDialog= new ProgressDialog(UserAccount.this);
        //showProgressDialog("Fetchin AccountDetails");
        String strurl = Config.getUserAcccountDetails +""+ prefManager.getUserName();
        ApiCallingMethods.requestForGet(strurl, UserAccount.this, this, progressDialog, "getQuizDetails");

    }

    @Override
    public void onResultCallback(String response, String flag) {
        if (!UserAccount.this.isFinishing() ) {
            dismissDialog();
        }
        dismissDialog();

        Log.e("Result", " " + response);
        try {

            if (flag != null && flag.equals("withdrarrequest")) {
                JSONObject objMain = new JSONObject(response);
                String merssge = objMain.getString("message");
               // showToast(merssge);
                dialogConfirm();


            }
            else if (flag != null && flag.equals("getQuizDetails")) {
                JSONObject objMain = new JSONObject(response);
                String merssge = objMain.getString("message");
                JSONArray jsonQuizDetailArray = new JSONArray(objMain.getString("QuizDetail"));
                JSONArray jsonWithdrawListArray = new JSONArray(objMain.getString("WithdrawList"));
                JSONArray jsonStatamentListArray = new JSONArray(objMain.getString("StatamentList"));

                System.out.println(jsonQuizDetailArray);


                for (int i = 0; i < jsonWithdrawListArray.length(); i++) {
                    JSONObject obj = jsonWithdrawListArray.getJSONObject(i);
                    WithdrawlistData withdrawlistData = new WithdrawlistData();
                    withdrawlistData.setRemark(obj.getString("Status"));
                    withdrawlistData.setTranjectionDate(obj.getString("WithdrawDate"));
                    if (obj.getString("amount") != null) {
                        withdrawlistData.setWalletAddAMount(obj.getString("amount"));
                    } else {
                        withdrawlistData.setWalletAddAMount("0");
                    }
                    withdrawlistDataArrayList.add(withdrawlistData);
                }
                adapterWithdraw = new WithdrawListAdapter(context,withdrawlistDataArrayList);
                recylerViewwithdrawlist.setAdapter(adapterWithdraw);
                for (int i = 0; i < jsonQuizDetailArray.length(); i++) {
                    JSONObject obj = jsonQuizDetailArray.getJSONObject(i);
                    tv_quizeCompletevalue.setText(obj.getString("AttemptedQuiz"));
                     tv_quizeWonvalue.setText(obj.getString("WinningQuiz"));
                    tv_quizelostvalue.setText(obj.getString("LoseQuiz"));
                    tv_lapsequiz.setText(obj.getString("LapsedQuiz"));

                    tv_quizeremaiinvalue.setText(obj.getString("RemainingQuiz"));
                    prefManager.setWalletAmount(obj.getString("TotalBalanceAmount"));
                    tv_myWallet.setText("\u20B9 " + prefManager.getWalletAmount());
                }
                for (int i = 0; i <jsonStatamentListArray.length(); i++) {
                    JSONObject obj = jsonStatamentListArray.getJSONObject(i);
                    StatementListData statementListData=new StatementListData();
                    statementListData.setTranjectionDate(obj.getString("quizdate"));
                    statementListData.setQuizeName("Quize"+i);
                    statementListData.setBalance(obj.getString("BalanceAmount"));
                    statementListData.setWalletWithdrawalAMount(obj.getString("Withdrawamount"));
                    int status=-1;
                    if(Boolean.valueOf(obj.getString("IsPass"))){
                        status=1;
                    }else {
                        status=0;
                    }
                    statementListData.setQuizStatus(status);

                    statementArrayListData.add(statementListData);

                }
                adapter = new StatementAdapter(context, statementArrayListData);
                recylerViewStatement.setAdapter(adapter);
            }
            else{

            }





        } catch (Exception d) {
            d.printStackTrace();
        }
    }

    @Override
    public void onErrorCallback(VolleyError error) {
        if (!UserAccount.this.isFinishing() ) {
            dismissDialog();
        }
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
    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(cal.getTime());
    }
}
