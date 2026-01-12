public class GarageTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Pucc.class).addTemplate(
            "puccExpiryDate",
            new Rule() {{
                add("expiryDate", "2024-12-09 18:29:59");
                add("vehicleNumber", "DL6SAM5099");
            }}
        );
    }
}
