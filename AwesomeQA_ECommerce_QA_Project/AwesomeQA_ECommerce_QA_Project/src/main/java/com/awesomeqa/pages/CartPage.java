package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private final By heading = By.cssSelector("#content h1");
    private final By productNames = By.cssSelector("#content .table-responsive tbody td.text-left a");
    private final By quantityInput = By.cssSelector("#content .table-responsive input[name^='quantity']");
    private final By updateButton = By.xpath("//div[@id='content']//button[@type='submit' and (contains(@data-original-title,'Update') or @title='Update')]");
    private final By removeButton = By.xpath("//div[@id='content']//button[@type='button' and (contains(@data-original-title,'Remove') or @title='Remove')]");
    private final By emptyMessage = By.xpath("//div[@id='content']//p[contains(.,'Your shopping cart is empty')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return text(heading).equals("Shopping Cart");
    }

    public List<String> productNames() {
        return driver.findElements(productNames).stream().map(WebElement::getText).map(String::trim).toList();
    }

    public boolean containsProduct(String name) {
        return productNames().stream().anyMatch(p -> p.equalsIgnoreCase(name));
    }

    public CartPage updateFirstQuantity(int qty) {
        WebElement input = visible(quantityInput);
        input.clear();
        input.sendKeys(String.valueOf(qty));
        click(updateButton);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(quantityInput)));
        return this;
    }

    public String firstQuantity() {
        return visible(quantityInput).getAttribute("value");
    }

    public CartPage removeFirstProduct() {
        click(removeButton);
        wait.until(driver -> driver.findElements(productNames).isEmpty() || !driver.findElements(emptyMessage).isEmpty());
        return this;
    }

    public boolean emptyMessageVisible() {
        return !driver.findElements(emptyMessage).isEmpty();
    }
}
