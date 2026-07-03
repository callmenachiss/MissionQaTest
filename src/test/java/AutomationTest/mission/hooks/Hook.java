package AutomationTest.mission.hooks;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import AutomationTest.mission.config.ConfigReader;
import AutomationTest.mission.driver.BrowserSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hook {

    @Before("@UI")
    public void startBrowser() {
        WebDriver driver = BrowserSetup.startBrowser();
        Duration timeout = Duration.ofSeconds(ConfigReader.getInt("browser.timeout.seconds", 20));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(timeout);
        driver.manage().timeouts().implicitlyWait(timeout);
        driver.manage().timeouts().scriptTimeout(timeout);
    }

    @After("@UI")
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            saveScreenshot(scenario);
        }
        BrowserSetup.quitDriver();
    }

    private void saveScreenshot(Scenario scenario) {
        WebDriver driver = BrowserSetup.getDriver();
        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenshot, "image/png", "Failure Screenshot");

        File screenshotDirectory = new File(ConfigReader.getRequired("screenshot.dir"));
        File destination = new File(screenshotDirectory, scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png");

        try {
            FileUtils.forceMkdir(screenshotDirectory);
            FileUtils.writeByteArrayToFile(destination, screenshot);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save screenshot to " + destination.getAbsolutePath(), e);
        }
    }
}
