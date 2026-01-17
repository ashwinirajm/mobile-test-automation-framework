package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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

    public GarageHomePage navigateToGarageHome() {
        if (isElementVisible(garageCTA)) {
            garageCTA.click();
        }
        return new GarageHomePage(driver);
    }
}
