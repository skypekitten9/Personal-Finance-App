package com.example.personalfinanceapp;

public class Controller {
    public enum TransactionCategory
    {
        Food,
        Transportation,
        Household,
        Entertainment,
        Other
    }

    private String userName, password, firstName, surName;
    private static Controller instance;

    public Controller()
    {

    }

    public void InitializeController()
    {
        if(instance == null) instance = new Controller();
    }

    public Controller Instance()
    {
        if(instance == null) return null;
        return  instance;
    }
}
