package saucedemo.stepdefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import saucedemo.steps.SaucedemoUser;

public class BuyingProcess {
    @Steps(shared = true)
    private SaucedemoUser user;

    @Given("^user logs in with as the standard_user$")
    public void userLogsInAsStandardUser() {
        user.loginWithStandardUser();
    }

    @When("^user order products from lower to higher price$")
    public void orderProductsFromLowerToHigherPrice() {
        user.orderProductsFromLowerToHigherPrice();
    }

    @When("^user chooses the four most expensive products$")
    public void chooseFourMostExpensiveProducts() {
        user.chooseLastNItems(4);
    }

    @Then("^user sees a 4 on the cart icon$")
    public void validateCartShowsFourItems() {
        String numberInCartImg = user.getNumberShownInCart();
        Assert.assertEquals("4", numberInCartImg);
    }

    @When("user goes to his cart")
    public void userGoesToHisCart() {
        user.goToMyCart();

        String selectedProductsInfo = user.getSelectedProductsInfo();
        Serenity.recordReportData().withTitle("Chosen Products Report").andContents(selectedProductsInfo);
    }

    @When("user does checkout")
    public void userDoesCheckout() {
        user.goToCheckout();
        user.fillCheckoutInfoAndConfirmPurchase();
    }

    @When("user comfirms the purchase")
    public void userComfirmsThePurchase() {
        // Get the computed price and the one displayed on the page
        Double computedTotalPrice = user.computeTotalPrice();
        Double pageTotalPrice = user.getTotalPrice();

        // Validate the prices coincide
        Assert.assertEquals(computedTotalPrice, pageTotalPrice);

        String purchaseInformation = user.getPurchaseInformation();
        Serenity.recordReportData().withTitle("Payment, Shipping and Pricing Information").andContents(purchaseInformation);

        user.confirmPurhcase();
    }

    @Then("user sees a success message")
    public void userSeesASuccessMessage() {
        String message = user.getSuccessMessage();
        Assert.assertTrue(message.equalsIgnoreCase("THANK YOU FOR YOUR ORDER"));
    }
}
