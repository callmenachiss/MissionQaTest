package AutomationTest.mission.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends BasePage {

    @FindBy(css = "[data-test='firstName']")
    private WebElement firstNameField;

    @FindBy(css = "[data-test='lastName']")
    private WebElement lastNameField;

    @FindBy(css = "[data-test='postalCode']")
    private WebElement postalCodeField;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitUntilUrlContains("checkout-step-one");
        visible(firstNameField);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeField, postalCode);
    }

    public void continueToOverview() {
        clickWithJavaScript(continueButton);
    }
}
