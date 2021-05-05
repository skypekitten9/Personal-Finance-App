package com.example.personalfinanceapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface TransactionsDao {
    @Insert
    void insert(TransactionsEntity transaction);

    @Update
    void update(TransactionsEntity transaction);

    @Delete
    void delete(TransactionsEntity transaction);

    @Query("DELETE FROM transactions")
    void deleteAllTransactions();

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<TransactionsEntity>> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE income = 1 ORDER BY date DESC")
    LiveData<List<TransactionsEntity>> getAllIncome();

    @Query("SELECT * FROM transactions WHERE income = 0 ORDER BY date DESC")
    LiveData<List<TransactionsEntity>> getAllExpenditure();
}
