package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should be on the Products page")
    public void the_user_should_be_on_the_products_page() {
        Assert.assertTrue(productsPage.getPageTitle().contains("Products"));
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertTrue(loginPage.getErrorMessage().length() > 0);
    }
}
