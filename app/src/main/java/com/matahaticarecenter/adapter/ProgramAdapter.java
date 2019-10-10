package com.matahaticarecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.matahaticarecenter.DetailProgramActivity;
import com.matahaticarecenter.ProgramActivity;
import com.matahaticarecenter.R;
import com.matahaticarecenter.model.ProgramModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {

    private List<ProgramModel> program;
    private Context context;

    public ProgramAdapter(List<ProgramModel> program, Context context) {
        this.program = program;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(program.get(position).getImg()).into(holder.imageView);
        holder.textView.setText(program.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProgramActivity.class);
                intent.putExtra("IMG", program.get(position).getImg());
                intent.putExtra("TITLE", program.get(position).getTitle());
                intent.putExtra("DESC", program.get(position).getDesctiption());
                intent.putExtra("CONTENT", program.get(position).getContent());
                intent.putExtra("TIME", program.get(position).getTime());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return program.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_program_card);
            imageView = itemView.findViewById(R.id.item_program_img);
            textView = itemView.findViewById(R.id.item_program_title);
        }
    }
}

