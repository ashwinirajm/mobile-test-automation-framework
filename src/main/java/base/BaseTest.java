package base;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.utils.LoggerUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * BaseTest
 *
 * Responsibilities:
 * - Load fixture templates once per suite
 * - Create and quit Appium driver per test
 * - Capture logs and screenshots on failures for reporting
 */
public class BaseTest {

    protected String platformName;

    /**
     * Load all fixture templates once before test execution starts
     */
    @BeforeSuite(alwaysRun = true)
    public void loadFixtures() {
        FixtureLoader.loadTemplates();
        LoggerUtils.info("Fixture templates loaded successfully.");
    }

    /**
     * Capture platform name from TestNG XML
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"platformName"})
    public void setPlatform(@Optional("ANDROID") String platformName) {
        this.platformName = platformName;
        LoggerUtils.info("Platform set to: " + this.platformName);
    }

    /**
     * Create fresh driver before every test
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        try {
            AppiumDriver driver = DriverFactory.createDriver(platformName);
            DriverManager.setDriver(driver);
            LoggerUtils.info("Appium driver initialized successfully.");
        } catch (Exception e) {
            LoggerUtils.error("Driver initialization failed: " + e.getMessage());
            throw new RuntimeException("Driver initialization failed", e);
        }
    }

    /**
     * Quit driver after every test and capture screenshot on failure
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        AppiumDriver driver = DriverManager.getDriver();

        // Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            LoggerUtils.attachScreenshot(driver, result.getName() + "_FAILED");
            LoggerUtils.error("Test FAILED: " + result.getName() +
                    " | Exception: " + result.getThrowable());
        }

        if (driver != null) {
            DriverManager.quitDriver();
            LoggerUtils.info("Driver quit successfully for test: " + result.getName());
        }
    }

    /**
     * Helper to access driver in tests
     */
    protected AppiumDriver driver() {
        return DriverManager.getDriver();
    }
}
