package dataTemplates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import dataTemplatesPojo.VehicleDetails;

public class VehicleDetailsTemplate implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(VehicleDetails.class)
            .addTemplate("validVehicle", new Rule() {{
                add("vehicleNumber", "KA01AB1234");
            }});
    }
}
