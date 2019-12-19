package com.matahaticarecenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.matahaticarecenter.model.GalleryModel;

import java.io.IOException;

public class ListMenu extends AppCompatActivity {


    private FirebaseRecyclerAdapter adapter;

    private EditText hargaInput, editTextName_menu;
    private RecyclerView menuRv;

    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private ImageView mImageView;
    private Button mButtonSelect, mButtonUpload;
    private String firebaseUrlImage;

    private String  id;

    private Context context = ListMenu.this;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_menu);

        //realtime
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("gallery");

        // Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mButtonSelect = findViewById(R.id.buttonImage);
        mButtonUpload = findViewById(R.id.buttonUpload);
        mImageView = findViewById(R.id.imageView);
        editTextName_menu = findViewById(R.id.editTextName_menu);
        menuRv = findViewById(R.id.rv);

        //View
//        menuInput = findViewById(R.id.menu_input);
        hargaInput = findViewById(R.id.harga_input);

        mButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //file path
                //input storage
                StorageReference riversRef = mStorageRef.child("images/"+editTextName_menu.getText().toString()+".jpg");

                BitmapFactory.Options options = new BitmapFactory.Options();
                UploadTask uploadTask = riversRef.putFile(filePath);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Storage Success", Toast.LENGTH_SHORT).show();
                        mStorageRef.child("images/"+editTextName_menu.getText()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                uploadRealtimeDatabase(uri.toString());
                                hargaInput.setText("");
                                editTextName_menu.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("storage", e.getMessage().toString());
                            }
                        });
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Storage gagal", Toast.LENGTH_SHORT).show();
                        Log.e("storage",e.getMessage());
                    }
                });
            }
        });

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("gallery");

        FirebaseRecyclerOptions<GalleryModel> options =
                new FirebaseRecyclerOptions.Builder<GalleryModel>()
                        .setQuery(query, GalleryModel.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<GalleryModel, ListMenu.MenuHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ListMenu.MenuHolder menuHolder, int i, @NonNull final GalleryModel galleryModel) {
                menuHolder.setHarga(galleryModel.getName());

                Toast.makeText(context, galleryModel.getId(), Toast.LENGTH_SHORT).show();

                if (galleryModel.getFile() != null){
                    menuHolder.setImageViewBtn(galleryModel.getFile());
                }

                menuHolder.detailBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ListMenu.this).create();
                        alertDialog.setTitle("Edit " + galleryModel.getName());

                        LinearLayout layout = new LinearLayout(context);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        final EditText hargaUpdate = new EditText(context);
                        final EditText photomenuUpdate = new EditText(context);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        hargaUpdate.setText(galleryModel.getName());
                        photomenuUpdate.setText(galleryModel.getFile());

                        hargaUpdate.setLayoutParams(lp);
                        photomenuUpdate.setLayoutParams(lp);

                        Intent intent = new Intent(getBaseContext(), DetailMenu.class);
                        intent.putExtra("name", galleryModel.getName());
                        intent.putExtra("file", galleryModel.getFile());
                        startActivity(intent);
                    }
                });

                menuHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ListMenu.this).create();
                        alertDialog.setTitle("Edit " + galleryModel.getName());

                        LinearLayout layout = new LinearLayout(context);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        final EditText hargaUpdate = new EditText(context);
                        final EditText photomenuUpdate = new EditText(context);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        hargaUpdate.setText(galleryModel.getName());
                        photomenuUpdate.setText(galleryModel.getFile());

                        hargaUpdate.setLayoutParams(lp);
                        photomenuUpdate.setLayoutParams(lp);

                        layout.addView(hargaUpdate);
                        layout.addView(photomenuUpdate);
                        alertDialog.setView(layout);
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        GalleryModel updatedmenu = new GalleryModel(id, hargaUpdate.getText().toString(), photomenuUpdate.getText().toString());
                                        myRef.child(galleryModel.getId()).setValue(updatedmenu).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });

                menuHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ListMenu.this).create();
                        alertDialog.setTitle("You sure?");
                        alertDialog.setMessage(galleryModel.getName() + " will be deleted.");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES, DELETE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        myRef.child(galleryModel.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    StorageReference photoRef = mStorageRef.child("images/"+galleryModel.getId().toString()+".jpg");
                                                    photoRef.delete();
                                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });
            }
            @Override
            public ListMenu.MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_res, parent, false);

                return new ListMenu.MenuHolder(view);
            }
        };
        // Adpter Recyclerview untuk me-list data
        menuRv.setLayoutManager(new LinearLayoutManager(context));
        menuRv.setAdapter(adapter);
    }

    private void uploadRealtimeDatabase(String firebaseUrlImage) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menu");

        String key = myRef.push().getKey();
        Log.d("key", key);

        //process nulis
        String id = myRef.push().getKey();
        GalleryModel mMenu = new GalleryModel(id, hargaInput.getText().toString(), firebaseUrlImage);
        myRef.child(id).setValue(mMenu);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String namaMenu = ds.child("name").getValue(String.class);
//                    String harga = ds.child("harga").getValue(String.class);
                    Log.d("TAG", namaMenu);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
//        private final TextView namamenuField;
        private final TextView hargaField;
        private ImageView imageViewBtn;
        private ImageView editBtn;
        private ImageView deleteBtn;
        private ImageView detailBtn;

        MenuHolder(@NonNull View itemView) {
            super(itemView);
//            namamenuField = itemView.findViewById(R.id.item_name);
            hargaField = itemView.findViewById(R.id.item_type);
            imageViewBtn = itemView.findViewById(R.id.ic_user);
            editBtn = itemView.findViewById(R.id.ic_edit);
            detailBtn = itemView.findViewById(R.id.ic_detail);
            deleteBtn = itemView.findViewById(R.id.ic_delete);
        }

//        private void setMenu(@Nullable String menu) {
//            namamenuField.setText(menu);
//        }
        private void setHarga(@Nullable String harga) {
            hargaField.setText(harga);
        }
        private void setImageViewBtn(String url) {
            Glide.with(context).load(url).into(imageViewBtn);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }




}
