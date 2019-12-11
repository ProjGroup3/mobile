package com.matahaticarecenter;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.matahaticarecenter.model.ResponseRegister;
import com.matahaticarecenter.networking.NetworkService;
import com.matahaticarecenter.networking.RetrofitClientInstance;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private NetworkService service = RetrofitClientInstance.getRetrofitInstance()
            .create(NetworkService.class);

    private EditText inputFullname;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private Button btnReg;

    private Context context = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Paper.init(context);

        inputFullname = findViewById(R.id.input_fullname);
        inputUsername = findViewById(R.id.input_username);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
        btnReg = findViewById(R.id.btnregister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputFullname.getText().toString().isEmpty() &&
                        !inputUsername.getText().toString().isEmpty() &&
                        !inputEmail.getText().toString().isEmpty() &&
                        !inputPhone.getText().toString().isEmpty() &&
                        !inputPassword.getText().toString().isEmpty()
                ) {
                    registerUser(
                            inputFullname.getText().toString(),
                            inputUsername.getText().toString(),
                            inputEmail.getText().toString(),
                            inputPhone.getText().toString(),
                            inputPassword.getText().toString()
                    );
                } else {
                    Toast.makeText(context, "Isi username dan password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(String fullname, final String username, String email, String phone, String password) {
        Call<ResponseRegister> regCall = service.registerCall(fullname, username, email, phone, password);
        regCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus_code().equals(200) && response.body().getResult().equals(true)) {
                        finish();
                        Toast.makeText(context, "Daftar berhasil, silahkan login!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
