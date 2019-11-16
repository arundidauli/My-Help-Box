package com.techastrum.zomato.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.techastrum.zomato.R;
import com.techastrum.zomato.activities.DetailsActivity;
import com.techastrum.zomato.model.ProductListData;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context context;
    private List<ProductListData> productListData;


    public ProductListAdapter(Context context, List<ProductListData> productListData) {
        this.context = context;
        this.productListData = productListData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(productListData.get(position).getName());
        holder.number.setText("Rs. "+productListData.get(position).getNumber());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context, DetailsActivity.class);
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
            name = itemView.findViewById(R.id.food_name);
            number = itemView.findViewById(R.id.food_price);



        }
    }


}

