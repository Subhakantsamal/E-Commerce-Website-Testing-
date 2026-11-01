# AwesomeQA E-Commerce — Manual & Automation Testing

Portfolio QA project for **https://awesomeqa.com/ui/**.

## Project Scope

- **120 manual test cases** across Home, Registration, Login, Search, Navigation, Product Details, Cart, Wishlist, Checkout, and Contact/Information flows.
- **Exactly 20 Selenium WebDriver regression tests** selected from the manual suite.
- Java 21 + Selenium WebDriver + TestNG + Maven.
- Page Object Model, explicit waits, assertions, configuration file, cross-browser setup, and screenshots on failure.

## Technology Stack

- Java 21
- Selenium WebDriver 4.52.0
- TestNG 7.12.0
- Maven
- Chrome / Edge

## Framework Structure

```text
src/main/java/com/awesomeqa/
├── pages/
│   ├── BasePage.java
│   ├── HomePage.java
│   ├── LoginPage.java
│   ├── RegisterPage.java
│   ├── SearchPage.java
│   ├── CategoryPage.java
│   ├── ProductPage.java
│   └── CartPage.java
└── utils/
    └── ConfigReader.java

src/test/java/com/awesomeqa/
├── base/BaseTest.java
├── listeners/TestListener.java
└── tests/
    ├── HomeTests.java
    ├── AccountTests.java
    ├── SearchTests.java
    ├── NavigationTests.java
    ├── ProductCartTests.java
    └── CheckoutTests.java
```

## Automated Tests

See `docs/AUTOMATION_COVERAGE.md` for the exact 20 Excel test cases mapped to TestNG methods.

## How to Run in Eclipse

1. Extract the project.
2. Eclipse → **File → Import → Maven → Existing Maven Projects**.
3. Select the project folder and finish the import.
4. Right-click project → **Maven → Update Project**.
5. Right-click `testng.xml` → **Run As → TestNG Suite**.

Or from a terminal with Maven installed:

```bash
mvn clean test
```

## Configuration

Edit `src/test/resources/config.properties`:

```properties
baseUrl=https://awesomeqa.com/ui/
browser=chrome
headless=false
timeoutSeconds=15
```

Supported browsers: `chrome`, `edge`.

## Test Execution Notes

- Each `@Test` method counts as one TestNG test; no DataProvider is used, so the suite total stays at **20**.
- Failure screenshots are written to the `screenshots/` folder.
- The manual Excel workbook starts with `Not Executed` status. Update it only after actual execution.
- The application is a public practice site; test data/content can change over time. If a locator or product catalog changes, update the Page Object rather than hard-coding fixes in test classes.

## Resume Line

**AwesomeQA E-Commerce – Manual & Automation Testing | Java, Selenium WebDriver, TestNG, Maven**  
Designed 120 manual test cases and developed a 20-test Selenium regression suite for search, navigation, account validation, product, cart, and checkout workflows using Page Object Model, explicit waits, assertions, and failure screenshots.
