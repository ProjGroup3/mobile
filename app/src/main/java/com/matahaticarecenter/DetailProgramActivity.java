package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.matahaticarecenter.model.ProgramModel;

public class DetailProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_program);

        Toolbar myToolbar = findViewById(R.id.detail_program_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));

        TextView description = findViewById(R.id.detail_program_text);
        description.setText(getIntent().getStringExtra("DESC"));

        Log.d("INTENT_GET", String.valueOf(getIntent().getExtras()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
