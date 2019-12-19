package com.matahaticarecenter.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
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
        final int[] imgs = {
                R.drawable.picture_1,
                R.drawable.picture_2,
                R.drawable.picture_3,
                R.drawable.picture_4,
        };
        final String[] titles = {
                "Pelantikan Duta",
                "Travel Edukasi",
                "Positive Character Tour",
                "Positive Character Tour (ASEAN)",
        };

        viewHolder.textViewDescription.setText(titles[position]);
        Glide.with(viewHolder.itemView)
                .load(imgs[position])
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_zoom_image);
                PhotoView photoView = dialog.findViewById(R.id.imageView);
                Glide.with(context)
                        .load(imgs[position])
                        .into(photoView);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            }
        });
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
