package com.matahaticarecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class DetailMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseRecyclerAdapter adapter;

    private String TAG = "LOG";

    private FrameLayout frameLayout;
    private TextView harga, menu;
    private ImageView imageMenu, mImageView;
    private RecyclerView recyclerView;

    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private String firebaseUrlImage;

    private Context context = DetailMenu.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_menu);


        frameLayout = findViewById(R.id.fl);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        // initialitation Firebase Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();


        //get data dari firebase
//        final String mMenu = getIntent().getStringExtra("name");
        final String mHarga = getIntent().getStringExtra("name");
        final String mImage = getIntent().getStringExtra("file");
//        Toast.makeText(context, sessionId, Toast.LENGTH_SHORT).show();

//        menu = findViewById(R.id.item_nama_menu);
        harga = findViewById(R.id.item_harga);
        imageMenu = findViewById(R.id.ic_user);

//        menu.setText("Menu      : " + mMenu);
        harga.setText("Harga    : " + mHarga);
        Glide.with(context).load(mImage).into(imageMenu);

    }
}

