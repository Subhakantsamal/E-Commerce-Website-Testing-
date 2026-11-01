package com.awesomeqa.tests;

import com.awesomeqa.base.BaseTest;
import com.awesomeqa.pages.LoginPage;
import com.awesomeqa.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {

    @Test(description = "TC_REG_001 - Verify registration page opens")
    public void registerPageOpens() {
        RegisterPage page = home().openRegister();
        Assert.assertTrue(page.isLoaded());
        Assert.assertTrue(page.mandatoryFieldsAreVisible());
    }

    @Test(description = "TC_REG_017 - Verify required registration validations")
    public void registerRequiresMandatoryFields() {
        RegisterPage page = home().openRegister().submitEmptyForm();
        Assert.assertTrue(page.validationMessageCount() > 0, "Expected validation messages for an empty registration form.");
    }

    @Test(description = "TC_LOGIN_001 - Verify login page opens")
    public void loginPageOpens() {
        Assert.assertTrue(home().openLogin().isLoaded());
    }

    @Test(description = "TC_LOGIN_004 - Verify invalid login warning")
    public void invalidLoginShowsWarning() {
        LoginPage page = home().openLogin()
                .loginExpectingFailure("invalid.user@example.com", "WrongPassword123!");
        Assert.assertTrue(page.warningText().toLowerCase().contains("warning"), "Expected invalid login warning.");
    }
}
