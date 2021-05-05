package com.example.personalfinanceapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class TransactionsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<TransactionsEntity>> allTransactions, allIncome, allExpenditure;

    public TransactionsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTransactions = repository.getAllTransactions();
        allIncome = repository.getAllIncome();
        allExpenditure = repository.getAllExpenditure();
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

    public LiveData<List<TransactionsEntity>> getAllIncome()
    {
        return allIncome;
    }

    public LiveData<List<TransactionsEntity>> getAllExpenditure()
    {
        return allExpenditure;
    }
}
