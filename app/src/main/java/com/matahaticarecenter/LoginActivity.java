package com.matahaticarecenter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.matahaticarecenter.model.UserModel;
import com.matahaticarecenter.networking.NetworkService;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import java.util.Date;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private NetworkService service = RetrofitClientInstance.getRetrofitInstance()
            .create(NetworkService.class);

    ProgressBar progressBar;
    EditText inputUsername, inputPassword;
    Button btnlogin;
    TextView singup;

    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Paper.init(context);

        progressBar = findViewById(R.id.login_progressbar);
        progressBar.setVisibility(View.GONE);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        btnlogin = findViewById(R.id.login_btn);
        singup = findViewById(R.id.singup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputUsername.getText().toString().isEmpty() && !inputPassword.getText().toString().isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    loginFirebase(inputUsername.getText().toString(), inputPassword.getText().toString());
                } else {
                    Toast.makeText(context, "Isi email dan password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(context, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginFirebase(final String username, final String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String idUser = task.getResult().getUser().getUid();
                            FirebaseFirestore.getInstance().collection("users")
                                    .document(idUser)
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        Paper.book().write("user", new UserModel(
                                                task.getResult().getString("id"),
                                                task.getResult().getString("username"),
                                                task.getResult().getString("password"),
                                                task.getResult().getString("level"),
                                                task.getResult().getString("email"),
                                                task.getResult().getString("fullname"),
                                                task.getResult().getString("phone"),
                                                task.getResult().getString("avatar"),
                                                true,
                                                new Date().toString(),
                                                new Date().toString()
                                        ));
                                        Toast.makeText(context, "Selamat Datang " + username, Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
