@only_once
Feature: example of data_changed usage
  mvn clean test -Dcucumber.options="src/test/resources/cucumber/3_data_changed.feature"

  Scenario: 1. first scenario
    When do nothing

  @data_changed
  Scenario: 2. second scenario
    Data is changed in this scenario => prepare data again next time
    When do nothing

  Scenario: 3. third scenario
    Before hook @only_once is executed here
    When do nothing