package io.mart.steps;

import cucumber.api.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleHooks extends AbstractSteps{

    @Before("@only_once")
    public void only_once_hook(){
        hookExecutor.runBasedOnFlag("only_once", this::logic_of_only_once_hook);
    }

    private void logic_of_only_once_hook() {
        log.info("I am inside of hook @only_once doing data preparation");
    }

    @Before("@override_setup")
    public void override_hook(){
        hookExecutor.runBasedOnFlag("override_setup", this::logic_of_override_hook);
    }

    private void logic_of_override_hook() {
        log.info("I am inside of hook @override_setup preparing custom data");
    }

}
