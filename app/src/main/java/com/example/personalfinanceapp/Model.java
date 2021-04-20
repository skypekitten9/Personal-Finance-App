package com.example.personalfinanceapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.personalfinanceapp.data.TransactionsDao;
import com.example.personalfinanceapp.data.TransactionsDatabase;
import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class Model {
    private TransactionsDao transactionsDao;
    private LiveData<List<TransactionsEntity>> allTransactions;

    public Model(Application application) {
        TransactionsDatabase database = TransactionsDatabase.getInstance(application);
        transactionsDao = database.transactionsDao();
        allTransactions = transactionsDao.getAllTransactions();
    }

    public void insert(TransactionsEntity transaction) {
        transactionsDao.insert(transaction);
    }

    public void update(TransactionsEntity transaction) {
        transactionsDao.update(transaction);
    }

    public void delete(TransactionsEntity transaction) {
        transactionsDao.delete(transaction);
    }

    public void deleteAll() {
        transactionsDao.deleteAllTransactions();
    }

    public LiveData<List<TransactionsEntity>> getAllTransactions() {
        return allTransactions;
    }
}
