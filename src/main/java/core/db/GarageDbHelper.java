package global.db;

import dataTemplatesPojo.VehicleDetails;
import utilities.LoggerUtils;

/**
 * GarageDbHelper
 *
 * Contains scenario-specific DB operations
 * related to Garage onboarding and add vehicle flows.
 */
public final class GarageDbHelper {

    private static final String GARAGE_DB = garageDbConstants.GARAGE;

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
            HERMIONE_DB,
            String.format(
                "DELETE FROM hermione.user_details WHERE user_id = '%s';",
                vehicleDetails.getUserId()
            )
        );

        DbUtils.executeUpdate(
            HERMIONE_DB,
            String.format(
                "DELETE FROM hermione.user_vehicle WHERE user_id = '%s';",
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
            HERMIONE_DB,
            String.format(
                "UPDATE hermione.owner_details " +
                "SET owner_name = '%s' WHERE vehicle_number = '%s';",
                vehicleDetails.getVehicleOwnerName(),
                vehicleDetails.getVehicleNumber()
            )
        );

        DbUtils.executeUpdate(
            HERMIONE_DB,
            String.format(
                "UPDATE hermione.user_vehicle_status " +
                "SET status = 'AUTO_SUGGESTION' " +
                "WHERE vehicle_number = '%s' AND user_id = '%s';",
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
