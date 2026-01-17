package dataTemplate.login;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import dataTemplatePojo.login.LoginData;

/**
 * LoginTemplate
 *
 * Purpose:
 * - Defines reusable login data variations
 * - Keeps test data separate from tests & pages
 */
public class LoginTemplate implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(LoginData.class)
                .addTemplate("validMobileLogin", new Rule() {{
                    add("mobileNumber", "9876543210");
                }});

        Fixture.of(LoginData.class)
                .addTemplate("invalidMobileLogin", new Rule() {{
                    add("mobileNumber", "12345");
                }});
    }
}
