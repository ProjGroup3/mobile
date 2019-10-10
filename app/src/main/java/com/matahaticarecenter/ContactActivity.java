package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.matahaticarecenter.model.Message;
import com.matahaticarecenter.networking.NetworkService;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class ContactActivity extends AppCompatActivity {

    private EditText titleInput, messageInput;
    private Button sendBtn;
    private ProgressBar progressBar;

    private Context context = ContactActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://polinema-english-club.com/api/")
//                .baseUrl("https://japancornerpolinema.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkService service = retrofit.create(NetworkService.class);

        Toolbar myToolbar = findViewById(R.id.contact_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleInput = findViewById(R.id.contact_subject_input);
        messageInput = findViewById(R.id.contact_message_input);
        sendBtn = findViewById(R.id.contact_send_btn);
        progressBar = findViewById(R.id.contact_progress_bar);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(titleInput.getText().toString()) && !TextUtils.isEmpty(messageInput.getText().toString())) {
                    progressBar.setVisibility(View.VISIBLE);
                    titleInput.setEnabled(false);
                    messageInput.setEnabled(false);
//                    Call<String> listCall = service.sendMessage(1, titleInput.getText().toString(), messageInput.getText().toString());
//                    listCall.enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            Log.d("RESPONSE", response.message());
//                            resetForm("success");
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//                            Log.d("RESPONSE", t.getMessage());
//                            resetForm("failed");
//                        }
//                    });
                    service.getVocabCategories().enqueue(new Callback<HashMap<String, Object>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                            Toast.makeText(context, "Terkirim " + response.body().get("status_code").toString(), Toast.LENGTH_SHORT).show();
                            resetForm("success");
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                            Log.d("RESPONSE", t.getMessage());
                            resetForm("failed");
                        }
                    });
                } else {
                    Toast.makeText(context, "Tolong lengkapi form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetForm(String string) {
        progressBar.setVisibility(View.GONE);
        titleInput.setEnabled(true);
        messageInput.setEnabled(true);

        if (string.equals("success")) {
            titleInput.setText("");
            messageInput.setText("");
        }
    }

}
