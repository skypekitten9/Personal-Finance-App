package com.example.personalfinanceapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.personalfinanceapp.Controller;


@Database(entities = {TransactionsEntity.class}, version = 5)
public abstract class TransactionsDatabase extends RoomDatabase {
    private static TransactionsDatabase instance;
    public abstract TransactionsDao transactionsDao();

    public static synchronized TransactionsDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), TransactionsDatabase.class, "transactions")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionsDao transactionsDao;
        private PopulateDbAsyncTask(TransactionsDatabase db) {
            transactionsDao = db.transactionsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            transactionsDao.insert(new TransactionsEntity(
                    "2020-02-02",
                    "McDonalds",
                    Controller.TransactionCategory.Food.ordinal(),
                    300,
                    false
            ));
            transactionsDao.insert(new TransactionsEntity(
                    "2019-09-09",
                    "Coop Mårtenstorget",
                    Controller.TransactionCategory.Food.ordinal(),
                    100,
                    false
            ));
            transactionsDao.insert(new TransactionsEntity(
                    "2019-09-25",
                    "Malmo Stad",
                    Controller.TransactionCategory.Salery.ordinal(),
                    2000,
                    true
            ));
            return null;
        }
    }
}
