package unitTest.pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GarageHomePage;
import utils.LoggerUtils;

/**
 * Negative unit tests for GarageHomePage
 * Focused on invalid vehicle numbers and element visibility
 */
public class GarageHomePageNegativeTest extends BaseTest {

    @Test(
        groups = {"unit", "Garage"},
        description = "Invalid vehicle number should not be added"
    )
    public void addInvalidVehicleNumber() {
        LoggerUtils.info("Starting test: addInvalidVehicleNumber");

        GarageHomePage garagePage = new GarageHomePage(driver());

        garagePage.addToGarage();
        garagePage.inputVehicleNumber("INVALID123");

        boolean isAdded = garagePage.validateAddedVehicle("INVALID123");

        LoggerUtils.info("Vehicle added validation result: " + isAdded);
        Assert.assertFalse(isAdded, "Invalid vehicle should not be added");

        LoggerUtils.info("Finished test: addInvalidVehicleNumber");
    }

    @Test(
        groups = {"unit", "Garage"},
        description = "Validation should fail if added vehicle element not visible"
    )
    public void validateVehicle_elementNotVisible() {
        LoggerUtils.info("Starting test: validateVehicle_elementNotVisible");

        GarageHomePage garagePage = new GarageHomePage(driver());

        boolean result = garagePage.validateAddedVehicle("DL6SAM5099");

        LoggerUtils.info("Validation result when element not visible: " + result);
        Assert.assertFalse(result, "Validation should fail if element is missing");

        LoggerUtils.info("Finished test: validateVehicle_elementNotVisible");
    }
}
