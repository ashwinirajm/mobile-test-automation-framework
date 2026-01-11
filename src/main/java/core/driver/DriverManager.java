package core.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
        // prevent instantiation
    }

    public static void setDriver(AppiumDriver driver) {
        DRIVER.set(driver);
    }

    public static AppiumDriver getDriver() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Did you forget to call setDriver()?");
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        AppiumDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}

