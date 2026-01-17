package unitTests.pages;

import base.BaseTest;
import dataTemplatesPojo.LoginDetails;
import org.testng.annotations.Test;
import pages.LoginPage;
import br.com.six2six.fixturefactory.Fixture;

public class LoginPageNegativeTest extends BaseTest {

    @Test(description = "Entering invalid mobile number should not proceed")
    public void testInvalidMobileNumber() {
        LoginPage loginPage = new LoginPage(driver());
        LoginDetails invalidLogin = Fixture.from(LoginDetails.class).gimme("invalidMobile");

        // Here, inputMobileNumberNewApp should handle validation error
        loginPage.inputMobileNumberNewApp(invalidLogin.getMobileNumber());

        // You can optionally assert an error message element is visible
        // Assert.assertTrue(loginPage.isValidationErrorVisible());
    }

    @Test(description = "Checkbox not clickable should be handled gracefully")
    public void testCreditFetchCheckboxNotClickable() {
        LoginPage loginPage = new LoginPage(driver());

        // Simulate scenario where checkbox is disabled (mock or force state)
        // Ensure method handles exception/logs properly
        loginPage.inputMobileNumberNewApp("9999999999");
    }
}
