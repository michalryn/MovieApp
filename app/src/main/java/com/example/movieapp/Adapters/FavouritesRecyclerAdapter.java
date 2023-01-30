package com.example.movieapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Data.MovieModel;
import com.example.movieapp.Listeners.OnMovieClickListener;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritesRecyclerAdapter extends RecyclerView.Adapter<FavouritesViewHolder>{
    Context context;
    List<MovieModel> list;
    OnMovieClickListener listener;

    public FavouritesRecyclerAdapter(Context context, List<MovieModel> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouritesViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movies_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        holder.textViewMovie.setText(list.get(position).getTitle());
        holder.textViewMovie.setSelected(true);
        if (!list.get(position).getImage().equals(""))
            Picasso.get().load(list.get(position).getImage()).into(holder.imageViewPoster);

        holder.homeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMovieClicked(list.get(holder.getAdapterPosition()).getImbdId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class FavouritesViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewPoster;
    TextView textViewMovie;
    CardView homeContainer;

    public FavouritesViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewPoster = itemView.findViewById(R.id.imageView_poster);
        textViewMovie = itemView.findViewById(R.id.textView_movie);
        homeContainer = itemView.findViewById(R.id.home_container);
    }
}