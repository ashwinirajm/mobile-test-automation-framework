package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;

public class GarageHomePage extends BasePage {

    @AndroidFindBy(accessibility = "addToGarageCTA")
    @iOSXCUITFindBy(accessibility = "addToGarageCTA")
    private WebElement addToGarageCTA;

    @AndroidFindBy(accessibility = "vehicleNumberInput")
    @iOSXCUITFindBy(accessibility = "vehicleNumberInput")
    private WebElement vehicleNumberInput;

    @AndroidFindBy(accessibility = "proceedCTA")
    @iOSXCUITFindBy(accessibility = "proceedCTA")
    private WebElement proceedCTA;

    @AndroidFindBy(accessibility = "addedVehicleNumber")
    @iOSXCUITFindBy(accessibility = "addedVehicleNumber")
    private WebElement addedVehicleNumber;

    public GarageHomePage(AppiumDriver driver) {
        super(driver);
    }

    public void addToGarage() {
        waitUntilClickable(addToGarageCTA);
        addToGarageCTA.click();
        LoggerUtils.info("Clicked Add to Garage button");
        LoggerUtils.attachScreenshot(driver, "Garage_ClickAddToGarage");
    }

    public void inputVehicleNumber(String vehicleNumber) {
        waitUntilVisible(vehicleNumberInput);
        vehicleNumberInput.sendKeys(vehicleNumber);
        LoggerUtils.info("Entered vehicle number: " + vehicleNumber);
        LoggerUtils.attachScreenshot(driver, "Garage_EnteredVehicleNumber");

        waitUntilClickable(proceedCTA);
        proceedCTA.click();
        LoggerUtils.info("Clicked proceed button after entering vehicle");
        LoggerUtils.attachScreenshot(driver, "Garage_ClickedProceed");
    }

    public boolean validateAddedVehicle(String vehicleNumber) {
        if (ifElementVisible(addedVehicleNumber, 5000)) {
            String addedVehicle = addedVehicleNumber.getText();
            boolean isSameVehicle = addedVehicle.equals(vehicleNumber);

            LoggerUtils.info(isSameVehicle
                ? "Vehicle successfully added to garage"
                : "Failed to add vehicle to garage");

            LoggerUtils.attachScreenshot(driver, "Garage_Validation_" + vehicleNumber);

            return isSameVehicle;
        } else {
            LoggerUtils.warn("Added vehicle element not visible");
            LoggerUtils.attachScreenshot(driver, "Garage_ValidationFailed_" + vehicleNumber);
            return false;
        }
    }
}
