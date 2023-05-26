package com.itlat.mysql.basics.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.user";
    private static final String DB_PASSWORD = "db.password";

    static {
        loadDriver();
    }

    private ConnectionManager() {
    }

    /**
     * Инициализация соединения с базой данных (параметры берутся из переменных окружения + см. файл application.properties)
     */
    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesManager.get(DB_URL),
                    PropertiesManager.get(DB_USERNAME),
                    PropertiesManager.get(DB_PASSWORD)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
