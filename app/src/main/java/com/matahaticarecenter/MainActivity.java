package com.matahaticarecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.matahaticarecenter.adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        CardView profileBtn = findViewById(R.id.main_profile_btn);
        CardView programBtn = findViewById(R.id.main_program_btn);
        CardView galleryBtn = findViewById(R.id.main_gallery_btn);
        CardView contactBtn = findViewById(R.id.main_contact_btn);
        CardView program2Btn = findViewById(R.id.main_program_2_btn);
        CardView partnerBtn = findViewById(R.id.main_partner_btn);

        buttonOnclick(profileBtn, ProfileActivity.class, "");
        buttonOnclick(programBtn, ProgramActivity.class, "PROGRAM1");
        buttonOnclick(galleryBtn, GalleryActivity.class, "");
        buttonOnclick(contactBtn, ContactActivity.class, "");
        buttonOnclick(program2Btn, ProgramActivity.class, "PROGRAM2");
        buttonOnclick(partnerBtn, PartnerActivity.class, "");

        SliderView sliderView = findViewById(R.id.main_image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(context);

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void buttonOnclick(CardView cardView, final Class c, final String somekey) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, c);
                intent.putExtra("KEY", somekey);
                startActivity(intent);
            }
        });
    }
}
