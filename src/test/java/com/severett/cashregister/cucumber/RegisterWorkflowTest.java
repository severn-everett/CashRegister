package com.severett.cashregister.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:cucumber/registerWorkflow.feature",
        glue = "com.severett.cashregister.cucumber.steps"
)
public class RegisterWorkflowTest {   
}
