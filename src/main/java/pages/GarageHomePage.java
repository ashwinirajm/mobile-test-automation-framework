package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;

public class GarageHomePage extends BasePage {

    @AndroidFindBy(accessibility = "addToGarageCTA")
    @iOSXCUITFindBy(accessibility = "addToGarageCTA")
    private WebElement addToGarageCTA;

    @AndroidFindBy(accessibility = "vehicleNumberInputField")
    @iOSXCUITFindBy(accessibility = "vehicleNumberInputField")
    private WebElement vehicleNumberInputField;

    @AndroidFindBy(accessibility = "proceedCTA")
    @iOSXCUITFindBy(accessibility = "proceedCTA")
    private WebElement proceedCTA;

    @AndroidFindBy(accessibility = "addedVehicleNumber")
    @iOSXCUITFindBy(accessibility = "addedVehicleNumber")
    private WebElement addedVehicleNumber;

    public GarageHomePage(AppiumDriver driver) {
        super(driver);
    }

    /** Clicks Add to Garage CTA with logging and screenshot */
    public void addToGarage() {
        waitUntilClickable(addToGarageCTA);
        addToGarageCTA.click();
        LoggerUtils.info("Clicked Add to Garage button");
        LoggerUtils.attachScreenshot(driver(), "AddToGarage_Clicked");
    }

    /** Inputs vehicle number with logging and screenshot */
    public void inputVehicleNumber(String vehicleNumber) {
        waitUntilVisible(vehicleNumberInputField);
        vehicleNumberInputField.sendKeys(vehicleNumber);
        LoggerUtils.info("Entered vehicle number: " + vehicleNumber);
        LoggerUtils.attachScreenshot(driver(), "Vehicle_Number_Entered");

        waitUntilClickable(proceedCTA);
        proceedCTA.click();
        LoggerUtils.info("Clicked proceed after entering vehicle number");
        LoggerUtils.attachScreenshot(driver(), "Proceed_Clicked");
    }

    /** Validates that the vehicle was added successfully */
    public boolean validateAddedVehicle(String vehicleNumber) {
        waitUntilVisible(addedVehicleNumber);
        String addedVehicle = addedVehicleNumber.getText();
        boolean isSame = addedVehicle.equals(vehicleNumber);

        LoggerUtils.info(isSame
            ? "Vehicle added successfully: " + vehicleNumber
            : "Vehicle addition failed. Expected: " + vehicleNumber + ", Found: " + addedVehicle);
        LoggerUtils.attachScreenshot(driver(), "Validate_Vehicle");

        return isSame;
    }
}
