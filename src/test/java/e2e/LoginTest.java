import br.com.six2six.fixturefactory.Fixture;
import dataTemplatePojo.login.LoginData;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validateLoginWithValidMobile() {

        LoginData loginData =
                Fixture.from(LoginData.class).gimme("validMobileLogin");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputMobileNumberNewApp(loginData.getMobileNumber());
    }
}
