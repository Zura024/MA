package com.example.lakhs.movieapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lakhs.movieapplication.R;
import com.example.lakhs.movieapplication.activity.SingleMovieActivity;
import com.example.lakhs.movieapplication.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> data = new ArrayList<Movie>();
    Context context;
    private LayoutInflater layoutInflater;

    public MovieAdapter(ArrayList<Movie> data,Context context) {
        layoutInflater = layoutInflater.from(context);
        this.context = context;
        this.data = data;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movie_main, parent, false);
        ViewHolder holder = new ViewHolder(view,data,context);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie current = data.get(position);
        //holder.title.setText(current.getTitle());
        //holder.des.setText(current.getDesc());
        //holder.rate.setText(current.getRate());
        //holder.year.setText(current.getYear());
        //Picasso.with(holder.imageView.getContext()).load().into(holder.imageView);
        Picasso.with(context)
                .load(current.getRsc())
                .resize(350, 500)
                .centerCrop()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView,title,des,year;
        ArrayList<Movie> data = new ArrayList<Movie>();
        Context context;

        public ViewHolder(View itemView, ArrayList<Movie> data, Context context) {
            super(itemView);
            this.context = context;
            this.data = data;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.iv_movie);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = this.data.get(position);
            Intent intent = new Intent(this.context, SingleMovieActivity.class);
            intent.putExtra("year", movie.getYear());
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("rate", movie.getRate());
            intent.putExtra("desc",movie.getDesc());
            intent.putExtra("rsc", movie.getRsc());
            intent.putExtra("id",movie.getId());
            this.context.startActivity(intent);

        }

    }
}
