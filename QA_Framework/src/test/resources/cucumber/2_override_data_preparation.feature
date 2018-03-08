@only_once
Feature: example for overriding of data preparation
  mvn clean test -Dcucumber.options="src/test/resources/cucumber/2_override_data_preparation.feature"

  Scenario: 1. first scenario
    When do nothing

  Scenario: 2. second scenario
    Before hook will not be executed before this scenario
    When do nothing

  @override_setup
  Scenario: 3. third scenario
    Before hook @override_setup is executed here
    When do nothing

  Scenario: 4. fourth scenario
    Before hook @only_once is executed here
    When do nothing

  Scenario: 5. fifth scenario
    Before hook will not be executed before this scenario
    When do nothing