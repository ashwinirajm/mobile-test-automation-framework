package dataTemplatePojo.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * LoginData
 *
 * Purpose:
 * - Holds login-related test data
 * - Used by FixtureFactory to generate test inputs
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {

    private String mobileNumber;
}
