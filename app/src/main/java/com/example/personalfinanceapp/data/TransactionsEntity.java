package com.example.personalfinanceapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;



@Entity(tableName = "transactions")
public class TransactionsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;
    private String date;
    private String title;
    private int category;
    private float amount;
    private boolean income;

    public TransactionsEntity(String userName, String date, String title, int category, float amount, boolean income) {
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getCategory() {
        return category;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isIncome() {
        return income;
    }
}
