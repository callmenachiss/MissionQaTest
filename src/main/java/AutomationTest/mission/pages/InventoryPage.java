package AutomationTest.mission.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElement> inventoryItems;

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement shoppingCartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitUntilUrlContains("inventory");
        waitUntilListIsNotEmpty(inventoryItems);
    }

    public void addProductsToCart(List<String> productNames) {
        int expectedCartCount = cartItemCount();
        for (String productName : productNames) {
            addProductToCart(productName);
            expectedCartCount++;
            waitUntilCartCountIs(expectedCartCount);
        }
    }

    public int cartItemCount() {
        List<WebElement> badges = driver.findElements(By.cssSelector("[data-test='shopping-cart-badge']"));
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText().trim());
    }

    public void openCart() {
        clickWithJavaScript(shoppingCartLink);
    }

    private void addProductToCart(String productName) {
        WebElement product = productByName(productName);
        List<WebElement> addButtons = children(product, By.cssSelector("button[data-test^='add-to-cart']"));

        if (!addButtons.isEmpty()) {
            clickWithJavaScript(addButtons.get(0));
        }
    }

    private void waitUntilCartCountIs(final int expectedCount) {
        waitUntil(driver -> cartItemCount() == expectedCount);
    }

    private WebElement productByName(String productName) {
        waitUntilListIsNotEmpty(inventoryItems);
        for (WebElement item : inventoryItems) {
            String actualName = child(item, By.cssSelector("[data-test='inventory-item-name']")).getText().trim();
            if (productName.equals(actualName)) {
                return item;
            }
        }
        throw new NoSuchElementException("Product was not found in inventory: " + productName);
    }
}
