package com.example.personalfinanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

public class FilterTransactions extends AppCompatActivity {
    private DatePicker dateFrom, dateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_transactions);

        dateFrom = findViewById(R.id.dateBefore);
        dateTo = findViewById(R.id.dateAfter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Filter between dates");
    }

    private void filterTransactions() {

        if(dateFrom.getYear() > dateTo.getYear())
        {
            Toast.makeText(this, "Picked dates are invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateFrom.getYear() == dateTo.getYear() && dateFrom.getMonth() > dateTo.getMonth())
        {
            Toast.makeText(this, "Picked dates are invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dateFrom.getYear() == dateTo.getYear() && dateFrom.getMonth() == dateTo.getMonth() && dateFrom.getDayOfMonth() > dateTo.getDayOfMonth())
        {
            Toast.makeText(this, "Picked dates are invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        String from = String.valueOf(dateFrom.getYear()) + "-" + String.valueOf(dateFrom.getMonth()) + "-" + String.valueOf(dateFrom.getDayOfMonth());
        String to = String.valueOf(dateTo.getYear()) + "-" + String.valueOf(dateTo.getMonth()) + "-" + String.valueOf(dateTo.getDayOfMonth());


        finish();
        Controller.Instance().FilterDates(from, to);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterDates:
                filterTransactions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}