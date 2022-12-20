package com.example.movieapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Models.Actor;
import com.example.movieapp.R;

import java.util.List;

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastViewHolder>{

    Context context;
    List<Actor> list;

    public CastRecyclerAdapter(Context context, List<Actor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.textViewActor.setText(list.get(position).getName());
        holder.textViewCharacter.setText(list.get(position).getAsCharacter());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CastViewHolder extends RecyclerView.ViewHolder {

    TextView textViewActor, textViewCharacter;

    public CastViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewActor = itemView.findViewById(R.id.textView_actor);
        textViewCharacter = itemView.findViewById(R.id.textView_character);
    }
}