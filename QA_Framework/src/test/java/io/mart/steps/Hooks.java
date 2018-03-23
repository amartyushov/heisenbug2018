package io.mart.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class Hooks extends AbstractSteps{


    private LocalDateTime scenarioStartTime;

    @Before("@deleteAll")
    public void deleteAll(){
        log.info("Executing @deleteAll hook");
        userExecutor.deleteUsers();
    }

    @Before(order = 1)
    public void savingContext(Scenario scenario){
        scenarioStartTime = LocalDateTime.now();
        log.info("Saving context of scenario");
        scenarioContext.init(scenario);
    }

    @After(order = 1)
    public void collectDevLogs(Scenario scenario) {
        if (scenario.isFailed()) {
            logCollector.recordExternalServicesLogs(
                    scenario,
                    scenarioStartTime,
                    scenarioContext.getTags());
        }
    }
}
