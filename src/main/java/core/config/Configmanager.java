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

    public static String get(String key) {
        String value = System.getProperty(key);
        if (value != null) return value;

        value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPERTIES.getProperty(key, defaultValue));
    }

    public static Environment getEnvironment() {
        return Environment.from(get("env", Environment.QA.name()));
    }

    public static Platform getPlatform() {
        return Platform.from(get("platform", Platform.ANDROID.name()));
    }

    // ---------------- PRIVATE LOADERS ----------------

    private static void loadBaseConfigs() {
        loadOptional("config/execution.properties");
        loadOptional("config/app.properties");      
        loadOptional("config/timeouts.properties"); 
    }

    private static void loadPlatformConfig() {
        Platform platform = getPlatform();

        switch (platform) {
            case ANDROID -> load("config/android.properties");
            case IOS -> load("config/ios.properties");
        }
        System.out.println("Loaded platform config: " + platform);
    }

    private static void loadEnvConfig() {
        Environment env = getEnvironment();
        load("config/env/" + env.name().toLowerCase() + ".properties");
        System.out.println("Loaded environment config: " + env);
    }

    private static void load(String path) {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Config file not found: " + path);
            }
            PROPERTIES.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }

    private static void loadOptional(String path) {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(path)) {
            if (is != null) PROPERTIES.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + path, e);
        }
    }
}
