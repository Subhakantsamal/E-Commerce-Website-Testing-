package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.CartPage;
import com.awesomeqa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test(description = "TC_CHECKOUT_001 - Empty checkout redirects to cart")
    public void emptyCheckoutRedirectsToCart() {
        driver.get(ConfigReader.get("baseUrl") + "index.php?route=checkout/checkout");
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isLoaded());
        Assert.assertTrue(cart.emptyMessageVisible());
    }
}
