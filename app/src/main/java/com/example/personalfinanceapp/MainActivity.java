package com.example.personalfinanceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.personalfinanceapp.data.TransactionsEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize controller
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


        //Setup pager adapter
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Overview());
        fragmentList.add(new Expenditures());
        fragmentList.add(new Income());

        viewPager = findViewById(R.id.fragmentPager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList);

        viewPager.setAdapter(pagerAdapter);
    }

    public void CreateAccountForm()
    {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void AddTransaction(String title, String date, int amount, Controller.TransactionCategory category, boolean income)
    {
        TransactionsEntity transaction = new TransactionsEntity(date, title, category.ordinal(), amount, income);
        //transactionsViewModel.insert(transaction);
        Toast.makeText(this, "Transaction added!", Toast.LENGTH_SHORT).show();
    }

    public void DeleteTransaction(TransactionsEntity transaction)
    {
        //transactionsViewModel.delete(transaction);
    }
}