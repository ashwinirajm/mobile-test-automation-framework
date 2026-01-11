package core.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Centralized configuration manager for platform, environment, and execution configs.
 * Supports system property overrides (e.g., -Dplatform=ios, -Denv=stage).
 */
public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadBaseConfigs();
        loadPlatformConfig();
        loadEnvConfig();
    }

    private ConfigManager() {
        // prevent instantiation
    }

    // ---------------- PUBLIC API ----------------

    /**
     * Get property value. Checks system property first, then loaded properties.
     * @param key property name
     * @return property value
     */
    public static String get(String key) {
        String value = System.getProperty(key);
        if (value != null) return value;

        value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    /**
     * Get property with default fallback.
     */
    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPERTIES.getProperty(key, defaultValue));
    }

    /**
     * Get Environment enum
     */
    public static Environment getEnvironment() {
        String env = get("env", "qa");
        return Environment.from(env);
    }

    // ---------------- PRIVATE LOADERS ----------------

    private static void loadBaseConfigs() {
        load("config/execution.properties");
        load("config/app.properties");      // optional, if you have general app settings
        load("config/timeouts.properties"); // optional, if you have default timeouts
    }

    private static void loadPlatformConfig() {
        String platform = get("platform", "android").toLowerCase();

        switch (platform) {
            case "android":
                load("config/android.properties");
                break;
            case "ios":
                load("config/ios.properties");
                break;
            default:
                throw new RuntimeException("Unsupported platform: " + platform);
        }
    }

    private static void loadEnvConfig() {
        String env = get("env", "qa").toLowerCase();
        load("config/env/" + env + ".properties");
    }

    private static void load(String path) {
        try (InputStream is = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(path)) {

            if (is == null) {
                throw new RuntimeException("Config file not found: " + path);
            }

            PROPERTIES.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }
}
