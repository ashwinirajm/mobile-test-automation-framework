package dataTemplatesPojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * LoginDetails
 *
 * Purpose:
 * - Holds vehicle and owner test data
 * - Used by FixtureFactory templates
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetails {

    private String userId;
    private String vehicleNumber;
    private String vehicleOwnerName;
}
