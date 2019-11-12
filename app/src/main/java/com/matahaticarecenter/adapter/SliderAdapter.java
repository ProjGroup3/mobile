package com.matahaticarecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.matahaticarecenter.DetailProgramActivity;
import com.matahaticarecenter.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final int imgs[] = {
                R.drawable.picture_1,
                R.drawable.picture_2,
                R.drawable.picture_3,
                R.drawable.picture_4,
        };
        final String titles[] = {
                "Pelantikan Duta",
                "Travel Edukasi",
                "Positive Character Tour",
                "Positive Character Tour (ASEAN)",
        };
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProgramActivity.class);
                intent.putExtra("IMG", imgs[position]);
                intent.putExtra("TITLE", titles[position]);
                intent.putExtra("DESC", "DESCRIPTION ....'");
                context.startActivity(intent);
            }
        });
        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("Pelantikan Duta");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.picture_1)
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("Travel Edukasi");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.picture_2)
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("Positive Character Tour");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.picture_3)
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                viewHolder.textViewDescription.setText("Positive Character Tour (ASEAN)");
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.picture_4)
                        .into(viewHolder.imageViewBackground);
                break;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        ViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
