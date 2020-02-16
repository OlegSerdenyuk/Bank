package ado;

import entity.Transaction;

import java.util.List;

public interface TransactionAdo {
    List<Transaction> getTransactionsByCard(String card);
}
