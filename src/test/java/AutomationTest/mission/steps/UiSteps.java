package AutomationTest.mission.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import AutomationTest.mission.driver.BrowserSetup;
import AutomationTest.mission.pages.CartPage;
import AutomationTest.mission.pages.CheckoutInformationPage;
import AutomationTest.mission.pages.CheckoutOverviewPage;
import AutomationTest.mission.pages.InventoryPage;
import AutomationTest.mission.pages.LoginPage;
import AutomationTest.mission.utils.MoneyUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UiSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    private void log(String message) {
        System.out.println("[UI] " + message);
    }

    @Given("^I am on the home page$")
    public void openHomePage() {
        WebDriver driver = BrowserSetup.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.open();
        log("Opened home page: " + driver.getCurrentUrl());
    }

    @And("^I login in with the following details$")
    public void loginWithDetails(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMaps(String.class, String.class).get(0);
        loginPage.login(credentials.get("userName"), credentials.get("Password"));

        inventoryPage = new InventoryPage(BrowserSetup.getDriver());
        inventoryPage.waitUntilLoaded();

        log("Logged in as: " + credentials.get("userName"));
    }

    @And("^I add the following items to the basket$")
    public void addItemsToBasket(DataTable dataTable) {
        List<String> productNames = dataTable.asList(String.class);
        inventoryPage.addProductsToCart(productNames);

        log("Added to cart: " + productNames);
    }

    @And("^I\\s+should see (\\d+) items added to the shopping cart$")
    public void shoppingCartCountShouldBe(int expectedCount) {

        int actualCount = cartPage != null
                ? cartPage.cartItemCount()
                : inventoryPage.cartItemCount();

        log("Shopping cart count: " + actualCount +
                " (expected " + expectedCount + ")");

        Assert.assertEquals(actualCount, expectedCount,
                "Shopping cart count should match.");
    }

    @And("^I click on the shopping cart$")
    public void openShoppingCart() {

        inventoryPage.openCart();

        cartPage = new CartPage(BrowserSetup.getDriver());
        cartPage.waitUntilLoaded();

        log("Opened shopping cart");
    }

    @And("^I verify that the QTY count for each item should be (\\d+)$")
    public void quantityForEachItemShouldBe(int expectedQuantity) {

        List<Integer> quantities = cartPage.itemQuantities();

        log("Cart item quantities: " + quantities +
                " (expected each to be " + expectedQuantity + ")");

        for (Integer quantity : quantities) {
            Assert.assertEquals(quantity.intValue(),
                    expectedQuantity,
                    "Cart item quantity should match.");
        }
    }

    @And("^I remove the following item:$")
    public void removeItems(DataTable dataTable) {

        List<String> itemsToRemove = dataTable.asList(String.class);

        cartPage.removeItems(itemsToRemove);

        log("Removed from cart: " + itemsToRemove);
    }

    @And("^I click on the CHECKOUT button$")
    public void clickCheckout() {

        cartPage.checkout();

        checkoutInformationPage =
                new CheckoutInformationPage(BrowserSetup.getDriver());

        checkoutInformationPage.waitUntilLoaded();

        log("Navigated to checkout information page");
    }

    @And("^I type \"([^\"]*)\" for First Name$")
    public void typeFirstName(String firstName) {

        checkoutInformationPage.enterFirstName(firstName);

        log("Entered First Name: " + firstName);
    }

    @And("^I type \"([^\"]*)\" for Last Name$")
    public void typeLastName(String lastName) {

        checkoutInformationPage.enterLastName(lastName);

        log("Entered Last Name: " + lastName);
    }

    @And("^I type \"([^\"]*)\" for ZIP/Postal Code$")
    public void typePostalCode(String postalCode) {

        checkoutInformationPage.enterPostalCode(postalCode);

        log("Entered ZIP/Postal Code: " + postalCode);
    }

    @When("^I click on the CONTINUE button$")
    public void continueToOverview() {

        checkoutInformationPage.continueToOverview();

        checkoutOverviewPage =
                new CheckoutOverviewPage(BrowserSetup.getDriver());

        checkoutOverviewPage.waitUntilLoaded();

        log("Navigated to checkout overview page");
    }

    @Then("^Item total will be equal to the total of items on the list$")
    public void itemTotalShouldMatchListedItems() {

        List<BigDecimal> listedPrices =
                checkoutOverviewPage.listedItemPrices();

        BigDecimal expectedTotal = MoneyUtils.sum(listedPrices);
        BigDecimal actualTotal = checkoutOverviewPage.displayedItemTotal();

        log("Listed item prices: " + listedPrices +
                ", expected total: " + expectedTotal +
                ", displayed total: " + actualTotal);

        Assert.assertEquals(actualTotal,
                expectedTotal,
                "Displayed item total should equal listed item prices.");
    }

    @And("^a Tax rate of (\\d+) % is applied to the total$")
    public void taxRateShouldBeApplied(int taxRate) {

        BigDecimal itemTotal = checkoutOverviewPage.displayedItemTotal();

        BigDecimal expectedTax =
                MoneyUtils.percentageOf(itemTotal, taxRate);

        BigDecimal actualTax =
                checkoutOverviewPage.displayedTax();

        BigDecimal expectedGrandTotal =
                itemTotal.add(expectedTax).setScale(2);

        BigDecimal actualGrandTotal =
                checkoutOverviewPage.displayedTotal();

        log("Item total: " + itemTotal +
                ", expected tax (" + taxRate + "%): " + expectedTax +
                ", displayed tax: " + actualTax +
                ", expected grand total: " + expectedGrandTotal +
                ", displayed grand total: " + actualGrandTotal);

        Assert.assertEquals(actualTax,
                expectedTax,
                "Displayed tax should match expected tax rate.");

        Assert.assertEquals(actualGrandTotal,
                expectedGrandTotal,
                "Grand total should include item total and tax.");
    }
}