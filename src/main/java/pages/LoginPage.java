package pages.login;

import global.PageUtils;
import io.appium.java_client.pagefactory.*;
import java.util.Objects;
import org.openqa.selenium.WebElement;


public class NewLoginPage extends PageUtils {

private void inputMobileNumberNewApp(String mobileNum) {
    waitUntilVisible(mobileInput);
    mobileInput.sendKeys(mobileNum);
    waitUntilClickable(creditFetchCheckBox);
    creditFetchCheckBox.click();
    waitUntilClickable(proceedBtn);
    proceedBtn.click();
}
