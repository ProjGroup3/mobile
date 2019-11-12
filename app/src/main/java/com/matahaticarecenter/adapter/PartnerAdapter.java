package com.matahaticarecenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.matahaticarecenter.R;
import com.matahaticarecenter.model.PartnerModel;

import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {

    private List<PartnerModel> partnerModels;
    private Context context;

    public PartnerAdapter(List<PartnerModel> partner, Context context) {
        this.partnerModels = partner;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(partnerModels.get(position).getLogo()).into(holder.imageView);

        String text = partnerModels.get(position).getName();
        if (text.length() > 60) {
            text = text.substring(0, 60) + " ...";
        }
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return partnerModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout cardView;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_partner_card);
            imageView = itemView.findViewById(R.id.item_partner_logo);
            textView = itemView.findViewById(R.id.item_partner_name);
        }
    }
}

