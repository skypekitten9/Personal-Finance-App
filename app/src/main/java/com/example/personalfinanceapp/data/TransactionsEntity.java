package com.example.personalfinanceapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;


@Entity(tableName = "transactions")
public class TransactionsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;
    private Long date;
    private String title;
    private float amount;
    private boolean income;

    public TransactionsEntity(String userName, Long date, String title, float amount, boolean income) {
        this.userName = userName;
        this.date = date;
        this.title = title;
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

    public Long getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isIncome() {
        return income;
    }
}
