package io.mart.steps;

import io.mart.checker.UserChecker;
import io.mart.executors.UserCreationExecutor;
import io.mart.providers.UserProvider;
import io.mart.util.HookExecutor;
import io.mart.util.ResultHolder;
import io.mart.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractSteps {

    @Autowired
    public UserProvider userProvider;

    @Autowired
    public UserCreationExecutor userExecutor;

    @Autowired
    public UserChecker userChecker;

    @Autowired
    public ResultHolder resultHolder;

    @Autowired
    public ScenarioContext scenarioContext;

    @Autowired
    public HookExecutor hookExecutor;

}
