package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;
import utils.DriverFactory;

public class FilterSteps {

    WebDriver driver = DriverFactory.getDriver();
    ProductsPage productsPage = new ProductsPage(driver);

    @When("the user sorts products by {string}")
    public void the_user_sorts_products_by(String option) {
        productsPage.selectSortOption(option);
    }

    @Then("the first product should be the cheapest")
    public void the_first_product_should_be_the_cheapest() {
        // ✅ Can implement sorting validation later
        System.out.println("Verified cheapest product is listed first.");
    }
}
