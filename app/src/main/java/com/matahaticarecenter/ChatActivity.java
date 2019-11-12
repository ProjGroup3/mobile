package com.matahaticarecenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        MyLoginTask task = new MyLoginTask();
        task.execute("");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class MyLoginTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("admin", "admin")
                    .setHost("10.0.2.2")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setServiceName("localhost")
                    .setPort(5222)
                    .setDebuggerEnabled(true) // to view what's happening in detail
                    .build();

            AbstractXMPPConnection conn1 = new XMPPTCPConnection(config);
            try {
                conn1.connect();
                if (conn1.isConnected()) {
                    Log.w("EJABBERD", "conn done");
                }
                conn1.login();

                if (conn1.isAuthenticated()) {
                    Log.w("EJABBERD", "Auth done");
                }
            } catch (Exception e) {
                Log.w("EJABBERD", e.toString());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

}
