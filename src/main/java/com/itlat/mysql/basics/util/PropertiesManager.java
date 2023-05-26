package com.itlat.mysql.basics.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesManager {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesManager() {

    }

    private static void loadProperties() {
        try (InputStream stream = PropertiesManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
