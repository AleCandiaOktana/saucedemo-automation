package saucedemo.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
// import net.thucydides.core.annotations.DefaultUrl;

// @DefaultUrl("https://saucedemo.com")
public class LoginPage extends PageObject {
    @FindBy(id = "user-name")
    WebElementFacade usernameInpt;

    @FindBy(id = "password")
    WebElementFacade passwordInpt;

    @FindBy(id = "login-button")
    WebElementFacade loginBtn;

    public void loginWithCredentials(String username, String password) {
        this.usernameInpt.sendKeys(username);
        this.passwordInpt.sendKeys(password);
        this.loginBtn.click();
    }

    public void loginAsStandardUser() {
        this.usernameInpt.sendKeys("standard_user");
        this.passwordInpt.sendKeys("secret_sauce");
        this.loginBtn.click();
    }
}
