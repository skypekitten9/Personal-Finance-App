package com.example.personalfinanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new ViewModelProvider(this).get(Controller.class);
        controller.getAllTransactions().observe(this, new Observer<List<TransactionsEntity>>() {
            @Override
            public void onChanged(List<TransactionsEntity> transactionsEntities) {
                //update data
                Toast.makeText(MainActivity.this, "changed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}