package com.example.reward_demo.repository;

import com.example.reward_demo.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    void addTransaction(Transaction t);

    List<Transaction> findTransactionsByCustomerId(UUID id);

}
