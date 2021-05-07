package com.example.personalfinanceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalfinanceapp.data.TransactionsEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragmentList;
    private Overview overview;
    private Income income;
    private Expenditures expenditures;

    private TextView financeNameView, totalExpenditureView, revenueView, expenditureValueView, revenueValueView;

    private TransactionsViewModel transactionsViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize controller
        transactionsViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TransactionsViewModel.class);
        Controller.InitializeController(this);
        Controller.Instance().Begin();


        //Setup new transaction button
        FloatingActionButton floatingActionButton = findViewById(R.id.newTransactionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTransactionForm.class);
                startActivity(intent);
            }
        });

        //Setup filter button
        FloatingActionButton filterActionButton = findViewById(R.id.filterbutton);
        filterActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilterTransactions.class);
                startActivity(intent);
            }
        });


        //Setup pager adapter
        overview = new Overview();
        expenditures = new Expenditures();
        income = new Income();

        fragmentList = new ArrayList<>();
        fragmentList.add(overview);
        fragmentList.add(expenditures);
        fragmentList.add(income);

        viewPager = findViewById(R.id.fragmentPager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList);

        viewPager.setAdapter(pagerAdapter);

        //Get textviews
        financeNameView = findViewById(R.id.financeNameView);
        totalExpenditureView = findViewById(R.id.totalExpenditureView);
        expenditureValueView = findViewById(R.id.expenditureValueView);
        revenueView = findViewById(R.id.revenueView);
        revenueValueView = findViewById(R.id.revenueValueView);

        //Set title
        SetFinanceInfo();

        //Set list
        //Set live data to list
        Controller.Instance().GetTransactionsViewModel().getAllTransactions().observe(this, new Observer<List<TransactionsEntity>>() {
            @Override
            public void onChanged(@Nullable List<TransactionsEntity> transactionsEntities) {
                SetFinanceInfo();
            }
        });
    }

    public void CreateAccountForm()
    {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void AddTransaction(String title, String date, int amount, Controller.TransactionCategory category, boolean income)
    {
        TransactionsEntity transaction = new TransactionsEntity(date, title, category.ordinal(), amount, income);
        transactionsViewModel.insert(transaction);
    }
    public void ToastString(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    public void SetFinanceInfo()
    {
        financeNameView.setText(Controller.Instance().firstName + " " + Controller.Instance().surName + " Financials");

        LiveData<List<TransactionsEntity>> transactionsEntities = Controller.Instance().GetTransactionsViewModel().getAllTransactions();
        List<TransactionsEntity> transactions = new ArrayList<TransactionsEntity>();
        transactions = transactionsEntities.getValue();
        if(transactions == null) return;

        int income = 0;
        int expenditure = 0;
        for (TransactionsEntity t:transactions) {
            String[] date = t.getDate().split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            if(!Controller.Instance().IsDateInFilter(year, month, day)) continue;

            //Sets income if in filter
            if(t.isIncome()) {
                income += t.getAmount();
            }
            else{
                expenditure += t.getAmount();
            }
        }

        expenditureValueView.setText(Integer.toString(expenditure));
        revenueValueView.setText(Integer.toString(income - expenditure));

        if((income - expenditure) < 0) revenueValueView.setTextColor(getResources().getColor(R.color.red, getTheme()));
        else revenueValueView.setTextColor(getResources().getColor(R.color.green, getTheme()));

    }

    public void DeleteTransaction(TransactionsEntity transaction)
    {
        transactionsViewModel.delete(transaction);
    }

    public void Refresh(){
        AddTransaction("temp", "0-0-0", 0, Controller.TransactionCategory.Other, false);
        TransactionsEntity transacionTemp = new TransactionsEntity("0-0-0", "temp", Controller.TransactionCategory.Other.ordinal(), 0, false);
        DeleteTransaction(transacionTemp);
    }

    public void Restart()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public TransactionsViewModel GetTransactionsViewModel()
    {
        return transactionsViewModel;
    }
}