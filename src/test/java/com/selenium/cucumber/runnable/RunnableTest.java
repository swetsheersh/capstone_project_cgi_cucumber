package com.selenium.cucumber.runnable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",
glue= "StepDefinitions",
dryRun = false,
monochrome=true,
plugin= {"pretty","html:target/HtmlReports.html"})

public class RunnableTest {

    @BeforeClass
    public static void setup() {
        // Open the browser and perform any necessary setup
        // You can initialize your WebDriver here
    }

    @AfterClass
    public static void teardown() {
        // Close the browser and perform cleanup
        // You can close the WebDriver here
    }
}
