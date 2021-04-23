package com.example.personalfinanceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TransactionsViewModel transactionsViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.transactionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransactionListAdapter adapter = new TransactionListAdapter();
        recyclerView.setAdapter(adapter);

        transactionsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TransactionsViewModel.class);
        transactionsViewModel.getAllTransactions().observe(this, new Observer<List<TransactionsEntity>>() {
            @Override
            public void onChanged(@Nullable List<TransactionsEntity> transactionsEntities) {
                adapter.setTransactions(transactionsEntities);
            }
        });

        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }
}