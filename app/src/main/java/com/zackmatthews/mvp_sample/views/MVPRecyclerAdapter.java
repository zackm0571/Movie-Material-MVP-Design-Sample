package com.zackmatthews.mvp_sample.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zackmatthews.mvp_sample.R;
import com.zackmatthews.mvp_sample.models.GenericItem;
import com.zackmatthews.mvp_sample.models.Movie;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zmatthews on 3/13/18.
 */

public class MVPRecyclerAdapter extends RecyclerView.Adapter<MVPRecyclerAdapter.MyViewHolder>{

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, year, director;
        public CircleImageView img;
        public ViewGroup container;
        public Context ctx;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_movie_name);
            year = itemView.findViewById(R.id.tv_movie_year);
            director = itemView.findViewById(R.id.tv_director);
            img = itemView.findViewById(R.id.circleImage);
            container = itemView.findViewById(R.id.row_container);
            ctx = itemView.getContext();
        }
    }
    protected List<Movie> data = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context ctx = parent.getContext();
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.recycler_row_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movie = data.get(position);
        holder.title.setText(movie.getTitle());
        holder.director.setText(movie.getDirector());
        holder.year.setText(movie.getYear());

        Bitmap img = movie.getImg();
        if(img != null) {
            holder.img.setImageBitmap(img);
        }
        else if(Movie.useDefaultImg){
            holder.img.setImageResource(GenericItem.defaultImgRes);
        }

        final Context ctx = holder.ctx;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.setItem(movie);
                ctx.startActivity(new Intent(ctx, DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Movie> data){
        this.data = data;
    }

    public void add(Movie movie){
        data.add(movie);
        notifyDataSetChanged();
    }
}
