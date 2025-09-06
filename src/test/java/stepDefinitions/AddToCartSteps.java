package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class AddToCartSteps {

    WebDriver driver = DriverFactory.getDriver();
    ProductsPage productsPage = new ProductsPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    @Given("the user is logged in with username {string} and password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        loginPage.login(username, password);
    }

    @When("the user adds {string} to the cart")
    public void the_user_adds_item_to_the_cart(String productName) {
        productsPage.addProductToCart(productName);
    }

    @When("the user adds {string} and {string} to the cart")
    public void the_user_adds_two_items_to_the_cart(String item1, String item2) {
        productsPage.addProductToCart(item1);
        productsPage.addProductToCart(item2);
    }

    @Then("the cart should contain {int} item")
    public void the_cart_should_contain_items(Integer count) {
        Assert.assertEquals(productsPage.getCartCount(), count.intValue());
    }
}
