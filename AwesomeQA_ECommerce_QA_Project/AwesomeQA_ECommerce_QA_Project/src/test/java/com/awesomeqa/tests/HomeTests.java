package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeTests extends BaseTest {

    @Test(description = "TC_HOME_001 - Verify home page loads")
    public void homePageLoads() {
        Assert.assertTrue(home().isLoaded(), "AwesomeQA home page did not load correctly.");
    }

    @Test(description = "TC_HOME_002 - Verify featured products")
    public void featuredProductsVisible() {
        List<String> products = home().featuredProductNames();
        Assert.assertTrue(products.size() >= 4, "Expected at least four featured products.");
        Assert.assertTrue(products.contains("MacBook"), "MacBook should be present in Featured products.");
    }

    @Test(description = "TC_HOME_006 - Verify currency options")
    public void currencyOptionsAreAvailable() {
        List<String> currencies = home().openCurrencyAndGetOptions();
        Assert.assertTrue(currencies.stream().anyMatch(x -> x.contains("Euro")));
        Assert.assertTrue(currencies.stream().anyMatch(x -> x.contains("Pound Sterling")));
        Assert.assertTrue(currencies.stream().anyMatch(x -> x.contains("US Dollar")));
    }

    @Test(description = "TC_HOME_009 - Verify new session cart is empty")
    public void newSessionCartIsEmpty() {
        String summary = home().cartSummary();
        Assert.assertTrue(summary.contains("0 item(s)"), "New browser session should start with an empty cart.");
    }
}
