package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By productsTitle = By.className("title");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortDropdown = By.className("product_sort_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public void addProductToCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void selectSortOption(String optionText) {
        driver.findElement(sortDropdown).sendKeys(optionText);
    }
}
