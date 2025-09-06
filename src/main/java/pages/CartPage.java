package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By removeButtons = By.cssSelector("button.cart_button");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getCartItemNames() {
        return driver.findElements(cartItems).stream()
                     .map(item -> item.findElement(By.className("inventory_item_name")).getText())
                     .collect(Collectors.toList());
    }

    public void removeItemFromCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                item.findElement(removeButtons).click();
                return;
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public boolean isCartEmpty() {
        return getElementCount(cartItems) == 0;
    }

    public CheckoutPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
