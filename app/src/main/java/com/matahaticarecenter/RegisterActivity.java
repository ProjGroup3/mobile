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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.matahaticarecenter.model.UserModel;
import com.matahaticarecenter.networking.NetworkService;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import java.util.Date;

import io.paperdb.Paper;

public class RegisterActivity extends AppCompatActivity {

    private NetworkService service = RetrofitClientInstance.getRetrofitInstance()
            .create(NetworkService.class);

    private ProgressBar progressBar;
    private EditText inputFullname;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private Button btnReg;

    private Context context = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Paper.init(context);

        progressBar = findViewById(R.id.register_progressbar);
        progressBar.setVisibility(View.GONE);
        inputFullname = findViewById(R.id.input_fullname);
        inputUsername = findViewById(R.id.input_username);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
        inputConfirmPassword = findViewById(R.id.input_confirm_password);
        btnReg = findViewById(R.id.btnregister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormFilled()) {
                    if (isConfirmPassSame()) {
                        if (isValidEmail()) {
                            progressBar.setVisibility(View.VISIBLE);
                            registerUser();
                        } else {
                            showToast("Email tidak valid");
                        }
                    } else {
                        showToast("Konfirmasi password tidak sama");
                    }
                } else {
                    showToast("Lengkapi form");
                }
            }
        });
    }

    private void registerUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                inputEmail.getText().toString(),
                inputPassword.getText().toString()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    saveUser(task);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUser(Task<AuthResult> task) {
        final FirebaseUser user = task.getResult().getUser();
        UserModel userModel = new UserModel(
                user.getUid(),
                inputUsername.getText().toString(),
                inputPassword.getText().toString(),
                "user",
                inputEmail.getText().toString(),
                inputFullname.getText().toString(),
                inputPhone.getText().toString(),
                "",
                true,
                new Date().toString(),
                new Date().toString()
        );
        FirebaseFirestore.getInstance().collection("users").document(user.getUid())
                .set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Paper.book().write("user", new UserModel(
                            user.getUid(),
                            inputUsername.getText().toString(),
                            inputPassword.getText().toString(),
                            "user",
                            inputEmail.getText().toString(),
                            inputFullname.getText().toString(),
                            inputPhone.getText().toString(),
                            "",
                            true,
                            new Date().toString(),
                            new Date().toString()
                    ));
                    Toast.makeText(context, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(context, "Data tidak mau disimpan", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean isValidEmail() {
        return (Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches());
    }

    private boolean isConfirmPassSame() {
        return (inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString()));
    }

    private boolean isFormFilled() {
        return (!inputFullname.getText().toString().isEmpty() &&
                !inputUsername.getText().toString().isEmpty() &&
                !inputEmail.getText().toString().isEmpty() &&
                !inputPhone.getText().toString().isEmpty() &&
                !inputPassword.getText().toString().isEmpty()
        );
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
