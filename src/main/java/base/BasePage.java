package base;

import core.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;

/**
 * BasePage
 *
 * Purpose:
 * - Holds driver reference
 * - Provides common utilities to pages
 */
public abstract class BasePage {

    protected AppiumDriver driver;
    protected WaitUtils wait;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }
}
