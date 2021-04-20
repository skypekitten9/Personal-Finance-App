package com.example.personalfinanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalfinanceapp.data.TransactionsEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {
    List<TransactionsEntity> transactions = new ArrayList<TransactionsEntity>();

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionsEntity transaction = transactions.get(position);
        holder.titleView.setText(transaction.getTitle());
        holder.amountView.setText(String.valueOf(transaction.getAmount()));
        holder.dateView.setText(transaction.getDate());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<TransactionsEntity> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        int id;
        TextView titleView, amountView, dateView;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            amountView = itemView.findViewById(R.id.amountView);
            dateView = itemView.findViewById(R.id.dateView);
            id = 0;
        }
    }
}
