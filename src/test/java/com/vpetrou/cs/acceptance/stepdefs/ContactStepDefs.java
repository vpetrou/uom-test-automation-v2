package com.vpetrou.cs.acceptance.stepdefs;

import com.vpetrou.cs.acceptance.utils.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactStepDefs extends BaseTest {

    @When("a system requests the addition of a new contact")
    public void aSystemRequestsTheAdditionOfANewContact(DataTable testData) {
        contactAPI.addNewContact(testData);
    }

    @Then("the system responds and a new Contact {string} is created successfully")
    public void theSystemRespondsAndANewContactIsCreatedSuccessfully(String filterValue) {
        contactAPI.searchContactById(filterValue);
    }

}
