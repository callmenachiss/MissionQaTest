package AutomationTest.mission.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "[data-test='inventory-item']")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitUntilUrlContains("cart");
        visible(checkoutButton);
    }

    public List<Integer> itemQuantities() {
        waitUntilListIsNotEmpty(cartItems);
        List<Integer> quantities = new ArrayList<Integer>();
        for (WebElement item : cartItems) {
            String quantity = child(item, By.cssSelector("[data-test='item-quantity']")).getText().trim();
            quantities.add(Integer.parseInt(quantity));
        }
        return quantities;
    }

    public void removeItems(List<String> productNames) {
        for (String productName : productNames) {
            WebElement product = productByName(productName);
            clickWithJavaScript(child(product, By.cssSelector("button[data-test^='remove']")));
        }
    }

    public int cartItemCount() {
        List<WebElement> badges = driver.findElements(By.cssSelector("[data-test='shopping-cart-badge']"));
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText().trim());
    }

    public void checkout() {
        clickWithJavaScript(checkoutButton);
    }

    private WebElement productByName(String productName) {
        waitUntilListIsNotEmpty(cartItems);
        for (WebElement item : cartItems) {
            String actualName = child(item, By.cssSelector("[data-test='inventory-item-name']")).getText().trim();
            if (productName.equals(actualName)) {
                return item;
            }
        }
        throw new NoSuchElementException("Product was not found in cart: " + productName);
    }
}
