package core.db;

import dataTemplatesPojo.VehicleDetails;
import utilities.LoggerUtils;

/**
 * GarageDbHelper
 *
 * Contains scenario-specific DB operations
 * related to Garage onboarding and add vehicle flows.
 */
public final class GarageDbHelper {

    private GarageDbHelper() {
        // Utility class
    }

    /**
     * Cleans existing user data before add vehicle flow
     */
    public static void resetUserDataForAddingVehicle(VehicleDetails vehicleDetails) {

        LoggerUtils.ReportLog(
            LoggerUtils.LogsType.INFO,
            "Cleaning up existing user data before add vehicle flow"
        );

        DbUtils.executeUpdate(
            GarageDbConstants.GARAGE,
            String.format(
                "DELETE FROM %s.user_details WHERE user_id = '%s';",
                GarageDbConstants.GARAGE,
                vehicleDetails.getUserId()
            )
        );

        DbUtils.executeUpdate(
            GarageDbConstants.GARAGE,
            String.format(
                "DELETE FROM %s.user_vehicle WHERE user_id = '%s';",
                GarageDbConstants.GARAGE,
                vehicleDetails.getUserId()
            )
        );

        LoggerUtils.ReportLog(
            LoggerUtils.LogsType.INFO,
            "User data cleanup completed"
        );
    }

    /**
     * Prepares DB state for onboarding / auto-suggestion flow
     */
    public static void setupDatabaseForOnboarding(VehicleDetails vehicleDetails) {

        LoggerUtils.ReportLog(
            LoggerUtils.LogsType.INFO,
            "Setting up database state for onboarding"
        );

        DbUtils.executeUpdate(
            GarageDbConstants.GARAGE,
            String.format(
                "UPDATE %s.owner_details SET owner_name = '%s' WHERE vehicle_number = '%s';",
                GarageDbConstants.GARAGE,
                vehicleDetails.getVehicleOwnerName(),
                vehicleDetails.getVehicleNumber()
            )
        );

        DbUtils.executeUpdate(
            GarageDbConstants.GARAGE,
            String.format(
                "UPDATE %s.user_vehicle_status SET status = '%s' " +
                "WHERE vehicle_number = '%s' AND user_id = '%s';",
                GarageDbConstants.GARAGE,
                GarageDbConstants.AUTO_SUGGESTION,
                vehicleDetails.getVehicleNumber(),
                vehicleDetails.getUserId()
            )
        );

        LoggerUtils.ReportLog(
            LoggerUtils.LogsType.INFO,
            "Database setup completed for onboarding"
        );
    }
}

