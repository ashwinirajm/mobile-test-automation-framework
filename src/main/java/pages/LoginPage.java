package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;

public class LoginPage extends BasePage {

    // -------------------- ELEMENT LOCATORS --------------------
    private final By mobileInput = MobileBy.AccessibilityId("mobileInput");
    private final By creditFetchCheckBox = MobileBy.AccessibilityId("creditFetchCheckBox");
    private final By proceedBtn = MobileBy.AccessibilityId("proceedBtn");

    @AndroidFindBy(className = "android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    private WebElement mobileInput;

    
    // -------------------- CONSTRUCTOR --------------------
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    // -------------------- BUSINESS METHODS --------------------

    /**
     * Enters the mobile number and clicks through login flow
     * @param mobileNum Mobile number to login with
     */
    public void inputMobileNumberNewApp(String mobileNum) {
        // Wait until mobile input is visible and enter the number
        waitUntilVisible(mobileInput));
        driver.findElement(mobileInput).sendKeys(mobileNum);
        LoggerUtils.info("Entered mobile number: " + mobileNum);

        // Wait and click credit fetch checkbox
        waitUntilClickable(driver.findElement(creditFetchCheckBox));
        driver.findElement(creditFetchCheckBox).click();
        LoggerUtils.info("Clicked on credit fetch checkbox");

        // Wait and click proceed button
        waitUntilClickable(driver.findElement(proceedBtn));
        driver.findElement(proceedBtn).click();
        LoggerUtils.info("Clicked on proceed button");
    }

    /**
     * Optional: You can add more login-related flows here
     */
}
