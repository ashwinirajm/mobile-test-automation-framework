package E2ETest;

import base.BaseTest;
import br.com.six2six.fixturefactory.Fixture;
import dataTemplatesPojo.LoginDetails;
import dataTemplatesPojo.VehicleDetails;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GarageHomePage;
import pages.HomePage;
import pages.LoginPage;

/**
 * E2E Flow:
 * Login → Navigate to Garage → Add Vehicle → Validate Vehicle
 */
public class GarageE2ETest extends BaseTest {

    @Test(
        groups = {"E2E", "Garage"},
        description = "Validate add vehicle to garage using fixture driven data"
    )
    public void validateAddVehicleToGarage() {

        // 🔹 Test Data (Fixture Driven)
        LoginDetails loginDetails =
            Fixture.from(LoginDetails.class).gimme("validLogin");

        VehicleDetails vehicleDetails =
            Fixture.from(VehicleDetails.class).gimme("validVehicle");

        // 🔹 Pages
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        GarageHomePage garageHomePage = new GarageHomePage(driver);

        // 🔹 Flow
        loginPage.inputMobileNumberNewApp(loginDetails.getMobileNumber());

        homePage.navigateToGarageHome();

        garageHomePage.addToGarage();

        garageHomePage.inputVehicleNumber(vehicleDetails.getVehicleNumber());

        // 🔹 Validation
        Assert.assertTrue(
            garageHomePage.validateAddedVehicle(vehicleDetails.getVehicleNumber()),
            "Vehicle was not added to garage"
        );
    }
}

