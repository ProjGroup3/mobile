package com.matahaticarecenter;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class BaseActivity extends AppCompatActivity {

    public boolean isLoggedIn() {
        return Paper.book().read("user") != null;
    }
}
