package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    private final By featuredHeading = By.xpath("//h3[normalize-space()='Featured']");
    private final By featuredProducts = By.cssSelector("#content .product-thumb h4 a");
    private final By currencyButton = By.cssSelector("#form-currency .dropdown-toggle");
    private final By currencyOptions = By.cssSelector("#form-currency .dropdown-menu button");
    private final By cartTotal = By.id("cart-total");
    private final By registerLink = By.linkText("Register");
    private final By loginLink = By.linkText("Login");
    private final By searchInput = By.name("search");
    private final By searchButton = By.cssSelector("#search button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.getTitle().contains("Your Store") && visible(featuredHeading).isDisplayed();
    }

    public List<String> featuredProductNames() {
        return visibles(featuredProducts).stream().map(WebElement::getText).map(String::trim).toList();
    }

    public List<String> openCurrencyAndGetOptions() {
        click(currencyButton);
        return visibles(currencyOptions).stream().map(WebElement::getText).map(String::trim).toList();
    }

    public String cartSummary() {
        return text(cartTotal);
    }

    public RegisterPage openRegister() {
        click(registerLink);
        return new RegisterPage(driver);
    }

    public LoginPage openLogin() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public SearchPage search(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
        return new SearchPage(driver);
    }

    public CategoryPage openDesktops() {
        click(By.linkText("Desktops"));
        return new CategoryPage(driver);
    }

    public CategoryPage openPhonesAndPdas() {
        click(By.linkText("Phones & PDAs"));
        return new CategoryPage(driver);
    }
}
