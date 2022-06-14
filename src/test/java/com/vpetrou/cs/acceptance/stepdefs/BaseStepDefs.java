package com.vpetrou.cs.acceptance.stepdefs;

import com.vpetrou.cs.acceptance.utils.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BaseStepDefs extends BaseTest {

    @Before
    public void beforeTest() {
        setURL();
    }

    @Before("@UI")
    public void beforeUITest() {
        openBrowser();
    }

    @After("@UI")
    public void afterUITest() {
        closeBrowser();
    }

    @Given("the Demo Application is opened")
    public void the_Demo_Application_is_opened() {
        openApplication();
        contactList.verifyContactListPageOpens();
    }

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String pageName) {
        menu.navigateToPage(pageName);
    }

}
