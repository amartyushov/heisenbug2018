package io.mart.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.mart.util.KeyValueHolder;

import java.util.List;

public class UserSteps extends AbstractSteps {

    @Given("^create user with params$")
    public void createUserWithParams(List<KeyValueHolder> params) {
        model.User user = userProvider.buildUser(new model.User(), params);
        resultHolder.storeResult(userExecutor.createUser(user));
    }

    @Then("^user has name \"([^\"]*)\"$")
    public void userHasName(String name) {
        userChecker.userHasName((model.User) resultHolder.getResult(), name);
    }
}
