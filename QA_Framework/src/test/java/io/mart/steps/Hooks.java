package io.mart.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks extends AbstractSteps{

    @Before("@deleteAll")
    public void deleteAll(){
        log.info("Executing @deleteAll hook");
        userExecutor.deleteUsers();
    }

    @After("@data_changed")
    public void afterSuite() {
        log.info("Test has changed a data => same hook will be executed again next time");
        hookExecutor.updateHookFlagValue("data was changed during test");
    }
}
