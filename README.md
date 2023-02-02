# WebAPI Charter Assessment

  Two REST API endpoints:
  - `GET /api/reward/user/{id}/month/{month}`: get reward per month.
  - `GET /api/reward/user/{id}`: get total rewards, return an JSON array to frontend `[(reward1, month1), (reward2, month2)...]`.
  
  Structure:
  - Model(POJO) - `RewardList, RewardsPerMonth, Transaction`. Classes that are used to wrap in Json.
  - Controller - `RewardController`.
  - Service - `RewardService`. Could change to other repository easily with @Qualifier name change.
  - Repository - `MockTransactonRepository, TransactonRepository` as interface. 

  Testing framework: 
  - Junit, Mockito, RestTemplate.
