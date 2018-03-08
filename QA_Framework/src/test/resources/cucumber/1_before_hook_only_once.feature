@only_once
Feature: example of hook executed only once
  mvn clean test -Dcucumber.options="src/test/resources/cucumber/1_before_hook_only_once.feature"

  Scenario: 1. first scenario
    When do nothing

  Scenario: 2. second scenario
    Before hook will not be executed before this scenario
    When do nothing

  Scenario: 3. third scenario
    Before hook will not be executed before this scenario
    When do nothing