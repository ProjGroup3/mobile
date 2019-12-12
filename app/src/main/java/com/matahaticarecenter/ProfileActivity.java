package com.matahaticarecenter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    private Context context = ProfileActivity.this;
    ArrayAdapter<CharSequence> adapter1, adapter2, value1, value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView listView1 = findViewById(R.id.profile_listview_1);
        ListView listView2 = findViewById(R.id.profile_listview_2);

        adapter1 = ArrayAdapter.createFromResource(this, R.array.menu_profile_arr1, android.R.layout.simple_list_item_1);
        value1 = ArrayAdapter.createFromResource(this, R.array.menu_profile_value_arr1, android.R.layout.simple_list_item_1);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(String.valueOf(adapter1.getItem(i)),String.valueOf(value1.getItem(i)));
            }
        });

        adapter2 = ArrayAdapter.createFromResource(this, R.array.menu_profile_arr2, android.R.layout.simple_list_item_1);
        value2 = ArrayAdapter.createFromResource(this, R.array.menu_profile_value_arr2, android.R.layout.simple_list_item_1);
        listView2.setAdapter(adapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(String.valueOf(adapter2.getItem(i)),String.valueOf(value2.getItem(i)));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showDialog(String title, String text) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_profile);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.findViewById(R.id.dialog_profile_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView titleView = dialog.findViewById(R.id.dialog_profile_title);
        TextView textView = dialog.findViewById(R.id.dialog_profile_text);
        titleView.setText(title);
        textView.setText(text);
        textView.setMovementMethod(new ScrollingMovementMethod());

        dialog.show();
    }
}
