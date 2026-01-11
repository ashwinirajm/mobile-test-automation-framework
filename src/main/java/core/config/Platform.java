package core.config;

/**
 * Enum to represent supported mobile platforms.
 * Used by ConfigManager and DriverFactory to load capabilities.
 */
public enum Platform {
    ANDROID,
    IOS;

    /**
     * Converts a string to Platform enum.
     * @param value string value (android / ios)
     * @return Platform enum
     */
    public static Platform from(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Platform value cannot be null or empty");
        }

        switch (value.toLowerCase()) {
            case "android":
                return ANDROID;
            case "ios":
                return IOS;
            default:
                throw new IllegalArgumentException("Unsupported platform: " + value);
        }
    }
}
