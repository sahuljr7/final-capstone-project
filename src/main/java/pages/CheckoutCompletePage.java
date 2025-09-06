package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;
    private By thankYouMessage = By.className("complete-header");
    private By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getThankYouMessage() {
        return driver.findElement(thankYouMessage).getText();
    }

    public ProductsPage clickBackHome() {
        driver.findElement(backHomeButton).click();
        return new ProductsPage(driver);
    }
}
