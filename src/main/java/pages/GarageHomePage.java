package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerUtils;

public class GarageHomePage extends BasePage {

    @AndroidFindBy(accessibility = "addToGarageCTA")
    @iOSXCUITFindBy(accessibility = "addToGarageCTA")
    private WebElement addToGarageCTA;

    @AndroidFindBy(accessibility = "vehicleNumberInput")
    @iOSXCUITFindBy(accessibility = "vehicleNumberInput")
    private WebElement vehicleNumberInputField;

    @AndroidFindBy(accessibility = "proceedCTA")
    @iOSXCUITFindBy(accessibility = "proceedCTA")
    private WebElement proceedCTA;

    @AndroidFindBy(accessibility = "addedVehicleNumber")
    @iOSXCUITFindBy(accessibility = "addedVehicleNumber")
    private WebElement addedVehicleNumber;

    public GarageHomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
            new AppiumFieldDecorator(driver),
            this
        );
    }

    /**
     * Clicks on Add to Garage CTA
     */
    public void addToGarage() {
        waitUntilClickable(addToGarageCTA);
        addToGarageCTA.click();
        LoggerUtils.info("Clicked on Add to Garage CTA");
    }

    /**
     * Inputs vehicle number and proceeds
     */
    public void inputVehicleNumber(String vehicleNumber) {
        waitUntilVisible(vehicleNumberInputField);
        vehicleNumberInputField.sendKeys(vehicleNumber);
        LoggerUtils.info("Entered vehicle number: " + vehicleNumber);

        waitUntilClickable(proceedCTA);
        proceedCTA.click();
        LoggerUtils.info("Clicked on Proceed CTA after entering vehicle number");
    }

    /**
     * Validates if the vehicle was added successfully
     */
    public boolean validateAddedVehicle(String expectedVehicleNumber) {

        if (isElementVisible(addedVehicleNumber)) {
            String actualVehicleNumber = addedVehicleNumber.getText();
            boolean isMatched = actualVehicleNumber.equals(expectedVehicleNumber);

            LoggerUtils.info(
                isMatched
                    ? "Vehicle successfully added to garage: " + actualVehicleNumber
                    : "Vehicle number mismatch. Expected: " + expectedVehicleNumber +
                      ", Found: " + actualVehicleNumber
            );

            return isMatched;
        }

        LoggerUtils.info("Added vehicle number is not visible on Garage Home");
        return false;
    }
}
