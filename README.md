# WebAPI Design for assessment

  Two REST API endpoints:
  - `GET /api/reward/user/{id}/month/{month}`: get reward per month.
  - `GET /api/reward/user/{id}`: get total rewards, return an JSON array to frontend `[(reward1, month1), (reward2, month2)...]`.
