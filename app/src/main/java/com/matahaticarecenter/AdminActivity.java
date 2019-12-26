package com.matahaticarecenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.matahaticarecenter.model.ResModel;

import java.io.IOException;

public class AdminActivity extends AppCompatActivity {

    private StorageReference storageRef;
    private CollectionReference reference;
    private FirestoreRecyclerAdapter adapter;

    private TabLayout adminTab, programTab;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditText nameInput, descInput;
    private Button addBtn, selectImgBtn, cancelBtn;
    private ImageView previewImg;


    private Context context = AdminActivity.this;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri filePath;
    private String idRecord = "";
    private String urlImg = "";
    private String typeProgram = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameInput = findViewById(R.id.admin_name_input);
        descInput = findViewById(R.id.admin_desc_input);
        addBtn = findViewById(R.id.admin_add_btn);
        selectImgBtn = findViewById(R.id.admin_select_img_btn);
        cancelBtn = findViewById(R.id.admin_cancel_btn);
        previewImg = findViewById(R.id.admin_preview_img);

        progressBar = findViewById(R.id.admin_progressbar);
        recyclerView = findViewById(R.id.admin_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        settingTab();

        storageRef = FirebaseStorage.getInstance().getReference("images");
        reference = FirebaseFirestore.getInstance().collection("partner");
        settingFirebaseRecycler("");
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn.setEnabled(false);
                if (!TextUtils.isEmpty(nameInput.getText().toString())) {
                    if (idRecord.equals("")) {
                        idRecord = reference.document().getId();
                    }
                    if (filePath != null) {
                        uploadImage();
                    } else {
                        uploadDatabase();
                    }
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    showToast("Lengkapi form");
                    addBtn.setEnabled(true);
                }
            }
        });
        selectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetForm();
            }
        });
    }

    private void settingTab() {
        adminTab = findViewById(R.id.admin_tablayout);
        programTab = findViewById(R.id.program_tablayout);

        adminTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                progressBar.setVisibility(View.VISIBLE);
                resetForm();
                if (tab.getPosition() == 0) {
                    reference = FirebaseFirestore.getInstance().collection("partner");
                    descInput.setVisibility(View.GONE);
                    programTab.setVisibility(View.GONE);
                    settingFirebaseRecycler("");
                } else if (tab.getPosition() == 1) {
                    reference = FirebaseFirestore.getInstance().collection("gallery");
                    descInput.setVisibility(View.GONE);
                    programTab.setVisibility(View.GONE);
                    settingFirebaseRecycler("");
                } else {
                    reference = FirebaseFirestore.getInstance().collection("program");
                    descInput.setVisibility(View.VISIBLE);
                    programTab.setVisibility(View.VISIBLE);
                    settingFirebaseRecycler(typeProgram);
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        programTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                progressBar.setVisibility(View.VISIBLE);
                resetForm();
                if (tab.getPosition() == 0) {
                    typeProgram = "main";
                } else {
                    typeProgram = "side";
                }
                settingFirebaseRecycler(typeProgram);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void settingFirebaseRecycler(String typeProgram) {
        Query query = reference;
        if (!typeProgram.equals("")) {
            query = query.whereEqualTo("type", typeProgram);
        }
        FirestoreRecyclerOptions<ResModel> options = new FirestoreRecyclerOptions.Builder<ResModel>()
                .setQuery(query, ResModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ResModel, ViewHolder>(options) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position, final ResModel model) {
                progressBar.setVisibility(View.INVISIBLE);
                holder.textView.setText(model.getName());

                if (!model.getImg().equals("")) {
                    Glide.with(context).load(model.getImg()).into(holder.imageView);
                }

                holder.editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addBtn.setText("Update");
                        cancelBtn.setVisibility(View.VISIBLE);

                        idRecord = model.getId();
                        filePath = null;
                        if (!model.getImg().equals("")) {
                            urlImg = model.getImg();
                        }
                        nameInput.setText(model.getName());
                        descInput.setText(model.getDescription());
                        Glide.with(context).load(model.getImg()).into(previewImg);
                    }
                });

                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("You sure?");
                        alertDialog.setMessage(model.getName() + " will be deleted.");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES, DELETE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        if (!model.getImg().equals("")) {
                                            storageRef.child(model.getId() + ".jpg").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        reference.document(model.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                progressBar.setVisibility(View.INVISIBLE);
                                                                if (task.isSuccessful()) {
                                                                    showToast("Deleted");
                                                                } else {
                                                                    showToast(task.getException().getMessage());
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        showToast(task.getException().getMessage());
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            });
                                        } else {
                                            reference.document(model.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    if (task.isSuccessful()) {
                                                        showToast("Deleted");
                                                    } else {
                                                        showToast(task.getException().getMessage());
                                                    }
                                                }
                                            });
                                        }
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
            public ViewHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_admin, group, false);

                return new ViewHolder(view);
            }

            @Override
            public void onError(@NonNull FirebaseFirestoreException e) {
                super.onError(e);
                showToast(e.getMessage());
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
        adapter.startListening();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView;
        ImageView imageView;
        ImageButton editBtn, deleteBtn;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            textView = itemView.findViewById(R.id.item_admin_name);
            imageView = itemView.findViewById(R.id.item_admin_img);
            editBtn = itemView.findViewById(R.id.item_admin_edit);
            deleteBtn = itemView.findViewById(R.id.item_admin_delete);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                previewImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        UploadTask uploadTask = storageRef.child(idRecord + ".jpg").putFile(filePath);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    showToast("Storage success");
                    storageRef.child(idRecord + ".jpg").getDownloadUrl()
                            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        urlImg = task.getResult().toString();
                                        uploadDatabase();
                                    } else {
                                        showToast(task.getException().getMessage());
                                        Log.i("storage", task.getException().getMessage());
                                    }
                                }
                            });
                } else {
                    showToast(task.getException().getMessage());
                    Log.e("storage", task.getException().getMessage());
                    addBtn.setEnabled(true);
                }
            }
        });
    }

    private void uploadDatabase() {
        ResModel resModel = new ResModel(idRecord, nameInput.getText().toString(), urlImg, descInput.getText().toString(), typeProgram);
        if (idRecord != null) {
            reference.document(idRecord).set(resModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        showToast("Db success");
                        resetForm();
                    } else {
                        showToast(task.getException().getMessage());
                        addBtn.setEnabled(true);
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void resetForm() {
        addBtn.setText("Tambah");
        addBtn.setEnabled(true);
        cancelBtn.setVisibility(View.GONE);

        nameInput.setText("");
        previewImg.setImageResource(0);

        filePath = null;
        idRecord = "";
        urlImg = "";
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
