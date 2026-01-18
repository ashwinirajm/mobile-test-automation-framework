package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;

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
            LoggerUtils.info("Clicked on Garage CTA");
            LoggerUtils.attachScreenshot(driver, "Home_ClickGarageCTA");
        }
        return new GarageHomePage(driver);
    }
}
