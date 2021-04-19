package com.example.personalfinanceapp;

public class Controller {
    private static Controller instance;

    Controller()
    {

    }

    public static Controller Instance()
    {
        if (instance == null) instance = new Controller();
        return instance;

    }
}
