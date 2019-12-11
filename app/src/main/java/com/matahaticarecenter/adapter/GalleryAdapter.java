package com.matahaticarecenter.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.matahaticarecenter.R;
import com.matahaticarecenter.model.GalleryModel;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryModel> galleryModels;
    private Context context;

    public GalleryAdapter(List<GalleryModel> galleryModels, Context context) {
        this.galleryModels = galleryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(RetrofitClientInstance.IMG_URL + galleryModels.get(position).getFile()).into(holder.imageView);
        holder.textView.setText(galleryModels.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProgramActivity.class);
                intent.putExtra("IMG", galleryModels.get(position).getFile());
                intent.putExtra("TITLE", galleryModels.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_gallery_card);
            imageView = itemView.findViewById(R.id.item_gallery_img);
            textView = itemView.findViewById(R.id.item_gallery_caption);
        }
    }
}

