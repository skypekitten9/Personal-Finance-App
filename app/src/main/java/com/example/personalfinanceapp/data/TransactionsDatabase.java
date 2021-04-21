package com.example.personalfinanceapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.LocalDate;


@Database(entities = {TransactionsEntity.class}, version = 2)
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
            new PopulateDbAsyncTask(instance).execute();
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
                    "Victor",
                    "2020-02-02",
                    "McDonalds",
                    300,
                    false
            ));
            transactionsDao.insert(new TransactionsEntity(
                    "Roy",
                    "2019-09-09",
                    "Coop Mårtenstorget",
                    100,
                    false
            ));
            return null;
        }
    }
}
