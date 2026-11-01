package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By email = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginButton = By.cssSelector("input[type='submit'][value='Login']");
    private final By warning = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("route=account/login")
                && driver.getPageSource().contains("Returning Customer");
    }

    public String passwordFieldType() {
        return visible(password).getAttribute("type");
    }

    public LoginPage loginExpectingFailure(String emailValue, String passwordValue) {
        type(email, emailValue);
        type(password, passwordValue);
        click(loginButton);
        visible(warning);
        return this;
    }

    public String warningText() {
        return text(warning);
    }
}
