import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utils.BaseClass;
import Utils.PropertiesConfigs;
import Utils.CustomWebdriverlistener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Properties;

public class TestBase extends BaseClass {

    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    LoginPage loginPage;
    PropertiesConfigs config = new PropertiesConfigs();
    Properties file = config.prop();

    @BeforeMethod(alwaysRun = true)
    public void BeforeMethod() {
        String headless = null;
        headless = file.get("headless").toString();
        if (headless.equals("true")) {
            System.out.println("Running in headless mode");
            driver = BaseClass.headlessChrome();
            CustomWebdriverlistener listener = new CustomWebdriverlistener();
            driver = new EventFiringDecorator(listener).decorate(driver);
        } else {
            driver = BaseClass.Chromedriver();
            CustomWebdriverlistener listener = new CustomWebdriverlistener();
            driver = new EventFiringDecorator(listener).decorate(driver);
        }

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        driver.quit();
    }

}
