package com.example.reward_demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RewardsList {
    private List<RewardsPerMonth> list;

    public RewardsList() {
        this.list = new ArrayList<>();
    }

    @JsonCreator
    public RewardsList( @JsonProperty("list") List<RewardsPerMonth> list) {
        this.list = list;
    }

    public List<RewardsPerMonth> getList() {
        return list;
    }

    public void add(RewardsPerMonth r) {
        this.list.add(r);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardsList)) return false;
        RewardsList that = (RewardsList) o;
        return getList().equals(that.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getList());
    }
}
