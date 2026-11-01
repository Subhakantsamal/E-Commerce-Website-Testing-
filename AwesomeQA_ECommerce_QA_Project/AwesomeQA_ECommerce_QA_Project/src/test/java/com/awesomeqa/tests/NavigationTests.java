package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.CategoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    @Test(description = "TC_NAV_001 - Navigate to Desktops category")
    public void openDesktopsCategory() {
        CategoryPage page = home().openDesktops();
        Assert.assertEquals(page.heading(), "Desktops");
        Assert.assertTrue(page.products().contains("MacBook"));
    }

    @Test(description = "TC_NAV_006 - Navigate to Phones & PDAs category")
    public void openPhonesCategory() {
        CategoryPage page = home().openPhonesAndPdas();
        Assert.assertEquals(page.heading(), "Phones & PDAs");
        Assert.assertTrue(page.products().contains("iPhone"));
    }
}
