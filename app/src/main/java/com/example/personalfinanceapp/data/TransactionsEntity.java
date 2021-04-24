package com.example.personalfinanceapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;



@Entity(tableName = "transactions")
public class TransactionsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private String title;
    private int category;
    private int amount;
    private boolean income;

    public TransactionsEntity(String date, String title, int category, int amount, boolean income) {
        this.date = date;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.income = income;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isIncome() {
        return income;
    }
}
