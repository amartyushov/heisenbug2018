@deleteAll
Feature: create user
  mvn clean test -Dcucumber.options="--tags "

  Scenario: check user is not null
    Given create user with params
      | key  | value |
      | name | Alex  |
    Then user has name "Alexs"

  Scenario: check user is not null 1
    Given create user with params
      | key  | value |
      | name | Alex  |
    Then user has name "Alex"