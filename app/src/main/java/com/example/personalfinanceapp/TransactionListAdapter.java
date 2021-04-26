package com.example.personalfinanceapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        holder.transactionInView = transaction;
        holder.transactionID = String.valueOf(transaction.getId());
        holder.titleView.setText(transaction.getTitle());
        holder.dateView.setText(transaction.getDate());
        holder.position = position;

        if (transaction.isIncome()) holder.amountView.setText(String.valueOf(transaction.getAmount() * -1));
        else holder.amountView.setText(String.valueOf(transaction.getAmount()));

        if(Controller.Instance().GetExpandable(holder.transactionID)) holder.expandableLayout.setVisibility(View.VISIBLE);
        else holder.expandableLayout.setVisibility(View.GONE);
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
        String transactionID;
        TransactionsEntity transactionInView;
        int position;
        TextView titleView, amountView, dateView;
        Button removeButton, hideButton;
        boolean expanded;
        ConstraintLayout expandableLayout;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            amountView = itemView.findViewById(R.id.amountView);
            dateView = itemView.findViewById(R.id.dateView);
            hideButton = itemView.findViewById(R.id.hideButton);
            removeButton = itemView.findViewById(R.id.removeButton);
            expandableLayout = itemView.findViewById(R.id.expandlableLayout);
            expanded = false;

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Controller.Instance().DeleteTransaction(transactionInView);
                }
            });

            hideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Controller.Instance().SetExpandable(transactionID, false);
                    notifyDataSetChanged();
                }
            });

            titleView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Controller.Instance().SetExpandable(transactionID, true);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
