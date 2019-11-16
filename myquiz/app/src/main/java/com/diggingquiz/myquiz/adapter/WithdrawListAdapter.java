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
import com.diggingquiz.myquiz.beans.WithdrawlistData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanuj yadav on 30/11/2017.
 */

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.ViewHolder>  {
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
    ArrayList<WithdrawlistData>withdrawlistDatas;
    public WithdrawListAdapter(Context context, ArrayList<WithdrawlistData>withdrawlistDataArrayList) {
        this.context = context;
        this.withdrawlistDatas=withdrawlistDataArrayList;



    }

    @Override
    public WithdrawListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_withdraw, parent, false);

        WithdrawListAdapter.ViewHolder viewHolder = new WithdrawListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WithdrawListAdapter.ViewHolder holder, final int position) {

        try {
            //Log.e("Likecount",homeMissionLists.get(position).getTotalLikeCounts());
            holder.tv_withdraw.setText("\u20B9 "+withdrawlistDatas.get(position).getWalletAddAMount());
            holder.tv_status.setText(withdrawlistDatas.get(position).getRemark());
            holder.tv_date.setText(withdrawlistDatas.get(position).getTranjectionDate());

            prefManager = new PrefManager(context);




        } catch (Exception e) {
            Log.d("Something wrong", e.getMessage());

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewDate,tv_date,tv_withdraw,tv_status;
        public ImageView IvReqFriend;

        public Button btnBook, btncencel;

        int position = getAdapterPosition();

        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            tv_withdraw =itemView.findViewById(R.id.tv_withdraw);
            tv_status =itemView.findViewById(R.id.tv_status);
            tv_date =itemView.findViewById(R.id.tv_date);




            //   totalTime=(TextView)itemView.findViewById(R.id.mission_time);
        }
    }




    @Override
    public int getItemCount() {
        return withdrawlistDatas.size();
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
