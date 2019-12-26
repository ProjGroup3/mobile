package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.matahaticarecenter.adapter.ProgramAdapter;
import com.matahaticarecenter.model.ProgramModel;
import com.matahaticarecenter.model.ResModel;

import java.util.ArrayList;
import java.util.List;

public class ProgramActivity extends AppCompatActivity {

    private TabLayout tableLayout;
    private RecyclerView recyclerView;
    private ProgramAdapter programAdapter;
    private List<ProgramModel> programModels = new ArrayList<>();
    private Context context = ProgramActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Toolbar myToolbar = findViewById(R.id.program_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        programAdapter = new ProgramAdapter(programModels, context);
        recyclerView = findViewById(R.id.program_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(programAdapter);

        tableLayout = findViewById(R.id.program_tablayout);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getData("main");
                } else {
                    getData("side");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getData("main");
    }

    private void getData(String type) {
        FirebaseFirestore.getInstance().collection("program").whereEqualTo("type", type)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    programModels.clear();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                        ResModel res = snapshot.toObject(ResModel.class);
                        programModels.add(new ProgramModel(res.getImg(), res.getName(), res.getDescription()));
                    }
                    programAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
