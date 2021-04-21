package com.example.personalfinanceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Display;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.transactionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransactionListAdapter adapter = new TransactionListAdapter();
        recyclerView.setAdapter(adapter);

        controller = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(Controller.class);
        controller.getAllTransactions().observe(this, new Observer<List<TransactionsEntity>>() {
            @Override
            public void onChanged(@Nullable List<TransactionsEntity> transactionsEntities) {
                adapter.setTransactions(transactionsEntities);
            }
        });
    }
}