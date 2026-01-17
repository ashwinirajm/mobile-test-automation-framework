package unitTests.pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GarageHomePage;

public class GarageHomePageNegativeTest extends BaseTest {

    @Test(description = "Entering invalid vehicle number should fail validation")
    public void testInvalidVehicleNumber() {
        GarageHomePage garagePage = new GarageHomePage(driver());
        garagePage.addToGarage();

        // Enter invalid format
        garagePage.inputVehicleNumber("INVALID!!");

        // Validation should fail
        boolean isAdded = garagePage.validateAddedVehicle("INVALID!!");
        Assert.assertFalse(isAdded, "Invalid vehicle number should not be added");
    }

    @Test(description = "Added vehicle element not visible should return false")
    public void testAddedVehicleElementNotVisible() {
        GarageHomePage garagePage = new GarageHomePage(driver());

        // Simulate element not visible (can mock in framework)
        boolean isAdded = garagePage.validateAddedVehicle("DL6SAM5099");
        Assert.assertFalse(isAdded, "Validation should return false if element not visible");
    }
}
