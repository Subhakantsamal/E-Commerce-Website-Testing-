package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.ProductPage;
import com.awesomeqa.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {

    @Test(description = "TC_SEARCH_001 - Search for iPhone")
    public void searchExistingIPhone() {
        SearchPage results = home().search("iPhone");
        Assert.assertTrue(results.isLoaded());
        Assert.assertTrue(results.containsProduct("iPhone"));
    }

    @Test(description = "TC_SEARCH_002 - Search for MacBook")
    public void searchExistingMacBook() {
        Assert.assertTrue(home().search("MacBook").containsProduct("MacBook"));
    }

    @Test(description = "TC_SEARCH_004 - Search for unknown product")
    public void searchUnknownProduct() {
        SearchPage results = home().search("ProductThatDoesNotExist987654");
        Assert.assertTrue(results.noResultsMessageVisible());
    }

    @Test(description = "TC_SEARCH_010 - Open product from search results")
    public void openProductFromSearch() {
        ProductPage product = home().search("iPhone").openProduct("iPhone");
        Assert.assertEquals(product.productName(), "iPhone");
    }
}
