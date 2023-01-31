package saucedemo.pageobjects;

import java.time.Duration;
import java.util.List;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Represents the checkout two step (finish purchase) and the succes message page
 */
public class FinishPurchasePage extends PageObject {
    /**
     * Checkout step 2 elements
     */

    private static final String XPATH_PRICES = "//div[@class = 'inventory_item_price']";
    private static final String XPATH_PURCHASE_INFO = "//div[contains(@class,'summary') and contains(@class, 'label')]";

    @FindBy(id = "finish")
    WebElementFacade finishBtn;

    @FindBy(xpath = "//div[@class = 'summary_subtotal_label']")
    WebElementFacade itemTotal;

    /**
     * Succes message page elements
     */

    @FindBy(xpath = "//h2[contains(text(), 'THANK YOU FOR YOUR ORDER')]")
    WebElementFacade thankYouMessageText;

    /**
     * Checkout step 2 functionality
     */
    public void clickFinishButton() {
        this.finishBtn.click();
    }

    public Double aggregatePurchasedProducts() {
        Double totalPrice = 0.0;

        List<WebElementFacade> productButtons = withTimeoutOf(Duration.ofSeconds(10))
            .findAll(By.xpath(XPATH_PRICES));

        for (WebElementFacade product : productButtons) {
            String price = product.getText();

            // Strip the 'REMOVE' from the button text at the end
            totalPrice += Double.parseDouble(price.replace("$", ""));
        }

        return totalPrice;
    }

    public Double getTotalPrice() {
        return Double.parseDouble(this.itemTotal.getText().replace("Item total: $", ""));
    }

    public String getPurchaseInformation() {
        String purchaseInfo = "";

        List<WebElementFacade> productButtons = withTimeoutOf(Duration.ofSeconds(10))
            .findAll(By.xpath(XPATH_PURCHASE_INFO));

        for (WebElementFacade prod : productButtons) {
            purchaseInfo += prod.getText() + '\n';
        }

        return purchaseInfo;
    }

    /**
     * Succes message page functionality
     */
    public String getCongratulationText() {
        return this.thankYouMessageText.waitUntilVisible().getText();
    }

}
