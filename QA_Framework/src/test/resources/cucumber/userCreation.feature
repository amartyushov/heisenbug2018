@deleteAll
Feature: create user
  mvn clean test -Dcucumber.options="--tags @deleteAll"

  Scenario: check user is not null
    Given create user with params
      | key  | value |
      | name | Alex  |
    Then user has name "Alex"