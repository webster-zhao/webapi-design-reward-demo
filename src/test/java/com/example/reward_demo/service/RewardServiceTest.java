package com.example.reward_demo.service;

import com.example.reward_demo.model.RewardsList;
import com.example.reward_demo.model.RewardsPerMonth;
import com.example.reward_demo.model.Transaction;
import com.example.reward_demo.repository.MockTransactionRepository;
import com.example.reward_demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RewardServiceTest {
    private RewardService service;
    private UUID id;
    private List<Transaction> transactions;
    private RewardsList rewardsList;

    @BeforeEach
    void setup() {
        TransactionRepository repository = Mockito.mock(MockTransactionRepository.class);
        id = UUID.fromString("0a5c7d62-c39e-478a-aeab-31f9fde7652d");
        Transaction t1 = new Transaction(LocalDate.of(2023,1,1), 200, id);
        Transaction t2 = new Transaction(LocalDate.of(2023,2,2), 50, id);
        Transaction t3 = new Transaction(LocalDate.of(2023,3,3), 150, id);
        Transaction t4 = new Transaction(LocalDate.of(2023,1,4), 120, id);
        transactions = new ArrayList<>(Arrays.asList(t1,t2,t3,t4));
        rewardsList = new RewardsList();
        rewardsList.add(new RewardsPerMonth(340, 1));
        rewardsList.add(new RewardsPerMonth(0, 2));
        rewardsList.add(new RewardsPerMonth(150, 3));
        for (int month = 4; month <= 12; month ++) {
            rewardsList.add(new RewardsPerMonth(0, month));
        }
        when(repository.findTransactionsByCustomerId(id)).thenReturn(transactions);
        this.service = new RewardService(repository);
    }


    @Test
    void getRewardPerMonth() {
        assertEquals(340, service.getRewardPerMonth(id, 1));
        assertEquals(0, service.getRewardPerMonth(id, 2));
        assertEquals(150, service.getRewardPerMonth(id, 3));
    }

    @Test
    void getTotalReward() {
        assertEquals(rewardsList, service.getTotalReward(id));
    }

}