package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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

    public void inputMobileNumberNewApp(String mobileNum) {
        waitUntilVisible(mobileInput);
        mobileInput.sendKeys(mobileNum);
        LoggerUtils.info("Entered mobile number: " + mobileNum);
        LoggerUtils.attachScreenshot(driver, "Login_MobileNumberEntered");

        waitUntilClickable(creditFetchCheckBox);
        creditFetchCheckBox.click();
        LoggerUtils.info("Clicked on credit fetch checkbox");
        LoggerUtils.attachScreenshot(driver, "Login_CheckedCreditFetch");

        waitUntilClickable(proceedCTA);
        proceedCTA.click();
        LoggerUtils.info("Clicked on proceed button");
        LoggerUtils.attachScreenshot(driver, "Login_ProceedClicked");
    }
}
