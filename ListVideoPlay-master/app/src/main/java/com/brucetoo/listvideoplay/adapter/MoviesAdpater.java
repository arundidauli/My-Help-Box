package com.brucetoo.listvideoplay.adapter;

import android.content.Context;
import android.support.animation.VideoList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.brucetoo.listvideoplay.R;

import java.util.List;

public class MoviesAdpater extends RecyclerView.Adapter<MoviesAdpater.ViewHolder> {

    private Context context;
    private List<VideoList> subcategoryListData;

    public MoviesAdpater(Context context, List<VideoList> subcategoryListData) {
        this.context = context;
        this.subcategoryListData = subcategoryListData;
    }

    @Override
    public MoviesAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);


        MoviesAdpater.ViewHolder viewHolder = new MoviesAdpater.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MoviesAdpater.ViewHolder holder, final int position) {
        holder.title.setText(subcategoryListData.get(position).getPosition());
        /*Picasso.get()
                .load(subcategoryListData.get(position).getImageurl())
                .into(holder.subcategoryImage);*/


    }

    @Override
    public int getItemCount() {
        return subcategoryListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rr_main;
        ImageView coverImage;
        TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);
            setIsRecyclable(false);
            coverImage = itemView.findViewById(R.id.img_cover);
        }
    }


}

