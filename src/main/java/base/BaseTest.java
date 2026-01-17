package base;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        // driver init (already in your framework)
        FixtureLoader.loadTemplates();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            AppiumDriver driver = DriverFactory.createDriver();
            DriverManager.setDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /**
     * Helper to access driver in tests without calling DriverManager directly.
     */
    protected AppiumDriver driver() {
        return DriverManager.getDriver();
    }
}
