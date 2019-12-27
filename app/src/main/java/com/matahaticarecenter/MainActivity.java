package com.matahaticarecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.matahaticarecenter.adapter.SliderAdapter;
import com.matahaticarecenter.model.UserModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

import io.paperdb.Paper;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;
    private Button adminBtn;
    private UserModel userModel = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        Paper.init(context);

        adminBtn = findViewById(R.id.home_admin_btn);
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AdminActivity.class));
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        if (isLoggedIn()) {
            userModel = Paper.book().read("user");
            if (userModel.getLevel().equals("admin")) {
                adminBtn.setVisibility(View.VISIBLE);
            }
        } else {
            adminBtn.setVisibility(View.GONE);
        }
    }

    private void buttonOnclick(CardView cardView, final Class c) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, c));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_chat:
                if (isLoggedIn()) {
                    if (userModel.getLevel().equals("admin")) {
                        startActivity(new Intent(context, ChatListActivity.class));
                    } else {
                        startActivity(new Intent(context, ChatActivity.class));
                    }
                } else {
                    startActivity(new Intent(context, LoginActivity.class));
                }
                return true;
            case R.id.menu_account:
                if (isLoggedIn()) {
                    startActivity(new Intent(context, AccountActivity.class));
                } else {
                    startActivity(new Intent(context, LoginActivity.class));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
