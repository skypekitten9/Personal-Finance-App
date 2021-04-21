package com.example.personalfinanceapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.personalfinanceapp.data.TransactionsDao;
import com.example.personalfinanceapp.data.TransactionsDatabase;
import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.List;

public class Repository {
    private TransactionsDao transactionsDao;
    private LiveData<List<TransactionsEntity>> allTransactions;

    public Repository(Application application) {
        TransactionsDatabase database = TransactionsDatabase.getInstance(application);
        transactionsDao = database.transactionsDao();
        allTransactions = transactionsDao.getAllTransactions();
    }

    public void insert(TransactionsEntity transaction) {
        new InsertTransactionsAsyncTask(transactionsDao).execute(transaction);
    }

    public void update(TransactionsEntity transaction) {
        new UpdateTransactionsAsyncTask(transactionsDao).execute(transaction);
    }

    public void delete(TransactionsEntity transaction) {
        new DeleteTransactionsAsyncTask(transactionsDao).execute(transaction);
    }

    public void deleteAll() {
        new DeleteAllTransactionssAsyncTask(transactionsDao).execute();
    }

    public LiveData<List<TransactionsEntity>> getAllTransactions() {
        return allTransactions;
    }


    //Async classes
    private static class InsertTransactionsAsyncTask extends AsyncTask<TransactionsEntity, Void, Void> {
        private TransactionsDao transactionsDao;
        private InsertTransactionsAsyncTask(TransactionsDao transactionsDao) {
            this.transactionsDao = transactionsDao;
        }
        @Override
        protected Void doInBackground(TransactionsEntity... transactions) {
            transactionsDao.insert(transactions[0]);
            return null;
        }
    }
    private static class UpdateTransactionsAsyncTask extends AsyncTask<TransactionsEntity, Void, Void> {
        private TransactionsDao transactionsDao;
        private UpdateTransactionsAsyncTask(TransactionsDao transactionsDao) {
            this.transactionsDao = transactionsDao;
        }
        @Override
        protected Void doInBackground(TransactionsEntity... transactions) {
            transactionsDao.update(transactions[0]);
            return null;
        }
    }
    private static class DeleteTransactionsAsyncTask extends AsyncTask<TransactionsEntity, Void, Void> {
        private TransactionsDao transactionsDao;
        private DeleteTransactionsAsyncTask(TransactionsDao transactionsDao) {
            this.transactionsDao = transactionsDao;
        }
        @Override
        protected Void doInBackground(TransactionsEntity... transactions) {
            transactionsDao.delete(transactions[0]);
            return null;
        }
    }
    private static class DeleteAllTransactionssAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionsDao transactionsDao;
        private DeleteAllTransactionssAsyncTask(TransactionsDao transactionsDao) {
            this.transactionsDao = transactionsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            transactionsDao.deleteAllTransactions();
            return null;
        }
    }
}
