package com.example.reward_demo.controller;

import com.example.reward_demo.exception.TransactionNotFoundException;
import com.example.reward_demo.model.RewardsList;
import com.example.reward_demo.model.RewardsPerMonth;

import com.example.reward_demo.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reward")
public class RewardController {
    private final RewardService service;

    @Autowired
    public RewardController(RewardService service) {
        this.service = service;
    }

    @GetMapping(path = "/user/{id}/month/{month}")
    public ResponseEntity<Integer> getRewardPerMonth( @PathVariable("id") UUID id, @PathVariable("month") int month) {
            return new ResponseEntity<>(service.getRewardPerMonth(id, month), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<RewardsList> getTotalReward(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getTotalReward(id), HttpStatus.OK);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity exceptionHandler(Exception e) {
        return new ResponseEntity<>("Customer transaction not found.", HttpStatus.NOT_FOUND);
    }
}
