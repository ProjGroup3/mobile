package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.matahaticarecenter.adapter.ProgramAdapter;
import com.matahaticarecenter.model.ProgramModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

    }



}
