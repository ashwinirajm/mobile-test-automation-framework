package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LoggerUtils
 *
 * Purpose:
 * - Logs messages with timestamp and log level (INFO/WARN/ERROR)
 * - Supports reporting to Allure (text logs + screenshots)
 */
public class LoggerUtils {

    public enum LogsType {
        INFO,
        WARN,
        ERROR
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message with timestamp and log type to console
     * and attaches it to Allure report
     *
     * @param type    Log type (INFO/WARN/ERROR)
     * @param message Message to log
     */
    public static void ReportLog(LogsType type, String message) {
        String timestamp = sdf.format(new Date());
        String logMsg = "[" + timestamp + "] [" + type + "] " + message;

        // Console log
        if (type == LogsType.ERROR) {
            System.err.println(logMsg);
        } else {
            System.out.println(logMsg);
        }

        // Attach to Allure
        attachLogToAllure(logMsg);
    }

    // ----------------------
    // Optional shorthand
    // ----------------------
    public static void info(String message) {
        ReportLog(LogsType.INFO, message);
    }

    public static void warn(String message) {
        ReportLog(LogsType.WARN, message);
    }

    public static void error(String message) {
        ReportLog(LogsType.ERROR, message);
    }

    // ----------------------
    // Allure reporting support
    // ----------------------

    /**
     * Attaches a plain text log to Allure
     */
    @Attachment(value = "Step Log", type = "text/plain")
    private static String attachLogToAllure(String message) {
        return message;
    }

    /**
     * Captures a screenshot from the driver and attaches to Allure
     *
     * @param driver WebDriver or AppiumDriver instance
     * @param name   Screenshot name in report
     * @return byte[] of screenshot
     */
    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver, String name) {
        if (driver == null) return new byte[0];
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
