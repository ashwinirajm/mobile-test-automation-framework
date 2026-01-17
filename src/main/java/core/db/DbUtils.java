package core.db;

import com.cred.backend.base.DBHost;
import com.cred.backend.base.MySQLUtils;
import utilities.LoggerUtils;

import java.sql.*;
import java.util.*;

/**
 * DbUtils
 *
 * Stateless utility for DB operations.
 * Each operation manages its own connection for thread safety.
 */
public final class DbUtils {

    private DbUtils() {
        // Utility class
    }

    /**
     * Checks DB connectivity
     */
    public static boolean isDBConnectionSuccess(String dbName) {
        try (Connection connection =
                 MySQLUtils.connectionToDB(DBHost.MYSQL, dbName)) {

            if (connection != null && !connection.isClosed()) {
                LoggerUtils.ReportLog(LoggerUtils.LogsType.INFO,
                        "Successfully connected to DB: " + dbName);
                return true;
            }
        } catch (Exception e) {
            LoggerUtils.ReportLog(LoggerUtils.LogsType.ERROR,
                    "DB connection failed for " + dbName + " : " + e.getMessage());
        }
        return false;
    }

    /**
     * Executes INSERT / UPDATE / DELETE
     */
    public static int executeUpdate(String dbName, String query) {
        try (
            Connection connection = MySQLUtils.connectionToDB(DBHost.MYSQL, dbName);
            Statement statement = connection.createStatement()
        ) {
            int rowsAffected = statement.executeUpdate(query);
            LoggerUtils.ReportLog(
                LoggerUtils.LogsType.INFO,
                "Update executed. Rows affected: " + rowsAffected
            );
            return rowsAffected;
        } catch (SQLException | ClassNotFoundException e) {
            LoggerUtils.ReportLog(
                LoggerUtils.LogsType.ERROR,
                "Update failed: " + e.getMessage()
            );
            return 0;
        }
    }

    /**
     * Executes SELECT query
     */
    public static List<Map<String, Object>> executeQuery(String dbName, String query) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (
            Connection connection = MySQLUtils.connectionToDB(DBHost.MYSQL, dbName);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(row);
            }

            LoggerUtils.ReportLog(
                LoggerUtils.LogsType.INFO,
                "Query executed. Rows fetched: " + resultList.size()
            );

        } catch (SQLException | ClassNotFoundException e) {
            LoggerUtils.ReportLog(
                LoggerUtils.LogsType.ERROR,
                "Query failed: " + e.getMessage()
            );
        }
        return resultList;
    }
}
