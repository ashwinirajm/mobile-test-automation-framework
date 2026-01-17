package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"garage\")")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='garage']")
    private WebElement garageCTA;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public GarageHomePage navigateToGarageHome() {
    if (isElementVisible(garageCTA)) {
        garageCTA.click();
    }
    return new GarageHomePage(driver);
  }  
}

