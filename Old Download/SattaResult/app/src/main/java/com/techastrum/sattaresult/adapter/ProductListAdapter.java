package com.techastrum.sattaresult.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techastrum.sattaresult.R;
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


    }

    @Override
    public int getItemCount() {
        return productListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,number;


        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);



        }
    }


}

