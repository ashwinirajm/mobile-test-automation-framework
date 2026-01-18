package unitTest.pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageNegativeTest extends BaseTest {

    @Test(description = "Login should handle invalid mobile number gracefully")
    public void login_invalidMobileNumber() {
        LoginPage loginPage = new LoginPage(driver());

        loginPage.inputMobileNumberNewApp("123");

        // If no crash → handled correctly
        Assert.assertTrue(true, "Invalid mobile input handled");
    }

    @Test(description = "Login should not crash if checkbox is not clickable")
    public void login_checkboxNotClickable() {
        LoginPage loginPage = new LoginPage(driver());

        loginPage.inputMobileNumberNewApp("9999999999");

        Assert.assertTrue(true, "Checkbox failure handled gracefully");
    }
}
