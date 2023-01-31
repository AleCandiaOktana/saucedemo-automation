package saucedemo.stepdefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import saucedemo.steps.SaucedemoUser;

public class Navigation {
    @Steps(shared = true)
    SaucedemoUser user;

    @Given("^user navigates to the saucedemo page$")
    public void userNavgatesToWebsite() {
        user.navigateToSaucedemo();
    }

    @Given("^user logs in with a standard (.*) and (.*)$")
    public void userLogsIn(String username, String password) {
        user.logsIn(username, password);
    }

    @When("^user navigates to the about page$")
    public void userNavigatesToAbout() {
        user.navigateToAboutPage();
    }

    @Then("^user should be in the saucelabs page$")
    public void validateUserIsInSaucelabs() {
        String currentUrl = user.getCurrentPageUrl();
        Assert.assertTrue(currentUrl.contains("saucelabs.com"));
    }

    @When("^user navigates back$")
    public void navigateBackToSauceLabs() {
        user.goBack();
    }

    @Then("^user should be back in the all products page$")
    public void validateUserIsBackInSaucedemo() {
        String currentUrl = user.getCurrentPageUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com"));
    }
}
