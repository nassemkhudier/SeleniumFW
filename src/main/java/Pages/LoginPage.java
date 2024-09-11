package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginPageDisplayed() {
        WebElement loginPageHeader = driver.findElement(By.id("ap_email"));
        return loginPageHeader.isDisplayed();
    }
}
