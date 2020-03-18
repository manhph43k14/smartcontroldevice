package com.example.xoaphm.smartdevicecontrol.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xoaphm.smartdevicecontrol.R;
import com.example.xoaphm.smartdevicecontrol.control.ControlActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView login;
    private Handler handler = new Handler();
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Tien hanh anh xa
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login);

        //Init progressbar
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);

        // Bat even
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "admin";
                String pass = "admin";
                if (email.getText().toString().equals(username) && password.getText().toString().equals(pass)) {
                    loadUI();
                } else {
                    Toast.makeText(getApplicationContext(), "Log in Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void loadUI() {
        progressBar.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                try {
                    while (count < 2) {
                        Thread.sleep(1000);
                        count++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.dismiss();
                        Toast.makeText(getApplicationContext(), "Log in Succesfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), ControlActivity.class));
                        finish();
                    }
                });
            }
        }).start();
    }
}
