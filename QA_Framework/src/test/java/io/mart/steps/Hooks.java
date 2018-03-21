package io.mart.steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks extends AbstractSteps{

    @Before("@deleteAll")
    public void deleteAll(){
        log.info("Executing @deleteAll hook");
        userExecutor.deleteUsers();
    }

    @Before(order = 1)
    public void savingContext(Scenario scenario){
        log.info("Saving context of scenario");
        scenarioContext.init(scenario);
    }

    @Before(order = 2)
    public void checkingHealth(){
        log.info("Checking health of required services");
        healthChecker.checkMicroserviceStatuses(
                scenarioContext.getTags());
    }
}
