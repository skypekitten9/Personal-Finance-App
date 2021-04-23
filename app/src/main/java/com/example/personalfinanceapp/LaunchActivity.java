package com.example.personalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {
    Button button;
    EditText firstNameText, surnameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        button = findViewById(R.id.createAccountButton);
        firstNameText = findViewById(R.id.firstNameLaunchView);
        surnameText = findViewById(R.id.surNameLaunchView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Controller.Instance().CreateAccount(firstNameText.getText().toString(), surnameText.getText().toString()))
                {
                    finish();
                }
            }
        });
    }
}