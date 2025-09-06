package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    ProductsPage productsPage = new ProductsPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Given("the user has added items to the cart")
    public void the_user_has_added_items_to_the_cart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @When("the user enters first name {string}, last name {string}, and postal code {string}")
    public void the_user_enters_checkout_information(String first, String last, String postal) {
        checkoutPage.enterCheckoutInformation(first, last, postal);
        checkoutPage.clickContinue();
    }

    @When("the user completes the purchase")
    public void the_user_completes_the_purchase() {
        checkoutPage.completeOrder();
    }

    @Then("the order confirmation should be displayed")
    public void the_order_confirmation_should_be_displayed() {
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("Thank you"));
    }
}
