package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matahaticarecenter.adapter.GalleryAdapter;
import com.matahaticarecenter.model.GalleryModel;

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

        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));
        galleryModels.add(new GalleryModel("1", "https://koenig-media.raywenderlich.com/uploads/2016/08/GridView-feature.png", "Yes"));

        galleryAdapter = new GalleryAdapter(galleryModels, context);
        recyclerView = findViewById(R.id.gallery_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(galleryAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
