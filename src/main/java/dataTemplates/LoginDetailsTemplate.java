package dataTemplates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import dataTemplatesPojo.LoginDetails;

/**
 * LoginDetailsTemplate
 *
 * Purpose:
 * - Defines reusable login data variations
 * - Keeps test data out of tests and pages
 */
public class LoginDetailsTemplate implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(LoginDetails.class)
                .addTemplate("validLogin", new Rule() {{
                    add("mobileNumber", "9876543210");
                }});
        
        Fixture.of(LoginDetails.class)
                .addTemplate("invalidLogin", new Rule() {{
                    add("mobileNumber", "12345");
                }});
    }
}
