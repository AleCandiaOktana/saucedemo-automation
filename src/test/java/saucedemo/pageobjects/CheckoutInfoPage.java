package saucedemo.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CheckoutInfoPage extends PageObject {
    @FindBy(id = "first-name")
    WebElementFacade firstNameInpt;

    @FindBy(id = "last-name")
    WebElementFacade lastNameInpt;

    @FindBy(id = "postal-code")
    WebElementFacade postalCodeInpt;

    @FindBy(id = "continue")
    WebElementFacade continueBtn;

    public void fillCheckoutInfoAndFinish(String firstName, String lastName, String postalCode) {
        this.firstNameInpt.sendKeys(firstName);
        this.lastNameInpt.sendKeys(lastName);
        this.postalCodeInpt.sendKeys(postalCode);

        this.continueBtn.click();
    }

}
