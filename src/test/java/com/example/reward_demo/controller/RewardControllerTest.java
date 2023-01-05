package com.example.reward_demo.controller;

import com.example.reward_demo.model.RewardsList;
import com.example.reward_demo.model.RewardsPerMonth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RewardControllerTest {

    @LocalServerPort
    int randomServerPort;
    private RestTemplate restTemplate;
    private String url1;
    private String url2;
    private String url3;
    private String uuid;
    private RewardsList list;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        uuid = "0a5c7d62-c39e-478a-aeab-31f9fde7652d";
        url1 = "http://localhost:" + randomServerPort + "/api/reward/user/" + uuid + "/month/1";
        url2 = "http://localhost:" + randomServerPort + "/api/reward/user/" + uuid + "/month/2";
        url3 = "http://localhost:" + randomServerPort + "/api/reward/user/" + uuid;
        list = new RewardsList();
        list.add(new RewardsPerMonth(340, 1));
        list.add(new RewardsPerMonth(0, 2));
        list.add(new RewardsPerMonth(150, 3));
        for (int month = 4; month <= 12; month ++) {
            list.add(new RewardsPerMonth(0, month));
        }
    }

    @Test
    void getRewardPerMonth() throws Exception {
        ResponseEntity entity1 = restTemplate.getForEntity(url1, Integer.class);
        ResponseEntity entity2 = restTemplate.getForEntity(url2, Integer.class);

        assertEquals(OK, entity1.getStatusCode());
        assertEquals(340, entity1.getBody());
        assertEquals(OK, entity2.getStatusCode());
        assertEquals(0, entity2.getBody());
    }


    @Test
    void getTotalReward() throws Exception {
        ResponseEntity responseEntity = restTemplate.getForEntity(url3, RewardsList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        System.out.println(list.equals(responseEntity.getBody()));
        assertEquals(list, responseEntity.getBody());
    }

}