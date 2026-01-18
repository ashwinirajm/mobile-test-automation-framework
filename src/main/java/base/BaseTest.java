package base;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected String platformName;

    /**
     * Load fixture templates once before test execution starts
     */
    @BeforeSuite(alwaysRun = true)
    public void loadFixtures() {
        FixtureLoader.loadTemplates();
    }

    /**
     * Capture platform name from TestNG XML
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"platformName"})
    public void setPlatform(@Optional("ANDROID") String platformName) {
        this.platformName = platformName;
    }

    /**
     * Create fresh driver before every test
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        try {
            AppiumDriver driver = DriverFactory.createDriver(platformName);
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
