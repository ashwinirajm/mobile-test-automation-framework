package E2ETest;

import base.BaseTest;
import br.com.six2six.fixturefactory.Fixture;
import core.db.GarageDbHelper;
import core.utils.LoggerUtils;
import dataTemplatesPojo.LoginDetails;
import dataTemplatesPojo.VehicleDetails;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GarageHomePage;
import pages.HomePage;
import pages.LoginPage;

/**
 * E2E Flow:
 * 1. DB Cleanup → 2. DB Setup → 3. Login → 4. Navigate to Garage → 
 * 5. Add Vehicle → 6. Validate Vehicle Added
 *
 * Reporting:
 * - Step-wise logs
 * - Screenshots on critical actions
 */
public class GarageE2ETest extends BaseTest {

    @Test(
        groups = {"E2E", "Garage"},
        description = "E2E: Add vehicle to garage with DB cleanup and fixture-driven data"
    )
    public void validateAddVehicleToGarageWithDB() {

        LoggerUtils.info("========== START E2E TEST: Add Vehicle to Garage ==========");

        // --------------------------
        // Test Data
        // --------------------------
        LoginDetails loginDetails = Fixture.from(LoginDetails.class).gimme("validLogin");
        VehicleDetails vehicleDetails = Fixture.from(VehicleDetails.class).gimme("validVehicle");

        LoggerUtils.info("Loaded fixture data for login and vehicle");

        // --------------------------
        // Database Cleanup & Setup
        // --------------------------
        LoggerUtils.info("Cleaning up user data before onboarding");
        GarageDbHelper.resetUserDataForAddingVehicle(vehicleDetails);
        LoggerUtils.info("Setting up DB state for onboarding");
        GarageDbHelper.setupDatabaseForOnboarding(vehicleDetails);

        // --------------------------
        // Page Objects
        // --------------------------
        LoginPage loginPage = new LoginPage(driver());
        HomePage homePage = new HomePage(driver());
        GarageHomePage garageHomePage = new GarageHomePage(driver());

        // --------------------------
        // Step 1: Login
        // --------------------------
        LoggerUtils.info("Logging in with mobile number: " + loginDetails.getMobileNumber());
        loginPage.inputMobileNumberNewApp(loginDetails.getMobileNumber());
        LoggerUtils.attachScreenshot(driver(), "After_Login");

        // --------------------------
        // Step 2: Navigate to Garage
        // --------------------------
        LoggerUtils.info("Navigating to Garage Home Page");
        homePage.navigateToGarageHome();
        LoggerUtils.attachScreenshot(driver(), "Garage_Home");

        // --------------------------
        // Step 3: Add Vehicle
        // --------------------------
        LoggerUtils.info("Clicking Add to Garage button");
        garageHomePage.addToGarage();
        LoggerUtils.attachScreenshot(driver(), "Click_AddToGarage");

        LoggerUtils.info("Entering vehicle number: " + vehicleDetails.getVehicleNumber());
        garageHomePage.inputVehicleNumber(vehicleDetails.getVehicleNumber());
        LoggerUtils.attachScreenshot(driver(), "Vehicle_Number_Entered");

        // --------------------------
        // Step 4: Validate Vehicle Added
        // --------------------------
        LoggerUtils.info("Validating vehicle added to garage");
        boolean isAdded = garageHomePage.validateAddedVehicle(vehicleDetails.getVehicleNumber());
        LoggerUtils.attachScreenshot(driver(), "Validate_Vehicle");

        Assert.assertTrue(isAdded, "Vehicle was not added to garage");

        LoggerUtils.info("========== END E2E TEST ==========");
    }
}
