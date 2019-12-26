package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.matahaticarecenter.adapter.GalleryAdapter;
import com.matahaticarecenter.model.GalleryModel;
import com.matahaticarecenter.model.ResModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private List<GalleryModel> galleryModels = new ArrayList<>();
    private Context context = GalleryActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar myToolbar = findViewById(R.id.gallery_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        galleryAdapter = new GalleryAdapter(galleryModels, context);
        recyclerView = findViewById(R.id.gallery_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(galleryAdapter);

        FirebaseFirestore.getInstance().collection("gallery")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    galleryModels.clear();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                        ResModel res = snapshot.toObject(ResModel.class);
                        galleryModels.add(new GalleryModel(res.getId(), res.getName(), res.getImg()));
                    }
                    galleryAdapter.notifyDataSetChanged();
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
