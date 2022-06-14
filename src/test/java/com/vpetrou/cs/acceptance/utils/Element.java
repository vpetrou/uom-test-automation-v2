package com.vpetrou.cs.acceptance.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element extends BaseTest {

    WebDriver driver;

    public Element(WebDriver driver) {
        this.driver = driver;
    }

    public void input(WebElement field, String value) {
        if (value != null && !value.isEmpty()) {
            field.clear();
            field.sendKeys(value);
        }
    }

}
