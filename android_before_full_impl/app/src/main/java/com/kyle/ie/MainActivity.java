package com.kyle.ie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kyle.ie.auth.view.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private static MainActivity _instance;

    public String EDGE_IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final EditText ipField = (EditText) findViewById(R.id.hack_ip);
        Button btn = (Button) findViewById(R.id.hack_submit);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EDGE_IP = "http://" + ipField.getText().toString() + ":8200" + "/api";
                toLoginView();
            }
        });
    }


    public void toLoginView() {
        //TODO: Check if token exists, if so get usercontext. If request fails delete token and go to login. Else go to relevant dashboard.
        Intent loginView = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginView);
    }

    public static MainActivity getInstance() {
        return _instance;
    }
}
