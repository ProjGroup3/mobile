package com.matahaticarecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.matahaticarecenter.adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(context);

        AnimationDrawable animDrawable = (AnimationDrawable) findViewById(R.id.main_layout).getBackground();
        animDrawable.setEnterFadeDuration(10);
        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        CardView profileBtn = findViewById(R.id.main_profile_btn);
        CardView programBtn = findViewById(R.id.main_program_btn);
        CardView galleryBtn = findViewById(R.id.main_gallery_btn);
        CardView contactBtn = findViewById(R.id.main_contact_btn);

        buttonOnclick(profileBtn, ProfileActivity.class);
        buttonOnclick(programBtn, ProgramActivity.class);
        buttonOnclick(galleryBtn, GalleryActivity.class);
        buttonOnclick(contactBtn, ContactActivity.class);

        SliderView sliderView = findViewById(R.id.main_image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(context);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();
    }

    private void buttonOnclick(CardView cardView, final Class c) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, c));
            }
        });
    }
}
