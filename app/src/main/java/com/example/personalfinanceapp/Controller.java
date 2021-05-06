package com.example.personalfinanceapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.Hashtable;

public class Controller {

    public enum TransactionCategory
    {
        Food,
        Leisure,
        Travel,
        Accommodation,
        Salery,
        Other
    }

    private String firstName, surName;
    private static Controller instance;
    private MainActivity ma;
    private Hashtable<String, Boolean> expandedHashTable = new Hashtable<String, Boolean>();
    private String dateFilterFrom = "1000-1-1";
    private String dateFilterTo = "3000-3-3";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FIRST_NAME = "firstName";
    public static final String SURNAME = "surName";
    public static final String ACCOUNT_EXISTS = "accountExists";

    public Controller(MainActivity ma)
    {
        this.ma = ma;
    }

    public static void InitializeController(MainActivity ma)
    {
        if(instance == null) instance = new Controller(ma);
    }

    public static Controller Instance()
    {
        if(instance == null) return null;
        return  instance;
    }

    public void Begin()
    {
        if(DoesAccountExist())
        {
            LoadUser();
        }
        else{
            ma.CreateAccountForm();
        }
    }

    public void FilterDates(String fromDate, String toDate)
    {
        dateFilterFrom = fromDate;
        dateFilterTo = toDate;
        ma.Refresh();
    }

    public boolean IsDateInFilter(int year, int month, int day)
    {
        if(year < GetYearFrom() || year > GetYearTo()) return false;
        if(year == GetYearFrom())
        {
            if(month < GetMonthFrom()) return false;
            if(month == GetMonthFrom())
            {
                if(day < GetDayFrom()) return false;
            }
        }

        if(year == GetYearTo())
        {
            if(month > GetMonthTo()) return false;
            if(month == GetMonthTo())
            {
                if(day > GetDayTo()) return false;
            }
        }
        return true;
    }

    public int GetYearFrom()
    {
        String[] date = dateFilterFrom.split("-");
        return Integer.parseInt(date[0]);
    }

    public int GetMonthFrom()
    {
        String[] date = dateFilterFrom.split("-");
        return Integer.parseInt(date[1]);
    }

    public int GetDayFrom()
    {
        String[] date = dateFilterFrom.split("-");
        return Integer.parseInt(date[2]);
    }

    public int GetYearTo()
    {
        String[] date = dateFilterTo.split("-");
        return Integer.parseInt(date[0]);
    }

    public int GetMonthTo()
    {
        String[] date = dateFilterTo.split("-");
        return Integer.parseInt(date[1]);
    }

    public int GetDayTo()
    {
        String[] date = dateFilterTo.split("-");
        return Integer.parseInt(date[2]);
    }

    public void AddTransaction(String title, String date, int amount, Controller.TransactionCategory category, boolean income)
    {
        ma.AddTransaction(title, date, amount, category, income);
        ma.ToastString("Transaction added!");
    }

    public void DeleteTransaction(TransactionsEntity transaction)
    {
        ma.DeleteTransaction(transaction);
    }

    public boolean DoesAccountExist()
    {
        SharedPreferences sharedPreferences = ma.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(ACCOUNT_EXISTS, false);
    }

    public boolean CreateAccount(String firstName, String surname)
    {
        if (IsNameValid(firstName) && IsNameValid(surname))
        {
            Toast.makeText(ma, "Account created!", Toast.LENGTH_SHORT).show();
            SaveUser(firstName, surName);
            LoadUser();
            return true;
        }
        else
        {
            Toast.makeText(ma, "One ore more names are INVALID", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void SaveUser(String firstName, String surName)
    {
        SharedPreferences sharedPreferences = ma.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FIRST_NAME, firstName);
        editor.putString(SURNAME, surName);
        editor.putBoolean(ACCOUNT_EXISTS, true);
        editor.apply();
    }

    public void LoadUser()
    {
        SharedPreferences sharedPreferences = ma.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        firstName = sharedPreferences.getString(FIRST_NAME, "");
        surName = sharedPreferences.getString(SURNAME, "");
    }

    public boolean IsNameValid(String name)
    {
        return !(name.trim().isEmpty() || name == null || name.length() > 20);
    }

    public void SetExpandable(String transactionID, boolean value)
    {
        expandedHashTable.put(transactionID, value);
    }

    public boolean GetExpandable(String transactionID)
    {
        if(expandedHashTable.get(transactionID) == null) return false;
        else return expandedHashTable.get(transactionID);
    }

    public TransactionsViewModel GetTransactionsViewModel()
    {
        return ma.GetTransactionsViewModel();
    }
}
