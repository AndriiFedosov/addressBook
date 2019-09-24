package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    private static final String JDBC = "jdbc:mysql:";
    private static final String HOST_AND_PORT = "//localhost:3306";
    private static final String NAME_DB = "/address_book";
    private static final String UNICODE = "useUnicode=true";
    private static final String JDBC_TIME_ZONE_SHIFT = "useJDBCCompliantTimezoneShift=true";
    private static final String DATE_TIME = "useLegacyDatetimeCode=false";
    private static final String SERVICE_TOME_ZONE = "serverTimezone=UTC";
    private static final String SEPARATOR = "?";
    private static final String SEPARATOR_AND = "&";
    private static final String FULL_URL = JDBC + HOST_AND_PORT + NAME_DB + SEPARATOR +
            UNICODE + SEPARATOR_AND + JDBC_TIME_ZONE_SHIFT +
            SEPARATOR_AND + DATE_TIME + SEPARATOR_AND + SERVICE_TOME_ZONE;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS contacts(" +
            "  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
            "  `name` VARCHAR(255) NOT NULL, " +
            "  `last_name` VARCHAR(255) NULL, " +
            "  `phone_number` VARCHAR(45) NOT NULL, " +
            "  `age` INT NULL, " +
            "  `married` BOOLEAN DEFAULT false, " +
            "  `create_data_time` VARCHAR(45)," +
            "`update_data_time` VARCHAR(45))";

    private static final String USER = "root";
    private static final String PASSWORD = "root";


    private static Connection connection = null;

    static Connection getConnect() {
        if (connection != null) {
            return connection;
        } else {
            try {
                connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static void connectAndCreateDataBase() {
        Statement statement;
        try {
            statement = getConnect().createStatement();
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConection() {
        try {
            getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
