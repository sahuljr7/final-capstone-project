package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private By productNames = By.className("inventory_item_name");
    private By addToCartButtons = By.cssSelector("button.btn_inventory");
    private By cartIcon = By.id("shopping_cart_container");
    private By filterDropdown = By.className("product_sort_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                     .stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }

    public void addProductToCart(String productName) {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> buttons = driver.findElements(addToCartButtons);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equalsIgnoreCase(productName)) {
                buttons.get(i).click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void filterProducts(String filterOption) {
        WebElement dropdown = driver.findElement(filterDropdown);
        dropdown.click();
        dropdown.findElement(By.xpath("//option[contains(text(),'" + filterOption + "')]")).click();
    }

    public boolean isProductDisplayed(String productName) {
        return getProductNames().contains(productName);
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }
}
