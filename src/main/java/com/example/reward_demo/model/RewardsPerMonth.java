package com.example.reward_demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RewardsPerMonth {
    private int reward;
    private int month;

    @JsonCreator
    public RewardsPerMonth(@JsonProperty("reward") int reward, @JsonProperty("month") int month) {
        this.reward = reward;
        this.month = month;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardsPerMonth)) return false;
        RewardsPerMonth that = (RewardsPerMonth) o;
        return getReward() == that.getReward() && getMonth() == that.getMonth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReward(), getMonth());
    }
}
