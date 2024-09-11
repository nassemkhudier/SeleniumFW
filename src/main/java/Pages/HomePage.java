package Pages;

import Utils.BaseClass;
import Utils.PropertiesConfigs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Properties;

public class HomePage extends BaseClass {

    WebDriver driver;
    PropertiesConfigs config = new PropertiesConfigs();
    Properties file = config.prop();
    private final By AmazonOption = By.xpath("//*[@class='d8lRkd']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(file.get("GoogleURL").toString());
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(AmazonOption).click();
        driver.navigate().refresh();
        driver.navigate().refresh();

    }

    public void searchForProduct(String keyword) {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(keyword);
        searchBox.submit();
    }
}
