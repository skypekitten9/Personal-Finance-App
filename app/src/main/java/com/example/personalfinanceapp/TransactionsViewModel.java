package com.example.personalfinanceapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class TransactionsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<TransactionsEntity>> allTransactions, incomes, expenditures;

    public TransactionsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTransactions = repository.getAllTransactions();
    }

    public void insert(TransactionsEntity transaction) {
        repository.insert(transaction);
    }

    public void update(TransactionsEntity transaction) {
        repository.update(transaction);
    }

    public void delete(TransactionsEntity transaction) {
        repository.delete(transaction);
    }

    public void deleteAllTransactions()
    {
        repository.deleteAll();
    }

    public LiveData<List<TransactionsEntity>> getAllTransactions()
    {
        return allTransactions;
    }
}
