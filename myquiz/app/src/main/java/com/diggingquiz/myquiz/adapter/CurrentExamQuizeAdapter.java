package com.diggingquiz.myquiz.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.beans.QuizeListData;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.diggingquiz.myquiz.activities.QuizeQuestinAnswer;
import com.diggingquiz.myquiz.activities.UserLoginActivity;
import com.diggingquiz.myquiz.beans.QuizeListData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Tanuj yadav on 30/11/2017.
 */

public class CurrentExamQuizeAdapter extends RecyclerView.Adapter<CurrentExamQuizeAdapter.ViewHolder>  {

    private Context context;
    private String resultHandler = "";
    private List<String> listKey, listValue;
    private Dialog dialog;
    public int requestid = 0;
    private String layoutname;
    private ProgressDialog progressDialog;
    private PrefManager prefManager;
    android.support.v4.app.Fragment fragment = null;
    String backStackName = null;
    Bundle args = new Bundle();
    private Integer position = 0;
    ArrayList<Date> dateArraylist;

    ArrayList<QuizeListData> quizeListDataArrayList;
    int status;
    public CurrentExamQuizeAdapter(Context context,ArrayList<QuizeListData> satSunArrayList,int status) {
        this.context = context;

        this.quizeListDataArrayList=satSunArrayList;
        this.status=status;

    }


