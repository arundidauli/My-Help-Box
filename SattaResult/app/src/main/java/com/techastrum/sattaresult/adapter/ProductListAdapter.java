package com.techastrum.sattaresult.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.techastrum.sattaresult.R;
import com.techastrum.sattaresult.activities.ReportActivity;
import com.techastrum.sattaresult.model.ProductListData;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context context;
    private List<ProductListData> productListData;


    public ProductListAdapter(Context context, List<ProductListData> productListData) {
        this.context = context;
        this.productListData = productListData;
    }


    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_meditation, parent, false);


        ProductListAdapter.ViewHolder viewHolder = new ProductListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, final int position) {

        holder.name.setText(productListData.get(position).getName());
        holder.number.setText(productListData.get(position).getNumber());
        if(!productListData.get(position).getNumber().equals("")){
            holder.cleared.setVisibility(View.VISIBLE);
            holder.pending.setVisibility(View.GONE);

        }else {
            holder.pending.setVisibility(View.VISIBLE);
            holder.cleared.setVisibility(View.GONE);
            holder.number.setText("Pending");

        }

        holder.txt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context, ReportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, number, txt_view;
        private ImageView cleared,pending;


        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            txt_view = itemView.findViewById(R.id.txt_view);
            cleared = itemView.findViewById(R.id.img_cleared);
            pending = itemView.findViewById(R.id.img_pending);


        }
    }


}

