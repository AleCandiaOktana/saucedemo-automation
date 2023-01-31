package saucedemo.pageobjects;

import java.time.Duration;
import java.util.List;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class InventoryPage extends PageObject {
    public enum ProductOrder {
        NAME_A_TO_Z,
        NAME_Z_TO_A,
        PRICE_HIGHEST_FIRST,
        PRICE_LOWEST_FIRST
    }

    static final String XPATH_ADD_TO_CART_BTN = "//button[@class = 'btn btn_primary btn_small btn_inventory']";

    @FindBy(id = "react-burger-menu-btn")
    WebElementFacade menuBtn;

    @FindBy(id = "about_sidebar_link")
    WebElementFacade aboutLink;

    @FindBy(id = "shopping_cart_container")
    WebElementFacade cartBtn;

    @FindBy(xpath = "//span[@class = 'shopping_cart_badge']")
    WebElementFacade cartNumber;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElementFacade productFilter;

    public void openSideMenu() {
        this.menuBtn.waitUntilClickable().click();
    }

    public void openSideMenuAndClickAbout() {
        openSideMenu();
        this.aboutLink.waitUntilClickable().click();
    }

    public void orderProducts(ProductOrder order) {
        switch (order) {
            case NAME_A_TO_Z:
                this.productFilter.selectByValue("az");
                break;
            case NAME_Z_TO_A:
                this.productFilter.selectByValue("za");
                break;
            case PRICE_LOWEST_FIRST:
                this.productFilter.selectByValue("lohi");
                break;
            case PRICE_HIGHEST_FIRST:
                this.productFilter.selectByValue("hilo");
                break;
            default:
                break;
        }
    }

    /**
     * Add {@code productsToGather} of products to the cart from bottom to top.
     * 
     * @param productsToGather the number of products to gather (if it is larger
     *                         than the number of products available returns all
     *                         products)
     */
    public void selectLastNItems(int productsToGather) {
        List<WebElementFacade> productButtons = withTimeoutOf(Duration.ofSeconds(10))
                .findAll(By.xpath(XPATH_ADD_TO_CART_BTN));

        int gatheredProducts = 0;
        for (int i = productButtons.size() - 1; i >= 0; i--) {
            if (gatheredProducts < productsToGather) {
                productButtons.get(i).click();
                gatheredProducts++;
            }

            else
                return;
        }
    }

    public void clickCart() {
        this.cartBtn.click();
    }

    public String getCartNumber() {
        return this.cartBtn.getText();
    }
}
