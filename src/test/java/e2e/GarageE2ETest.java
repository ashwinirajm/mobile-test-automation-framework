package E2ETest;

import base.BaseTest;
import br.com.six2six.fixturefactory.Fixture;
import core.db.GarageDbHelper;
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
 */
public class GarageE2ETest extends BaseTest {

    @Test(
        groups = {"E2E", "Garage"},
        description = "E2E: Add vehicle to garage with DB cleanup and fixture-driven data"
    )
    public void validateAddVehicleToGarageWithDB() {

        // 🔹 Test Data (Fixture Driven)
        LoginDetails loginDetails = Fixture.from(LoginDetails.class).gimme("validLogin");
        VehicleDetails vehicleDetails = Fixture.from(VehicleDetails.class).gimme("validVehicle");

        // 🔹 1️⃣ Clean DB for this user
        GarageDbHelper.resetUserDataForAddingVehicle(vehicleDetails);

        // 🔹 2️⃣ Setup DB state if needed (owner details, vehicle status)
        GarageDbHelper.setupDatabaseForOnboarding(vehicleDetails);

        // 🔹 Pages
        LoginPage loginPage = new LoginPage(driver());
        HomePage homePage = new HomePage(driver());
        GarageHomePage garageHomePage = new GarageHomePage(driver());

        // 🔹 3️⃣ Login
        loginPage.inputMobileNumberNewApp(loginDetails.getMobileNumber());

        // 🔹 4️⃣ Navigate to Garage
        homePage.navigateToGarageHome();

        // 🔹 5️⃣ Add Vehicle
        garageHomePage.addToGarage();
        garageHomePage.inputVehicleNumber(vehicleDetails.getVehicleNumber());

        // 🔹 6️⃣ Validate Vehicle Added
        Assert.assertTrue(
            garageHomePage.validateAddedVehicle(vehicleDetails.getVehicleNumber()),
            "Vehicle was not added to garage"
        );
    }
}
