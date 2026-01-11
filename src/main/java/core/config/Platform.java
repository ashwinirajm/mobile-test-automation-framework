package core.config;

/**
 * Enum representing supported mobile platforms.
 */
public enum Platform {
    ANDROID,
    IOS;

    public static Platform from(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Platform value cannot be null or empty");
        }
        try {
            return Platform.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported platform: " + value, e);
        }
    }
}
