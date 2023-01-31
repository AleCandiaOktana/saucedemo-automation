package saucedemo.steps;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;
import saucedemo.pageobjects.CartPage;
import saucedemo.pageobjects.CheckoutInfoPage;
import saucedemo.pageobjects.FinishPurchasePage;
import saucedemo.pageobjects.InventoryPage;
import saucedemo.pageobjects.InventoryPage.ProductOrder;
import saucedemo.pageobjects.LoginPage;

public class SaucedemoUser extends ScenarioActor {
    @Steps(shared = true)
    private LoginPage loginPage;

    @Steps(shared = true)
    private InventoryPage inventoryPage;

    @Steps(shared = true)
    private CartPage cartPage;

    @Steps(shared = true)
    private CheckoutInfoPage checkoutInfoPage;

    @Steps(shared = true)
    private FinishPurchasePage finishPurchasePage;

    /**
     * Login Page Activities
     */

    public void navigateToSaucedemo() {
        loginPage.setDefaultBaseUrl("https://saucedemo.com");
        loginPage.open();
    }

    public void logsIn(String username, String password) {
        loginPage.loginWithCredentials(username, password);
    }

    public void loginWithStandardUser() {
        loginPage.loginAsStandardUser();
    }

    /**
     * Inventory Page Activities
     */

    public void navigateToAboutPage() {
        inventoryPage.openSideMenuAndClickAbout();
    }

    public void orderProductsFromLowerToHigherPrice() {
        inventoryPage.orderProducts(ProductOrder.PRICE_LOWEST_FIRST);
    }

    public void chooseLastNItems(int i) {
        inventoryPage.selectLastNItems(i);
    }

    public void goToMyCart() {
        inventoryPage.clickCart();
    }

    public String getNumberShownInCart() {
        return inventoryPage.getCartNumber();
    }

    /**
     * Cart Page Activities
     */

    public void goToCheckout() {
        cartPage.clickCheckoutBtn();
    }

    public String getSelectedProductsInfo() {
        return cartPage.getSelectedProductsInfo();
    }

    /**
     * Checkout Info Page
     */

    public void fillCheckoutInfoAndConfirmPurchase() {
        checkoutInfoPage.fillCheckoutInfoAndFinish("testName", "testLastName", "testPostalCode");
    }

    /**
     * Finish Purhcase page
     */

    public void confirmPurhcase () {
        finishPurchasePage.clickFinishButton();
    }

    public Double computeTotalPrice() {
        return finishPurchasePage.aggregatePurchasedProducts();
    }

    public Double getTotalPrice() {
        return finishPurchasePage.getTotalPrice();
    }

    public String getPurchaseInformation() {
        return finishPurchasePage.getPurchaseInformation();
    }

    public String getSuccessMessage() {
        return finishPurchasePage.getCongratulationText();
    }

    /**
     * Browser Actions
     */

    public String getCurrentPageUrl() {
        return Serenity.getDriver().getCurrentUrl();
    }

    public void goBack() {
        Serenity.getDriver().navigate().back();
    }
}
