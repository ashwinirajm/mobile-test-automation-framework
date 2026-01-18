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

public class GarageDbHelperNegativeTest extends BaseTest {

    @Test(description = "DB cleanup should not fail for non-existent user")
    public void resetUserData_nonExistingUser() {
        VehicleDetails vehicle =
            Fixture.from(VehicleDetails.class).gimme("validVehicle");

        vehicle.setUserId("unknown_user_123");

        // Should not throw exception
        GarageDbHelper.resetUserDataForAddingVehicle(vehicle);

        List<Map<String, Object>> rows =
            DbUtils.executeQuery(
                "SELECT * FROM user_details WHERE user_id='unknown_user_123'",
                GarageDbConstants.GARAGE
            );

        Assert.assertTrue(rows.isEmpty(), "No DB rows should exist for invalid user");
    }

    @Test(description = "DB setup should not break for invalid vehicle number")
    public void setupDatabase_invalidVehicleNumber() {
        VehicleDetails vehicle =
            Fixture.from(VehicleDetails.class).gimme("validVehicle");

        vehicle.setVehicleNumber("INVALID_VEH");

        GarageDbHelper.setupDatabaseForOnboarding(vehicle);

        List<Map<String, Object>> rows =
            DbUtils.executeQuery(
                "SELECT * FROM owner_details WHERE vehicle_number='INVALID_VEH'",
                GarageDbConstants.GARAGE
            );

        Assert.assertTrue(rows.isEmpty(), "Invalid vehicle should not be updated");
    }
}
