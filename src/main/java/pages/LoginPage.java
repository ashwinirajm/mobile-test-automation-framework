package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "mobileInput")
    @iOSXCUITFindBy(accessibility = "mobileInput")
    private WebElement mobileInput;

    @AndroidFindBy(accessibility = "creditFetchCheckBox")
    @iOSXCUITFindBy(accessibility = "creditFetchCheckBox")
    private WebElement creditFetchCheckBox;

    @AndroidFindBy(accessibility = "proceedCTA")
    @iOSXCUITFindBy(accessibility = "proceedCTA")
    private WebElement proceedCTA;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Inputs mobile number, clicks credit fetch, then proceeds.
     * Automatically logs and attaches screenshots.
     */
    public void inputMobileNumberNewApp(String mobileNum) {
        waitUntilVisible(mobileInput);
        mobileInput.sendKeys(mobileNum);
        LoggerUtils.info("Entered mobile number: " + mobileNum);
        LoggerUtils.attachScreenshot(driver(), "Mobile_Number_Entered");

        waitUntilClickable(creditFetchCheckBox);
        creditFetchCheckBox.click();
        LoggerUtils.info("Clicked on credit fetch checkbox");
        LoggerUtils.attachScreenshot(driver(), "CreditFetch_Clicked");

        waitUntilClickable(proceedCTA);
        proceedCTA.click();
        LoggerUtils.info("Clicked on proceed button");
        LoggerUtils.attachScreenshot(driver(), "Proceed_Clicked");
    }
}
