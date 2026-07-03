package AutomationTest.mission.driver;
import AutomationTest.mission.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public final class BrowserSetup {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();

    private BrowserSetup() {
    }

    public static WebDriver startBrowser() {
        if (DRIVER.get() != null) {
            return DRIVER.get();
        }

        String browser = ConfigReader.get("browser");
        WebDriver driver;

        if ("chrome".equalsIgnoreCase(browser) || "chromeMac".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            disablePasswordManager(options);
            driver = new ChromeDriver(options);
        } else if ("chromeHeadless".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size="
                    + ConfigReader.get("browser.width") + "," + ConfigReader.get("browser.height"));
            disablePasswordManager(options);
            driver = new ChromeDriver(options);
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser value: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        DRIVER.set(driver);
        return driver;
    }

    private static void disablePasswordManager(ChromeOptions options) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,AutofillServerCommunication");
        options.addArguments("--disable-save-password-bubble");
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Browser has not been started. UI hooks must start the driver first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
