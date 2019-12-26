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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.matahaticarecenter.adapter.PartnerAdapter;
import com.matahaticarecenter.model.PartnerModel;
import com.matahaticarecenter.model.ResModel;

import java.util.ArrayList;
import java.util.List;

public class PartnerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PartnerAdapter partnerAdapter;
    private List<PartnerModel> partnerModels = new ArrayList<>();
    private Context context = PartnerActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);

        Toolbar myToolbar = findViewById(R.id.partner_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        partnerAdapter = new PartnerAdapter(partnerModels, context);
        recyclerView = findViewById(R.id.partner_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(partnerAdapter);

        FirebaseFirestore.getInstance().collection("partner")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    partnerModels.clear();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                        ResModel res = snapshot.toObject(ResModel.class);
                        partnerModels.add(new PartnerModel(res.getId(), res.getName(), res.getImg()));
                    }
                    partnerAdapter.notifyDataSetChanged();
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
