package unitTest.pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GarageHomePage;

public class GarageHomePageNegativeTest extends BaseTest {

    @Test(description = "Invalid vehicle number should not be added")
    public void addInvalidVehicleNumber() {
        GarageHomePage garagePage = new GarageHomePage(driver());

        garagePage.addToGarage();
        garagePage.inputVehicleNumber("INVALID123");

        Assert.assertFalse(
            garagePage.validateAddedVehicle("INVALID123"),
            "Invalid vehicle should not be added"
        );
    }

    @Test(description = "Validation should fail if added vehicle element not visible")
    public void validateVehicle_elementNotVisible() {
        GarageHomePage garagePage = new GarageHomePage(driver());

        boolean result = garagePage.validateAddedVehicle("DL6SAM5099");

        Assert.assertFalse(result, "Validation should fail if element is missing");
    }
}
