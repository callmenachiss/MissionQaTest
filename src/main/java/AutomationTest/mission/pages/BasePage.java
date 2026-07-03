package AutomationTest.mission.pages;

import AutomationTest.mission.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait.seconds", 10)));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void clickWithJavaScript(WebElement element) {
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", clickableElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableElement);
    }

    protected void type(WebElement element, String text) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", visibleElement);
        visibleElement.clear();
        visibleElement.sendKeys(text);
        if (!text.equals(visibleElement.getAttribute("value"))) {
            setInputValueWithJavaScript(visibleElement, text);
        }
        wait.until(driver -> text.equals(visibleElement.getAttribute("value")));
    }

    protected String textOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
    }

    protected WebElement visible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement child(WebElement parent, By locator) {
        return parent.findElement(locator);
    }

    protected List<WebElement> children(WebElement parent, By locator) {
        return parent.findElements(locator);
    }

    protected void waitUntilUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    protected void waitUntilListIsNotEmpty(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitUntil(ExpectedCondition<Boolean> condition) {
        wait.until(condition);
    }

    private void setInputValueWithJavaScript(WebElement element, String text) {
        String script =
                "const input = arguments[0];" +
                "const value = arguments[1];" +
                "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(input, value);" +
                "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                "input.dispatchEvent(new Event('change', { bubbles: true }));";
        ((JavascriptExecutor) driver).executeScript(script, element, text);
    }
}
