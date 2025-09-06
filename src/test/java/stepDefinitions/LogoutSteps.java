package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.MenuPage;
import utils.DriverFactory;

public class LogoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    MenuPage menuPage = new MenuPage(driver);

    @When("the user logs out")
    public void the_user_logs_out() {
        menuPage.openMenu();
        menuPage.logout();
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
