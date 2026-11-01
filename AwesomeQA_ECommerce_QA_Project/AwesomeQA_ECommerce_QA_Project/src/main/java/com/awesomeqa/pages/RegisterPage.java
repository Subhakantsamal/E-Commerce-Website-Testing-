package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private final By heading = By.cssSelector("#content h1");
    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirm = By.id("input-confirm");
    private final By continueButton = By.cssSelector("input[type='submit'][value='Continue']");
    private final By validationMessages = By.cssSelector(".text-danger, .alert-danger");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return text(heading).equals("Register Account");
    }

    public boolean mandatoryFieldsAreVisible() {
        return visible(firstName).isDisplayed()
                && visible(lastName).isDisplayed()
                && visible(email).isDisplayed()
                && visible(telephone).isDisplayed()
                && visible(password).isDisplayed()
                && visible(confirm).isDisplayed();
    }

    public RegisterPage submitEmptyForm() {
        click(continueButton);
        return this;
    }

    public int validationMessageCount() {
        return driver.findElements(validationMessages).size();
    }
}
