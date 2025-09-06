package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartTitle = By.className("title");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartTitle() {
        return driver.findElement(cartTitle).getText();
    }

    public boolean isProductInCart(String productName) {
        return driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")).size() > 0;
    }

    public void removeItem(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button")).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
