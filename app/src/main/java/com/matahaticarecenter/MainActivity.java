package com.matahaticarecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.matahaticarecenter.adapter.SliderAdapter;
import com.matahaticarecenter.model.UserModel;
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
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_chat) {
            UserModel user = Paper.book().read("user");
            if (user != null) {
                Intent intent = new Intent(context, ChatActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
            return true;
        } else if (item.getItemId() == R.id.menu_logout) {
            Paper.book().delete("user");
            item.setVisible(false);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logout = menu.findItem(R.id.menu_logout);
        UserModel user = Paper.book().read("user");
        if (user != null) {
            logout.setVisible(true);
        } else {
            logout.setVisible(false);
        }
        return true;
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
