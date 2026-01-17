package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
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
        waitUntilVisible(mobileInput));
        driver.findElement(mobileInput).sendKeys(mobileNum);
        LoggerUtils.info("Entered mobile number: " + mobileNum);

        waitUntilClickable(driver.findElement(creditFetchCheckBox));
        driver.findElement(creditFetchCheckBox).click();
        LoggerUtils.info("Clicked on credit fetch checkbox");

        waitUntilClickable(driver.findElement(proceedBtn));
        driver.findElement(proceedBtn).click();
        LoggerUtils.info("Clicked on proceed button");
    }
}
