package com.example.mvr2.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvr2.ItemClickListener;
import com.example.mvr2.R;
import com.example.mvr2.model.Movie;

import java.lang.reflect.Type;
import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * Created by kununya1996 on 11/5/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private List<Movie> listData;
    private LayoutInflater inflater;
    private static Context c;
    private static ItemClickListener clickListener;

    public MovieAdapter(List<Movie> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
        this.c = c;
    }

    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MovieHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieHolder holder, int position) {
        Movie item = listData.get(position);
        holder.title.setText(item.getName());
        Glide.with(c).load(item.getPicture()).into(holder.picture);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public static class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView picture;
        public View container;
        private Typeface MyTypeface = Typeface.createFromAsset(c.getAssets(), "fonts/aparaj.ttf");

        public MovieHolder(View itemView){
            super(itemView);
            title = (AutofitTextView)itemView.findViewById(R.id.textView);
            picture = (ImageView) itemView.findViewById(R.id.imageView);
            container = itemView.findViewById(R.id.contain);
            title.setTypeface(MyTypeface);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}