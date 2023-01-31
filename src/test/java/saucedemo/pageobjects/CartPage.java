package saucedemo.pageobjects;

import java.time.Duration;
import java.util.List;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CartPage extends PageObject {
    static final String XPATH_PRODUCTS = "//div[@class = 'cart_item']";

    @FindBy(id = "checkout")
    WebElementFacade checkoutBtn;

    public void clickCheckoutBtn() {
        this.checkoutBtn.click();
    }

    public String getSelectedProductsInfo() {
        String productDescriptions = "Selected Products\n\n";

        List<WebElementFacade> productButtons = withTimeoutOf(Duration.ofSeconds(10))
            .findAll(By.xpath(XPATH_PRODUCTS));

        for (WebElementFacade product : productButtons) {
            String prodText = product.getText();

            // Strip the 'REMOVE' from the button text at the end
            productDescriptions += "Quantity: " + prodText.substring(0, prodText.length() - 6) + '\n';
        }

        return (productButtons.size() > 0) ? productDescriptions : "No products selected";
    }
}
