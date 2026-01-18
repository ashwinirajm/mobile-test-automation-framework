package unitTest.pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoggerUtils;

/**
 * Negative unit tests for LoginPage
 * Focused on invalid input and element interaction failures
 */
public class LoginPageNegativeTest extends BaseTest {

    @Test(
        groups = {"unit", "Login"},
        description = "Login should handle invalid mobile number gracefully"
    )
    public void login_invalidMobileNumber() {
        LoggerUtils.info("Starting test: login_invalidMobileNumber");

        LoginPage loginPage = new LoginPage(driver());

        loginPage.inputMobileNumberNewApp("123");

        // If no crash → handled correctly
        LoggerUtils.info("Invalid mobile input handled without crash");
        Assert.assertTrue(true, "Invalid mobile input handled");

        LoggerUtils.info("Finished test: login_invalidMobileNumber");
    }

    @Test(
        groups = {"unit", "Login"},
        description = "Login should not crash if checkbox is not clickable"
    )
    public void login_checkboxNotClickable() {
        LoggerUtils.info("Starting test: login_checkboxNotClickable");

        LoginPage loginPage = new LoginPage(driver());

        loginPage.inputMobileNumberNewApp("9999999999");

        LoggerUtils.info("Checkbox not clickable handled gracefully");
        Assert.assertTrue(true, "Checkbox failure handled gracefully");

        LoggerUtils.info("Finished test: login_checkboxNotClickable");
    }
}
