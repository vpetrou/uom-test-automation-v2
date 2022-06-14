package com.vpetrou.cs.acceptance;

import com.vpetrou.cs.acceptance.api.ContactAPI;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber/cucumber.json", "pretty",
                "html:target/cucumber/cucumber-html-report.html", "junit:target/cucumber/cucumber-junit-report.xml"},
        features = {"src/test/resources/features/"},
        tags = "@API and not @Manual",
        glue = {"com.vpetrou.cs.acceptance.stepdefs"})
public class CucumberRunner {

    @BeforeClass
    public static void beforeAll() {
        ContactAPI.cleanup();
    }

    @AfterClass
    public static void afterAll() {
        ContactAPI.cleanup();
    }

}
