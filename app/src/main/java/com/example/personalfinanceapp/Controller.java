package com.example.personalfinanceapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class Controller extends AndroidViewModel {
    private Model model;
    private LiveData<List<TransactionsEntity>> allTransactions;

    public Controller(@NonNull Application application) {
        super(application);
        model = new Model(application);
        allTransactions = model.getAllTransactions();
    }

    public void insert(TransactionsEntity transaction) {
        model.insert(transaction);
    }

    public void update(TransactionsEntity transaction) {
        model.update(transaction);
    }

    public void delete(TransactionsEntity transaction) {
        model.delete(transaction);
    }

    public void deleteAllTransactions()
    {
        model.deleteAll();
    }

    public LiveData<List<TransactionsEntity>> getAllTransactions()
    {
        return allTransactions;
    }
}
