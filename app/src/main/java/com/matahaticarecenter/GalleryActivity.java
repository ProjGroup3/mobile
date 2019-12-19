package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matahaticarecenter.adapter.GalleryAdapter;
import com.matahaticarecenter.model.GalleryModel;
import com.matahaticarecenter.model.ResponseGallery;
import com.matahaticarecenter.model.ResponseLogin;
import com.matahaticarecenter.networking.NetworkService;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryActivity extends AppCompatActivity {

    private NetworkService service = RetrofitClientInstance.getRetrofitInstance()
            .create(NetworkService.class);

    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private List<GalleryModel> galleryModels = new ArrayList<>();
    private Context context = GalleryActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Call<ResponseGallery> galleryCall = service.getGalleryCall();


        Toolbar myToolbar = findViewById(R.id.gallery_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        galleryAdapter = new GalleryAdapter(galleryModels, context);
        recyclerView = findViewById(R.id.gallery_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(galleryAdapter);

        galleryCall.enqueue(new Callback<ResponseGallery>() {
            @Override
            public void onResponse(Call<ResponseGallery> call, Response<ResponseGallery> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatus_code().equals(200)) {
                        List<GalleryModel> galleryModels_tmp = response.body().getResult();
                        galleryModels.addAll(galleryModels_tmp);
                        galleryAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(context, "Gagal load galeri", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGallery> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
