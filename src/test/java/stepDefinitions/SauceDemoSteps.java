package stepDefinitions;



import io.cucumber.java.en.*;

import io.cucumber.java.After;

import io.cucumber.java.Before;

import io.cucumber.java.Scenario;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

import pages.LoginPage;

import utils.ConfigReader;

import utils.DriverFactory;

import utils.ScreenshotUtil;



import java.util.List;



public class SauceDemoSteps {

    private WebDriver driver;

    private LoginPage loginPage;



    // Setup: initialize driver and page objects before each scenario

    @Before

    public void setup() {

        driver = DriverFactory.initDriver(ConfigReader.get("browser"));

        loginPage = new LoginPage(driver);

    }



    // Tear down: capture screenshot if scenario fails and close driver

    @After

    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            ScreenshotUtil.takeScreenshot(driver, "failure-" + System.currentTimeMillis());

        }

        DriverFactory.quitDriver();

    }



    // Test Case 1: Open SauceDemo login page

    @Given("I open the saucedemo login page")

    public void i_open_the_saucedemo_login_page() {

        driver.get(ConfigReader.get("baseUrl"));

    }



    // Test Case 2: Enter valid/invalid username and password

    @When("I login with username {string} and password {string}")

    public void i_login_with_username_and_password(String user, String pass) {

        loginPage.enterUsername(user);

        loginPage.enterPassword(pass);

        loginPage.clickLogin();

    }



    // Test Case 3: Verify successful login redirects to products page

    @Then("I should land on the products page")

    public void i_should_land_on_the_products_page() {

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

    }



    // Test Case 4: Verify invalid login shows error message

    @Then("I should see an error message {string}")

    public void i_should_see_an_error_message(String message) {

        String actual = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertTrue(actual.contains(message));

    }



    // Test Case 5: Precondition - login as a specific user

    @Given("I am logged in as {string} with password {string}")

    public void i_am_logged_in_as_with_password(String user, String pass) {

        driver.get(ConfigReader.get("baseUrl"));

        loginPage.enterUsername(user);

        loginPage.enterPassword(pass);

        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

    }



    // Test Case 6: Logout from menu

    @When("I logout from the menu")

    public void i_logout_from_the_menu() {

        driver.findElement(By.id("react-burger-menu-btn")).click();

        driver.findElement(By.id("logout_sidebar_link")).click();

    }



    // Test Case 7: Verify user is redirected back to login page after logout

    @Then("I should be redirected to login page")

    public void i_should_be_redirected_to_login_page() {

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));

    }



    // Test Case 8: Add product to cart

    @When("I add product {string} to the cart")

    public void i_add_product_to_the_cart(String product) {

        driver.findElement(By.xpath("//div[text()='" + product + "']/ancestor::div[@class='inventory_item']//button")).click();

    }



    // Test Case 9: Remove product from cart

    @When("I remove product {string} from the cart")

    public void i_remove_product_from_the_cart(String product) {

        driver.findElement(By.xpath("//div[text()='" + product + "']/ancestor::div[@class='inventory_item']//button")).click();

    }



    // Test Case 10: Verify cart badge shows correct product count

    @Then("the cart badge should show {string}")

    public void the_cart_badge_should_show(String count) {

        String actual = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(actual, count);

    }



    // Test Case 11: Verify cart badge disappears when no products are in cart

    @Then("the cart badge should not be visible")

    public void the_cart_badge_should_not_be_visible() {

        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));

        Assert.assertTrue(badges.size() == 0);

    }



    // Test Case 12: Complete checkout flow with user details

    @When("I checkout with first name {string} last name {string} postal code {string}")

    public void i_checkout_with_first_name_last_name_postal_code(String fn, String ln, String pc) {

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys(fn);

        driver.findElement(By.id("last-name")).sendKeys(ln);

        driver.findElement(By.id("postal-code")).sendKeys(pc);

        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();

    }



    // Test Case 13: Verify confirmation message after checkout

    @Then("I should see a confirmation message {string}")

    public void i_should_see_a_confirmation_message(String msg) {

        String actual = driver.findElement(By.className("complete-header")).getText();

        Assert.assertTrue(actual.contains(msg));

    }



    // Test Case 14: Sort products by option (e.g., Price: Low to High)

    @When("I sort products by {string}")

    public void i_sort_products_by(String option) {

        driver.findElement(By.cssSelector("select.product_sort_container")).sendKeys(option);

    }



    // Test Case 15: Verify first product is cheapest when sorted low to high

    @Then("the first product price should be lowest")

    public void the_first_product_price_should_be_lowest() {

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));

        double first = Double.parseDouble(prices.get(0).getText().replace("$", ""));

        double last = Double.parseDouble(prices.get(prices.size() - 1).getText().replace("$", ""));

        Assert.assertTrue(first <= last);

    }



    // Test Case 16: Open product details page

    @When("I open the product details for {string}")

    public void i_open_the_product_details_for(String product) {

        driver.findElement(By.xpath("//div[text()='" + product + "']")).click();

    }



    // Test Case 17: Verify product detail page shows correct title

    @Then("the product page should show title {string}")

    public void the_product_page_should_show_title(String title) {

        String actual = driver.findElement(By.className("inventory_details_name")).getText();

        Assert.assertEquals(actual, title);

    }

}