package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

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
