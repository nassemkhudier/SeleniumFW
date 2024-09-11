package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    static WebDriver driver;
    private final By CartQuantity = By.cssSelector(".a-dropdown-prompt");
    private final By proceedToCheckout = By.id("sc-buy-box-ptc-button");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartQuantity() {
        return driver.findElement(CartQuantity).getText();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckout).click();
    }
}
