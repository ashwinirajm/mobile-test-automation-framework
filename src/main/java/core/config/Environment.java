package core.config;

/**
 * Enum representing supported environments.
 */
public enum Environment {
    QA,
    STAGE,
    PROD;

    public static Environment from(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Environment value cannot be null or empty");
        }
        try {
            return Environment.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported environment: " + value, e);
        }
    }
}
