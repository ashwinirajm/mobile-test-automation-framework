package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtils {

    public enum LogsType {
        INFO,
        WARN,
        ERROR
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message with timestamp and log type
     *
     * @param type    Log type (INFO/WARN/ERROR)
     * @param message Message to log
     */
    public static void log(LogsType type, String message) {
        String timestamp = sdf.format(new Date());
        System.out.println("[" + timestamp + "] [" + type + "] " + message);
    }

    // Optional shorthand methods
    public static void info(String message) {
        log(LogsType.INFO, message);
    }

    public static void warn(String message) {
        log(LogsType.WARN, message);
    }

    public static void error(String message) {
        log(LogsType.ERROR, message);
    }
}

