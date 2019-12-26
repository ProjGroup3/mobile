package com.matahaticarecenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.matahaticarecenter.model.UserModel;

import io.paperdb.Paper;

public class AccountActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText inputFullname;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private Button editBtn, updateBtn, logoutBtn;
    private boolean editState = false;
    private String idUser = "";
    private UserModel userModel = new UserModel();
    private Context context = AccountActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar myToolbar = findViewById(R.id.account_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.account_progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        inputFullname = findViewById(R.id.input_fullname);
        inputUsername = findViewById(R.id.input_username);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
        inputConfirmPassword = findViewById(R.id.input_confirm_password);
        editBtn = findViewById(R.id.edit_btn);
        updateBtn = findViewById(R.id.update_btn);
        logoutBtn = findViewById(R.id.logout_btn);

        userModel = Paper.book().read("user");
        if (userModel != null) {
            idUser = userModel.getId();
            inputFullname.setText(userModel.getFullname());
            inputUsername.setText(userModel.getUsername());
            inputEmail.setText(userModel.getEmail());
            inputPhone.setText(userModel.getPhone());
        }

        setOnlclick();

    }

    private void setOnlclick() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editState) {
                    formDeactive();
                } else {
                    formActive();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formDeactive();
                progressBar.setVisibility(View.VISIBLE);

                userModel.setFullname(inputFullname.getText().toString());
                userModel.setUsername(inputUsername.getText().toString());
                userModel.setEmail(inputEmail.getText().toString());
                userModel.setPhone(inputPhone.getText().toString());
                userModel.setPassword(inputPassword.getText().toString());

                if (isFormFilled()) {
                    if (isValidPass()) {
                        if (isValidEmail()) {
                            progressBar.setVisibility(View.VISIBLE);
                            updateProfile();
                        } else {
                            showToast("Email tidak valid");
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        showToast("Konfirmasi password tidak sama atau minimal 8 karakter");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    showToast("Lengkapi form");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Paper.book().delete("user");
                finish();
            }
        });
    }

    private void updateProfile() {
        FirebaseAuth.getInstance().getCurrentUser().updateEmail(inputEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().getCurrentUser().updatePassword(inputPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseFirestore.getInstance().collection("users")
                                                        .document(idUser).set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(context, "Update sukses", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        } else {
                                                            showToast(task.getException().getMessage());
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            formActive();
                                                        }
                                                    }
                                                });
                                            } else {
                                                showToast(task.getException().getMessage());
                                                progressBar.setVisibility(View.INVISIBLE);
                                                formActive();
                                            }
                                        }
                                    });
                        } else {
                            showToast(task.getException().getMessage());
                            progressBar.setVisibility(View.INVISIBLE);
                            formActive();
                        }
                    }
                });
    }

    private void formActive() {
        editState = true;
        inputFullname.setEnabled(true);
        inputUsername.setEnabled(true);
        inputEmail.setEnabled(true);
        inputPhone.setEnabled(true);
        inputPassword.setEnabled(true);
        inputConfirmPassword.setEnabled(true);
        updateBtn.setEnabled(true);
    }

    private void formDeactive() {
        editState = false;
        inputFullname.setEnabled(false);
        inputUsername.setEnabled(false);
        inputEmail.setEnabled(false);
        inputPhone.setEnabled(false);
        inputPassword.setEnabled(false);
        inputConfirmPassword.setEnabled(false);
        updateBtn.setEnabled(false);
    }

    private boolean isValidEmail() {
        return (Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches());
    }

    private boolean isValidPass() {
        return (inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())
                && inputPassword.length() >= 8);
    }

    private boolean isFormFilled() {
        return (!inputFullname.getText().toString().isEmpty() &&
                !inputUsername.getText().toString().isEmpty() &&
                !inputEmail.getText().toString().isEmpty() &&
                !inputPhone.getText().toString().isEmpty() &&
                !inputPassword.getText().toString().isEmpty()
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