    @Override
    public CurrentExamQuizeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem_currentquiz, parent, false);

        CurrentExamQuizeAdapter.ViewHolder viewHolder = new CurrentExamQuizeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CurrentExamQuizeAdapter.ViewHolder holder, final int position) {

        try {
            //Log.e("Likecount",homeMissionLists.get(position).getTotalLikeCounts());
            holder.tv_quiz.setText(quizeListDataArrayList.get(position).getQuizeName());
            //if(walletHistoryData.get(position).getWalletWithdrawalAMount().equalsIgnoreCase("0")){
            //    holder.textviewAmount.setText("+Rs "+3300);
            //}else{
                holder.textviewAmount.setText("\u20B9 "+ UserLoginActivity.userProfileData.getWinningamount());
            //}

            holder.textviewDate.setText("Date - "+quizeListDataArrayList.get(position).getDate());
            if(status==0){// 0 for current 1 for up comming 3 complete
                holder.tv_exam.setVisibility(View.VISIBLE);
                holder.tv_resultheader.setText("Prize Money");
                holder.linerWatch.setVisibility(View.GONE);
                holder.liner_amount.setVisibility(View.VISIBLE);
                holder.tv_statuscomplete.setVisibility(View.GONE);
                holder.liner_trophy.setVisibility(View.GONE);
            }else if(status==1) {
                holder.tv_exam.setVisibility(View.GONE);
                holder.tv_resultheader.setText("Prize Money");
                holder.linerWatch.setVisibility(View.VISIBLE);
                holder.liner_amount.setVisibility(View.VISIBLE);
                holder.tv_statuscomplete.setVisibility(View.GONE);
                holder.liner_trophy.setVisibility(View.GONE);
            }else if(status==2){
                holder.tv_exam.setVisibility(View.GONE);
                holder.linerWatch.setVisibility(View.GONE);
               holder.liner_amount.setVisibility(View.GONE);
               holder.tv_statuscomplete.setVisibility(View.VISIBLE);
               holder.liner_trophy.setVisibility(View.VISIBLE);
               holder.tv_statuscomplete.setTextColor(context.getResources().getColor(R.color.lightegreen));
                Drawable img = context.getResources().getDrawable( R.drawable.trophy );
                //holder.tv_statuscomplete.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                if(quizeListDataArrayList.get(position).getIsPass()==1){
                    holder.tv_statuscomplete.setText("You won "+"\u20B9 "+ UserLoginActivity.userProfileData.getWinningamount());
                    holder.tv_statuscomplete.setTextColor(context.getResources().getColor(R.color.lightegreen));
                }else {
                    holder.tv_statuscomplete.setText("You Lost this Quiz.");
                    holder.tv_statuscomplete.setTextColor(context.getResources().getColor(R.color.redcolor));
                }

            }
            holder.tv_Quizetime.setText("Time - 6 pm to 11:59pm");
            //prefManager = new PrefManager(context);
            holder.tv_exam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(checkTime()){
                        Intent intent = new Intent(context, QuizeQuestinAnswer.class);

                        intent.putExtra("quizeId",String.valueOf(quizeListDataArrayList.get(position).getId()));
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        notifyDataSetChanged();

                    }else{
                        Toast.makeText(context,"Quize Start on 6:00 pm to 11:59pm",Toast.LENGTH_LONG).show();
                    }

                    //context.startActivity(new Intent(context, QuizeQuestinAnswer.class));
                }
            });

            if (holder.timer != null) {
                holder.timer.cancel();
            }

            long timer = quizeListDataArrayList.get(position).getExpireTime();
            Date today = new Date();
            final long currentTime = today.getTime();
            long expiryTime = timer - currentTime;

          holder.timer=  new CountDownTimer(expiryTime, 500) {
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;
                    String time = days+" "+"days" +" :" +hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
                    holder.tv_timer.setText(time);
                    holder.tv_exam.setText(time);
                }

                public void onFinish() {

                    holder.tv_timer.setText("");
                    holder.tv_exam.setText("Start Quiz");
                }
            }.start();


        } catch (Exception e) {
            Log.d("Something wrong", e.getMessage());

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewDate,textviewAmount,tv_quiz,tv_Quizetime,tv_timer,tv_resultheader;
        public ImageView img_troffy;
        CountDownTimer timer;
        public TextView tv_exam, btncencel,tv_statuscomplete;
        LinearLayout liner_amount,linerWatch,liner_trophy;

        int position = getAdapterPosition();

        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            tv_quiz =itemView.findViewById(R.id.tv_quiz);
            textviewAmount =  itemView.findViewById(R.id.textviewAmount);
            textviewDate =  itemView.findViewById(R.id.textviewDate);
            tv_exam =  itemView.findViewById(R.id.tv_exam);
            tv_Quizetime=  itemView.findViewById(R.id.tv_Quizetime);
            tv_timer=  itemView.findViewById(R.id.tv_timer);
            tv_resultheader=itemView.findViewById(R.id.tv_resultheader);
            liner_amount=itemView.findViewById(R.id.liner_amount);
            linerWatch=itemView.findViewById(R.id.linerWatch);
            tv_statuscomplete=itemView.findViewById(R.id.tv_statuscomplete);
            liner_trophy=itemView.findViewById(R.id.liner_trophy);
            img_troffy=itemView.findViewById(R.id.img_troffy);
            //   totalTime=(TextView)itemView.findViewById(R.id.mission_time);
        }
    }




    @Override
    public int getItemCount() {
        return quizeListDataArrayList.size();
    }

    public void setSendRequestDataToServer(Integer requestuserid, Integer curentuserid, Integer request_id, String Status, int postion) {
        position = postion;
        progressDialog = new ProgressDialog(context);
        listValue = new ArrayList<>();
        listKey = new ArrayList<>();
        progressDialog.show();
        prefManager = new PrefManager(context);
        if (Status.equalsIgnoreCase("accept")) {
         /*   listKey.add("requested_user_id");
            listKey.add("current_user_id");*/
            listKey.add("request_id");
            listKey.add("status");

        /*    listValue.add(String.valueOf( requestuserid));
            listValue.add(prefManager.getUserId());*/
            listValue.add(String.valueOf(request_id));
            listValue.add(Status);
        } else {

            listKey.add("request_id");
            listKey.add("status");

            listValue.add(String.valueOf(request_id));
            listValue.add(Status);
        }
        resultHandler = "SendFrendRequest";
        // ApiCallingMethods.requestForPost(listKey, listValue, Utils.setAcceptfriendrequest, context, this, progressDialog);

    }

public Boolean checkTime(){
        boolean checkstatus=false;
    try {
        String string1 = "18:00:00";
        Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        String string2 = "23:59:00";
        Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);


        String someRandomTime = "01:00:00";
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        Date d = new SimpleDateFormat("HH:mm:ss").parse(formattedDate);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(d);


        Date x = calendar3.getTime();
        if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
            //checkes whether the current time is between 14:49:00 and 20:11:13.
         //   System.out.println(true);
            checkstatus=true;
        }

    } catch (ParseException e) {
        e.printStackTrace();
    }
    return checkstatus;
}

}
