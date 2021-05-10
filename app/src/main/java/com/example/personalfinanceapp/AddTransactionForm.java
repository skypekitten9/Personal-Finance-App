package com.example.personalfinanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AddTransactionForm extends AppCompatActivity {

    EditText editTextTitle, editTextAmount;
    DatePicker datePicker;
    Spinner catagorySpinner;
    Switch incomeSwitch;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_form);

        //Find resources
        editTextTitle = findViewById(R.id.editTextTitle);
        datePicker = findViewById(R.id.datePicker);
        editTextAmount = findViewById(R.id.editTextAmount);
        incomeSwitch = findViewById(R.id.incomeSwitch);
        catagorySpinner = findViewById(R.id.categorySpinner);

        //Set menu
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("New Transaction");

        //Set spinner
        if(Controller.Instance().switchActivated)
        {
            //Set spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.incomeTypes, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            catagorySpinner.setAdapter(adapter);
        }
        else
        {
            //Set spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.expenditureTypes, R.layout.support_simple_spinner_dropdown_item);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            catagorySpinner.setAdapter(adapter);
        }

        //Setup switch
        incomeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.Instance().switchActivated = incomeSwitch.isChecked();
                if(Controller.Instance().switchActivated)
                {
                    //Set spinner
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.incomeTypes, R.layout.support_simple_spinner_dropdown_item);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    catagorySpinner.setAdapter(adapter);
                }
                else
                {
                    //Set spinner
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.expenditureTypes, R.layout.support_simple_spinner_dropdown_item);
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    catagorySpinner.setAdapter(adapter);
                }
            }
        });
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
            Controller.Instance().AddTransaction(title, date, amount, GetCategoryFromSpinner(), incomeSwitch.isChecked());
            finish();
        }
    }

    private Controller.TransactionCategory GetCategoryFromSpinner()
    {
        String selectedItem = catagorySpinner.getSelectedItem().toString();
        if(selectedItem.equals("Food")) return Controller.TransactionCategory.Food;
        else if(selectedItem.equals("Leisure")) return Controller.TransactionCategory.Leisure;
        else if(selectedItem.equals("Travel")) return Controller.TransactionCategory.Travel;
        else if(selectedItem.equals("Accommodation")) return Controller.TransactionCategory.Accommodation;
        else if(selectedItem.equals("Other")) return Controller.TransactionCategory.Other;
        else if(selectedItem.equals("Salery")) return Controller.TransactionCategory.Salery;


        return Controller.TransactionCategory.Other;
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