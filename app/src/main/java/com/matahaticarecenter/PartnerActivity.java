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

        partnerModels.add(new PartnerModel("1", "Kementrian Pendidikan Nasional, Direktorat Jendral Pendidikan Masyarakat dan Direktorat Jendral Pendidikan Nonformal dan Informal", ""));
        partnerModels.add(new PartnerModel("1", "Dewi Hughes International Foundation", ""));
        partnerModels.add(new PartnerModel("1", "TBM @mall Plaza Semanggi Jakarta, Plaza Semanggi Jakarta dan City of Tomorrow Surabaya", ""));
        partnerModels.add(new PartnerModel("1", "Departemen Pendidikan Nasional Kota Malang", ""));
        partnerModels.add(new PartnerModel("1", "KFC Nasional, sekolah little 1 academy", ""));
        partnerModels.add(new PartnerModel("1", "BPPT Jawa Timur", ""));

        partnerAdapter = new PartnerAdapter(partnerModels, context);
        recyclerView = findViewById(R.id.partner_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(partnerAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
