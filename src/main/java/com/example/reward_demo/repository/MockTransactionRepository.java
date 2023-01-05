package com.example.reward_demo.repository;

import com.example.reward_demo.exception.TransactionNotFoundException;
import com.example.reward_demo.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository("mockDB")
public class MockTransactionRepository implements TransactionRepository {

    private final HashMap<UUID, List<Transaction>> transactions = new HashMap<>();


    public MockTransactionRepository() {
        UUID u1 = UUID.fromString("0a5c7d62-c39e-478a-aeab-31f9fde7652d");
        UUID u2 = UUID.fromString("d9b3e46e-6b64-4d83-9c8b-c33160a6e8b9");
        Transaction t1 = new Transaction(LocalDate.of(2023,1,1), 200, u1);
        Transaction t2 = new Transaction(LocalDate.of(2023,2,2), 50, u1);
        Transaction t3 = new Transaction(LocalDate.of(2023,3,3), 150, u1);
        Transaction t4 = new Transaction(LocalDate.of(2023,1,4), 120, u1);
        Transaction t5 = new Transaction(LocalDate.of(2023,2,5), 160, u2);
        Transaction t6 = new Transaction(LocalDate.of(2023,3,6), 180, u2);
        Transaction t7 = new Transaction(LocalDate.of(2023,2,7), 20, u2);
        Transaction t8 = new Transaction(LocalDate.of(2023,3,8), 80, u2);
        List<Transaction> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        list1.add(t3);
        list1.add(t4);
        transactions.put( u1, list1);
        List<Transaction> list2 = new ArrayList<>();
        list2.add(t5);
        list2.add(t6);
        list2.add(t7);
        list2.add(t8);
        transactions.put( u2, list2);
    }

    @Override
    public void addTransaction(Transaction t) {
        transactions.putIfAbsent(t.getCustomerId(), new ArrayList<>());
        List<Transaction> list = transactions.get(t.getCustomerId());
        list.add(t);
    }

    @Override
    public List<Transaction> findTransactionsByCustomerId(UUID id) {
        if (transactions.containsKey(id)) {
            return transactions.get(id);
        }
        throw new TransactionNotFoundException();
    }

}
