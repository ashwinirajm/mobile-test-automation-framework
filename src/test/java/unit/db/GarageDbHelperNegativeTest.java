package unitTests.db;

import base.BaseTest;
import br.com.six2six.fixturefactory.Fixture;
import core.db.GarageDbHelper;
import core.db.GarageDbConstants;
import dataTemplatesPojo.VehicleDetails;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import core.db.DbUtils;

public class GarageDbHelperNegativeTest extends BaseTest {

    @Test(description = "DB cleanup for non-existent user should not fail")
    public void testResetUserDataForNonExistentUser() {
        VehicleDetails vehicle = Fixture.from(VehicleDetails.class).gimme("validVehicle");
        vehicle.setUserId("nonexistent_user_123");

        // Should not throw exception
        GarageDbHelper.resetUserDataForAddingVehicle(vehicle);

        // Verify DB has no rows for this user (still empty)
        List<Map<String, Object>> rows = DbUtils.executeQuery(
            "SELECT * FROM user_details WHERE user_id='" + vehicle.getUserId() + "'",
            GarageDbConstants.GARAGE
        );
        Assert.assertTrue(rows.isEmpty(), "Non-existent user should have no rows");
    }

    @Test(description = "DB setup with invalid vehicle number should not crash")
    public void testSetupDatabaseWithInvalidVehicle() {
        VehicleDetails vehicle = Fixture.from(VehicleDetails.class).gimme("validVehicle");
        vehicle.setVehicleNumber("INVALID1234");

        // Should handle gracefully
        GarageDbHelper.setupDatabaseForOnboarding(vehicle);

        // Verify DB does not have a row with this invalid vehicle
        List<Map<String, Object>> rows = DbUtils.executeQuery(
            "SELECT * FROM owner_details WHERE vehicle_number='" + vehicle.getVehicleNumber() + "'",
            GarageDbConstants.GARAGE
        );
        Assert.assertTrue(rows.isEmpty(), "Invalid vehicle should not exist in owner_details");
    }
}

