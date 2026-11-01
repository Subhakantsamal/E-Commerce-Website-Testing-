package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {
    private final By heading = By.cssSelector("#content h1");
    private final By productLinks = By.cssSelector("#content .product-thumb h4 a");
    private final By noResults = By.xpath("//p[contains(.,'There is no product that matches the search criteria')]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return text(heading).contains("Search");
    }

    public List<String> productNames() {
        return driver.findElements(productLinks).stream().map(WebElement::getText).map(String::trim).toList();
    }

    public boolean containsProduct(String productName) {
        return productNames().stream().anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public boolean noResultsMessageVisible() {
        return visible(noResults).isDisplayed();
    }

    public ProductPage openProduct(String productName) {
        click(By.xpath("//div[@id='content']//h4/a[normalize-space()='" + productName + "']"));
        return new ProductPage(driver);
    }
}
