package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matahaticarecenter.adapter.PartnerAdapter;
import com.matahaticarecenter.model.PartnerModel;

import java.util.ArrayList;
import java.util.List;

public class PartnerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);


    }
}
