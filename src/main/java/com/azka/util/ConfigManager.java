package com.azka.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to manage configuration properties
 */
public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;
    
    private ConfigManager() {
        loadProperties();
    }
    
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public String getApiBaseUrl() {
        return getProperty("api.baseUrl");
    }
    
    public String getUiBaseUrl() {
        return getProperty("ui.baseUrl");
    }
    
    public String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "true"));
    }
    
    public int getImplicitTimeout() {
        return Integer.parseInt(getProperty("timeout.implicit", "10"));
    }
    
    public int getExplicitTimeout() {
        return Integer.parseInt(getProperty("timeout.explicit", "30"));
    }
    
    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("timeout.pageLoad", "60"));
    }
    
    public String getApiKey() {
        return getProperty("api.key");
    }
}
