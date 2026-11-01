package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private final By heading = By.cssSelector("#content h1");
    private final By quantity = By.id("input-quantity");
    private final By addToCart = By.id("button-cart");
    private final By successAlert = By.cssSelector(".alert-success");
    private final By availability = By.xpath("//div[@id='content']//li[contains(.,'Availability:')]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String productName() {
        return text(heading);
    }

    public String availabilityText() {
        return text(availability);
    }

    public ProductPage setQuantity(int qty) {
        type(quantity, String.valueOf(qty));
        return this;
    }

    public ProductPage addToCart() {
        click(addToCart);
        visible(successAlert);
        return this;
    }

    public String successMessage() {
        return text(successAlert);
    }
}
