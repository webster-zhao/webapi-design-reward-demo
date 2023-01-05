package com.example.reward_demo.service;

import com.example.reward_demo.model.RewardsList;
import com.example.reward_demo.model.RewardsPerMonth;
import com.example.reward_demo.model.Transaction;
import com.example.reward_demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RewardService {
    private final TransactionRepository repository;

    @Autowired
    public RewardService(@Qualifier("mockDB") TransactionRepository repository) {
        this.repository = repository;
    }

    public int getRewardPerMonth(UUID id, int month) {
        List<Transaction> transactions = repository.findTransactionsByCustomerId(id);
        int reward = transactions
                .stream().filter(t ->  t.getDate().getMonthValue() == month)
                .map(Transaction::getAmount)
                .map(RewardService::calculateRewardByAmount)
                .reduce(0, Integer::sum);
        return reward;
    }

    public RewardsList getTotalReward(UUID id) {
        RewardsList list = new RewardsList();
        for (int month = 1 ; month <= 12 ; month++)
            list.add(new RewardsPerMonth(getRewardPerMonth(id, month), month));
        return list;
    }

    private static int calculateRewardByAmount(int amount) {
        if (amount <= 50) {
            return 0;
        } else if (amount <= 100) {
            return amount - 50;
        }
        return (amount - 100) * 2 + 50;
    }
}
