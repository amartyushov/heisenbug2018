package io.mart.util.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import io.mart.util.cucumber.CustomRunner;
import org.junit.runner.RunWith;

@RunWith(CustomRunner.class)
@CucumberOptions(
        glue = "io.mart.steps",
        features = "classpath:cucumber",
        tags = {"~@exclude", "~@failed", "~@not_impl", "~@mobile", "~@slow"},
        plugin = {"pretty", "html:target/site/cucumber-pretty","json:target/cucumber.json"},
        snippets = SnippetType.UNDERSCORE,
        junit = "--allow-started-ignored"
)
public class CucumberRunnerTest {
}
