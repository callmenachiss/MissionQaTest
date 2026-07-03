package AutomationTest.mission.pages;

import AutomationTest.mission.utils.MoneyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElement> checkoutItems;

    @FindBy(css = "[data-test='subtotal-label']")
    private WebElement itemTotalLabel;

    @FindBy(css = "[data-test='tax-label']")
    private WebElement taxLabel;

    @FindBy(css = "[data-test='total-label']")
    private WebElement totalLabel;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitUntilUrlContains("checkout-step-two");
        visible(itemTotalLabel);
    }

    public List<BigDecimal> listedItemPrices() {
        waitUntilListIsNotEmpty(checkoutItems);
        List<BigDecimal> prices = new ArrayList<BigDecimal>();
        for (WebElement item : checkoutItems) {
            String priceText = child(item, By.cssSelector("[data-test='inventory-item-price']")).getText();
            prices.add(MoneyUtils.parseMoney(priceText));
        }
        return prices;
    }

    public BigDecimal displayedItemTotal() {
        return MoneyUtils.parseMoney(textOf(itemTotalLabel));
    }

    public BigDecimal displayedTax() {
        return MoneyUtils.parseMoney(textOf(taxLabel));
    }

    public BigDecimal displayedTotal() {
        return MoneyUtils.parseMoney(textOf(totalLabel));
    }
}
