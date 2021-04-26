package com.example.personalfinanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddTransactionForm extends AppCompatActivity {

    EditText editTextTitle, editTextAmount;
    DatePicker datePicker;
    Switch incomeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_form);

        editTextTitle = findViewById(R.id.editTextTitle);
        datePicker = findViewById(R.id.datePicker);
        editTextAmount = findViewById(R.id.editTextAmount);
        incomeSwitch = findViewById(R.id.incomeSwitch);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("New Transaction");
    }

    private void saveTransaction()
    {
        String title = editTextTitle.getText().toString();
        String date = String.valueOf(datePicker.getYear()) + "-" + String.valueOf(datePicker.getMonth()) + "-" + String.valueOf(datePicker.getDayOfMonth());
        int amount = Integer.parseInt(editTextAmount.getText().toString());

        if(title.trim().isEmpty() || title.trim().isEmpty()){
            Toast.makeText(this, "One ore more fields are INVALID", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Controller.Instance().AddTransaction(title, date, amount, Controller.TransactionCategory.Other, incomeSwitch.isChecked());
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_transaction_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.saveTransaction:
                saveTransaction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}