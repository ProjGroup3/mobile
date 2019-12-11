package com.matahaticarecenter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.matahaticarecenter.model.ResponseLogin;
import com.matahaticarecenter.model.UserModel;
import com.matahaticarecenter.networking.NetworkService;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private NetworkService service = RetrofitClientInstance.getRetrofitInstance()
            .create(NetworkService.class);

    EditText inputUsername, inputPassword;
    Button btnlogin;
    TextView singup;

    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Paper.init(context);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        btnlogin = findViewById(R.id.login_btn);
        singup = findViewById(R.id.singup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputUsername.getText().toString().isEmpty() && !inputPassword.getText().toString().isEmpty()) {
                    loginUser(inputUsername.getText().toString(), inputPassword.getText().toString());
                } else {
                    Toast.makeText(context, "Isi username dan password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(final String username, String password) {
        Call<ResponseLogin> loginCall = service.loginCall(username, password);
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus_code().equals(200)) {
                        UserModel userModel = response.body().getResult().get(0);
                        Paper.book().write("user", userModel);

                        finish();
                        Toast.makeText(context, "Selamat Datang " + username, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
