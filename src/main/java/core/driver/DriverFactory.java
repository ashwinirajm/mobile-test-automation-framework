package core.driver;

import core.config.ConfigManager;
import core.config.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory() {
        // prevent instantiation
    }

    public static AppiumDriver createDriver() {
        Platform platform = ConfigManager.getPlatform();

        switch (platform) {
            case ANDROID -> {
                System.out.println("Creating Android driver...");
                return createAndroidDriver();
            }
            case IOS -> {
                System.out.println("Creating iOS driver...");
                return createIOSDriver();
            }
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    // ---------- ANDROID ----------

    private static AppiumDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(ConfigManager.get("android.platform.name"))
                .setDeviceName(ConfigManager.get("android.device.name"))
                .setAutomationName(ConfigManager.get("android.automation.name"))
                .setApp(ConfigManager.get("android.app.path"))
                .setAppPackage(ConfigManager.get("android.app.package"))
                .setAppActivity(ConfigManager.get("android.app.activity"));

        return new AndroidDriver(getServerUrl(), options);
    }

    // ---------- IOS ----------

    private static AppiumDriver createIOSDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName(ConfigManager.get("ios.platform.name"))
                .setPlatformVersion(ConfigManager.get("ios.platform.version"))
                .setDeviceName(ConfigManager.get("ios.device.name"))
                .setAutomationName(ConfigManager.get("ios.automation.name"))
                .setApp(ConfigManager.get("ios.app.path"));

        return new IOSDriver(getServerUrl(), options);
    }

    // ---------- COMMON ----------

    private static URL getServerUrl() {
        try {
            return new URL(ConfigManager.get("appium.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium Server URL", e);
        }
    }
}
