package com.vpetrou.cs.acceptance.pages;

import com.vpetrou.cs.acceptance.utils.BaseTest;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact extends BaseTest {

    WebDriver driver;

    @FindBy(name = "name")
    WebElement name;
    @FindBy(name = "email")
    WebElement email;
    @FindBy(name = "phone")
    WebElement phone;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;

    public Contact(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addNewContact(DataTable dataTable) {
        System.out.println("The user adds a new contact.");
        element.input(name, getValue(dataTable, "Name"));
        element.input(email, getValue(dataTable, "Email"));
        element.input(phone, getValue(dataTable, "Phone"));
        saveButton.click();
        waitForLoad();
    }

}
