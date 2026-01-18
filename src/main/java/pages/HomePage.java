package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerUtils;

public class HomePage extends BasePage {

    @AndroidFindBy(
        uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"garage\")"
    )
    @iOSXCUITFindBy(
        xpath = "//XCUIElementTypeStaticText[@name='garage']"
    )
    private WebElement garageCTA;

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
            new AppiumFieldDecorator(driver),
            this
        );
    }

    /**
     * Navigate to Garage Home page.
     * Automatically logs action and attaches screenshot.
     */
    public GarageHomePage navigateToGarageHome() {
        if (isElementVisible(garageCTA)) {
            garageCTA.click();
            LoggerUtils.info("Clicked on Garage CTA from Home Page");
            LoggerUtils.attachScreenshot(driver(), "Clicked_GarageCTA");
        } else {
            LoggerUtils.warn("Garage CTA not visible on Home Page");
            LoggerUtils.attachScreenshot(driver(), "GarageCTA_NotVisible");
        }
        return new GarageHomePage(driver);
    }
}
