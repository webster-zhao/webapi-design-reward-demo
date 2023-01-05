package com.example.reward_demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final UUID customerId;

    public Transaction(LocalDate date, int amount, UUID customerId) {
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public UUID getCustomerId() {
        return customerId;
    }
}
