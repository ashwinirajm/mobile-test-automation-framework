package unitTest.db;

import base.BaseTest;
import br.com.six2six.fixturefactory.Fixture;
import core.db.GarageDbConstants;
import core.db.GarageDbHelper;
import core.db.DbUtils;
import dataTemplatesPojo.VehicleDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Negative DB unit tests for GarageDbHelper
 * Focused on invalid users or vehicle numbers
 */
public class GarageDbHelperNegativeTest extends BaseTest {

    @Test(
        groups = {"unit", "db"},
        description = "DB cleanup should not fail for non-existent user"
    )
    public void resetUserData_nonExistingUser() {
        LoggerUtils.info("Starting test: resetUserData_nonExistingUser");

        VehicleDetails vehicle =
            Fixture.from(VehicleDetails.class).gimme("validVehicle");
        vehicle.setUserId("unknown_user_123");

        GarageDbHelper.resetUserDataForAddingVehicle(vehicle);
        LoggerUtils.info("Executed DB cleanup for non-existing user");

        List<Map<String, Object>> rows =
            DbUtils.executeQuery(
                "SELECT * FROM user_details WHERE user_id='unknown_user_123'",
                GarageDbConstants.GARAGE
            );

        LoggerUtils.info("Number of rows fetched for unknown user: " + rows.size());
        Assert.assertTrue(rows.isEmpty(), "No DB rows should exist for invalid user");

        LoggerUtils.info("Finished test: resetUserData_nonExistingUser");
    }

    @Test(
        groups = {"unit", "db"},
        description = "DB setup should not break for invalid vehicle number"
    )
    public void setupDatabase_invalidVehicleNumber() {
        LoggerUtils.info("Starting test: setupDatabase_invalidVehicleNumber");

        VehicleDetails vehicle =
            Fixture.from(VehicleDetails.class).gimme("validVehicle");
        vehicle.setVehicleNumber("INVALID_VEH");

        GarageDbHelper.setupDatabaseForOnboarding(vehicle);
        LoggerUtils.info("Executed DB setup for invalid vehicle");

        List<Map<String, Object>> rows =
            DbUtils.executeQuery(
                "SELECT * FROM owner_details WHERE vehicle_number='INVALID_VEH'",
                GarageDbConstants.GARAGE
            );

        LoggerUtils.info("Number of rows fetched for invalid vehicle: " + rows.size());
        Assert.assertTrue(rows.isEmpty(), "Invalid vehicle should not be updated");

        LoggerUtils.info("Finished test: setupDatabase_invalidVehicleNumber");
    }
}
