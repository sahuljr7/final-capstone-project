package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class RemoveFromCartSteps {

    WebDriver driver = DriverFactory.getDriver();
    ProductsPage productsPage = new ProductsPage(driver);
    CartPage cartPage = new CartPage(driver);

    @Given("the user has items in the cart")
    public void the_user_has_items_in_the_cart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
    }

    @When("the user removes {string} from the cart")
    public void the_user_removes_item_from_the_cart(String productName) {
        productsPage.removeProductFromCart(productName);
    }

    @Then("the cart should not contain {string}")
    public void the_cart_should_not_contain(String productName) {
        Assert.assertFalse(cartPage.isProductInCart(productName));
    }
}
