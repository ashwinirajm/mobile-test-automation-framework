package base;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * BaseTest
 *
 * Responsibilities:
 * - Load fixture templates once
 * - Create and quit Appium driver per test
 */
public class BaseTest {

    /**
     * Load all fixture templates once before test execution starts
     */
    @BeforeSuite(alwaysRun = true)
    public void loadFixtures() {
        FixtureLoader.loadTemplates();
    }

    /**
     * Create fresh driver before every test
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        try {
            AppiumDriver driver = DriverFactory.createDriver();
            DriverManager.setDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    /**
     * Quit driver after every test
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /**
     * Helper to access driver in tests
     */
    protected AppiumDriver driver() {
        return DriverManager.getDriver();
    }
}
