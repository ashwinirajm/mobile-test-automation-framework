package actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileActions {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public MobileActions(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /* -------------------- VISIBILITY + CLICK -------------------- */

    public void isElementVisibleThenClick(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.click();
    }

    /* -------------------- SCROLL -------------------- */

    public void scrollTillElementVisible(By locator) {

        int maxScrolls = 5;
        int scrollCount = 0;

        while (scrollCount < maxScrolls) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) {
            }
            scrollDown();
            scrollCount++;
        }

        throw new RuntimeException(
                "Element not found after scrolling: " + locator
        );
    }

    private void scrollDown() {

        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

        int startX = width / 2;
        int startY = (int) (height * 0.7);
        int endY = (int) (height * 0.3);

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    /* -------------------- DEEP LINK -------------------- */

    public void openDeeplink(String deepLinkUrl) {
        driver.get(deepLinkUrl);
    }
}

