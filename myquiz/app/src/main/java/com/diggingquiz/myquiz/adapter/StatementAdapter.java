package com.diggingquiz.myquiz.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.diggingquiz.myquiz.R;
import com.diggingquiz.myquiz.Utils.preferences.PrefManager;
import com.diggingquiz.myquiz.beans.StatementListData;
import com.diggingquiz.myquiz.beans.WalletHistoryData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanuj yadav on 30/11/2017.
 */

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder>  {
    private List<WalletHistoryData> walletHistoryData;
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
    ArrayList<StatementListData>statementListData;
    public StatementAdapter(Context context,ArrayList<StatementListData>statementListDataArrayList) {
        this.context = context;
        this.statementListData=statementListDataArrayList;



    }

    @Override
    public StatementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_satement, parent, false);

        StatementAdapter.ViewHolder viewHolder = new StatementAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StatementAdapter.ViewHolder holder, final int position) {

        try {
            //Log.e("Likecount",homeMissionLists.get(position).getTotalLikeCounts());
            holder.tv_date.setText(statementListData.get(position).getTranjectionDate());
            holder.tv_withdraw.setText("\u20B9 "+statementListData.get(position).getWalletWithdrawalAMount());
            holder.tv_withdrawbalance.setText("\u20B9 "+statementListData.get(position).getBalance());
            if(statementListData.get(position).getQuizStatus()==1){
                holder.tv_status.setBackground(context.getResources().getDrawable(R.drawable.greenselector_withoutradious));
                holder.tv_status.setText("WON");
            }else{
                holder.tv_status.setBackground(context.getResources().getDrawable(R.drawable.redselectorwithoutradious));
                holder.tv_status.setText("LOST");
            }
            holder.tv_Name.setText(statementListData.get(position).getQuizeName());


            prefManager = new PrefManager(context);




        } catch (Exception e) {
            Log.d("Something wrong", e.getMessage());

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date,tv_Name,tv_status,tv_withdraw,tv_withdrawbalance;
        public ImageView IvReqFriend;

        public Button btnBook, btncencel;

        int position = getAdapterPosition();

        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            tv_date =itemView.findViewById(R.id.tv_date);

            tv_Name=itemView.findViewById(R.id.tv_Name);
            tv_status=itemView.findViewById(R.id.tv_status);
            tv_withdraw =itemView.findViewById(R.id.tv_withdraw);
            tv_withdrawbalance =itemView.findViewById(R.id.tv_withdrawbalance);




            //   totalTime=(TextView)itemView.findViewById(R.id.mission_time);
        }
    }




    @Override
    public int getItemCount() {
        return statementListData.size();
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



}
