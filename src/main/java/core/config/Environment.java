package core.config;

/**
 * Enum to represent supported environments for test execution.
 * Provides compile-time safety and avoids typos in config values.
 */
public enum Environment {
    QA,
    STAGE,
    PROD;

    /**
     * Converts a string to Environment enum.
     * @param value String representation of environment
     * @return Environment enum
     * @throws IllegalArgumentException if invalid
     */
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
