import br.com.six2six.fixturefactory.Fixture;
import dataTemplatePojo.login.LoginData;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validateLoginWithValidMobile() {

        LoginDetails loginDetails =
                Fixture.from(LoginDetails.class).gimme("validMobileLogin");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputMobileNumberNewApp(loginDetails.getMobileNumber());
    }
}
