package com.example.personalfinanceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personalfinanceapp.data.TransactionsEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TransactionsViewModel transactionsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize controller
        Controller.InitializeController(this);
        Controller.Instance().Begin();

        //Setup list and adapter
        RecyclerView recyclerView = findViewById(R.id.transactionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransactionListAdapter adapter = new TransactionListAdapter();
        recyclerView.setAdapter(adapter);

        //Set live data to list
        transactionsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TransactionsViewModel.class);
        transactionsViewModel.getAllTransactions().observe(this, new Observer<List<TransactionsEntity>>() {
            @Override
            public void onChanged(@Nullable List<TransactionsEntity> transactionsEntities) {
                adapter.setTransactions(transactionsEntities);
            }
        });

        //Setup new transaction button
        FloatingActionButton floatingActionButton = findViewById(R.id.newTransactionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTransactionForm.class);
                startActivity(intent);
            }
        });

    }

    public void CreateAccountForm()
    {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void AddTransaction(String title, String date, int amount, Controller.TransactionCategory category, boolean income)
    {
        TransactionsEntity transaction = new TransactionsEntity(date, title, category.ordinal(), amount, income);
        transactionsViewModel.insert(transaction);
        Toast.makeText(this, "Transaction added!", Toast.LENGTH_SHORT).show();
    }
}