package com.awesomeqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends BasePage {
    private final By heading = By.cssSelector("#content h2");
    private final By productLinks = By.cssSelector("#content .product-thumb h4 a");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String heading() {
        return text(heading);
    }

    public List<String> products() {
        return driver.findElements(productLinks).stream().map(WebElement::getText).map(String::trim).toList();
    }
}
