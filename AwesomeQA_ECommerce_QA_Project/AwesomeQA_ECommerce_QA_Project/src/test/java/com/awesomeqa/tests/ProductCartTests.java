package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.CartPage;
import com.awesomeqa.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCartTests extends BaseTest {

    @Test(description = "TC_PROD_001 - Verify MacBook product details")
    public void macBookProductDetailsVisible() {
        ProductPage page = openProduct(43);
        Assert.assertEquals(page.productName(), "MacBook");
        Assert.assertTrue(page.availabilityText().contains("Availability:"));
    }

    @Test(description = "TC_PROD_008 - Verify HTC Touch HD is in stock")
    public void htcAvailabilityIsInStock() {
        ProductPage page = openProduct(28);
        Assert.assertEquals(page.productName(), "HTC Touch HD");
        Assert.assertTrue(page.availabilityText().contains("In Stock"));
    }

    @Test(description = "TC_CART_001 - Add HTC Touch HD to cart")
    public void addHtcTouchHdToCart() {
        openProduct(28).addToCart();
        CartPage cart = openCart();
        Assert.assertTrue(cart.containsProduct("HTC Touch HD"));
    }

    @Test(description = "TC_CART_008 - Update cart quantity")
    public void updateCartQuantity() {
        openProduct(28).addToCart();
        CartPage cart = openCart().updateFirstQuantity(2);
        Assert.assertEquals(cart.firstQuantity(), "2");
    }

    @Test(description = "TC_CART_010 - Remove product from cart")
    public void removeProductFromCart() {
        openProduct(28).addToCart();
        CartPage cart = openCart().removeFirstProduct();
        Assert.assertTrue(cart.emptyMessageVisible() || cart.productNames().isEmpty());
    }
}
