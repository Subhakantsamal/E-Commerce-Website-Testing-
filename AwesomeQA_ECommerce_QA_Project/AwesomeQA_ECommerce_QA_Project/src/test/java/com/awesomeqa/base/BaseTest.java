package com.awesomeqa.base;

import com.awesomeqa.listeners.TestListener;
import com.awesomeqa.pages.CartPage;
import com.awesomeqa.pages.HomePage;
import com.awesomeqa.pages.ProductPage;
import com.awesomeqa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");

        driver = switch (browser) {
            case "edge" -> createEdge(headless);
            case "chrome" -> createChrome(headless);
            default -> throw new IllegalArgumentException("Supported browsers: chrome, edge. Received: " + browser);
        };

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        if (!headless) {
            driver.manage().window().maximize();
        }
        driver.get(ConfigReader.get("baseUrl"));
    }

    private WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerLeakDetection");
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return new ChromeDriver(options);
    }

    private WebDriver createEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return new EdgeDriver(options);
    }

    protected HomePage home() {
        return new HomePage(driver);
    }

    protected ProductPage openProduct(int productId) {
        driver.get(ConfigReader.get("baseUrl") + "index.php?route=product/product&product_id=" + productId);
        return new ProductPage(driver);
    }

    protected CartPage openCart() {
        driver.get(ConfigReader.get("baseUrl") + "index.php?route=checkout/cart");
        return new CartPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
