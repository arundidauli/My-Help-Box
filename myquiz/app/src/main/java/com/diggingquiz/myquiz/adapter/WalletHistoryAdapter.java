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
import com.diggingquiz.myquiz.beans.WalletHistoryData;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanuj yadav on 30/11/2017.
 */

public class WalletHistoryAdapter extends RecyclerView.Adapter<WalletHistoryAdapter.ViewHolder>  {
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
    public WalletHistoryAdapter(Context context) {
        this.context = context;



    }

    @Override
    public WalletHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_history, parent, false);

        WalletHistoryAdapter.ViewHolder viewHolder = new WalletHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WalletHistoryAdapter.ViewHolder holder, final int position) {

        try {
            //Log.e("Likecount",homeMissionLists.get(position).getTotalLikeCounts());
            holder.textviewRemark.setText("Withdraw");
            //if(walletHistoryData.get(position).getWalletWithdrawalAMount().equalsIgnoreCase("0")){
            //    holder.textviewAmount.setText("+Rs "+3300);
            //}else{
                holder.textviewAmount.setText("- Rs "+300);
            //}

            holder.textviewDate.setText("21-3-2019");
            prefManager = new PrefManager(context);




        } catch (Exception e) {
            Log.d("Something wrong", e.getMessage());

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewDate,textviewAmount,textviewRemark;
        public ImageView IvReqFriend;

        public Button btnBook, btncencel;

        int position = getAdapterPosition();

        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            textviewRemark =itemView.findViewById(R.id.textviewRemark);
            textviewAmount =  itemView.findViewById(R.id.textviewAmount);
            textviewDate =  itemView.findViewById(R.id.textviewDate);



            //   totalTime=(TextView)itemView.findViewById(R.id.mission_time);
        }
    }




    @Override
    public int getItemCount() {
        return 6;
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
