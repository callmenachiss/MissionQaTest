package AutomationTest.mission.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String CONFIG_FILE = "TestData.properties";
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (hasText(systemValue)) {
            return systemValue.trim();
        }

        String environmentValue = System.getenv(toEnvironmentKey(key));
        if (hasText(environmentValue)) {
            return environmentValue.trim();
        }

        return PROPERTIES.getProperty(key, "").trim();
    }

    public static String getRequired(String key) {
        String value = get(key);
        if (!hasText(value)) {
            throw new IllegalStateException("Missing required configuration: " + key);
        }
        return value;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return hasText(value) ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean hasValue(String key) {
        return hasText(get(key));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);

        if (inputStream == null) {
            return properties;
        }

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load " + CONFIG_FILE, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
        return properties;
    }

    private static String toEnvironmentKey(String key) {
        return key.replace('.', '_').replace('-', '_').toUpperCase();
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
