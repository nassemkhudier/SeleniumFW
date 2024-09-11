package Pages;

import org.openqa.selenium.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    static WebDriver driver;
    private final By productDiscount = By.xpath("//span[@data-a-strike='true']");
    private final By FirstResultTitle = By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])[1]");
    private final By FirstItem = By.xpath("(//div[@class='a-section aok-relative s-image-fixed-height'])[1]");
    private final By ProductPrice = By.xpath("//span[@class='a-price-whole']");

    private final By Product_Price = By.xpath("//span[@class='a-price']//span[@class='a-offscreen']");
    private final By addToCart = By.id("add-to-cart-button");
    private final By max_price = By.name("high-price");
    private By filterUnder25 = By.linkText("Under $25");
    private By filter50to100$ = By.linkText("$50 to $100");

    private final By min_price = By.name("low-price");
    private final By applyPriceFilter = By.id("a-autoid-1");
    private final By resultPriceLocator = By.xpath("//span[@class='a-price-whole']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstResultTitle() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver.findElement(FirstResultTitle).getText();
    }

    public void selectFirstItem() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(FirstItem).click();
    }

    public String getProductPrice() {
        return driver.findElement(ProductPrice).getText();
    }

    public void addToCart() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(addToCart).click();
    }

    public void select_maximum_of_price(String max_price) {
        driver.findElement(this.max_price).sendKeys(max_price);

    }

    public void select_minimum_of_price(String min_price) {
        driver.findElement(this.min_price).sendKeys(min_price);

    }

    public void applyUnder25Filter() {
        driver.findElement(filterUnder25).click();
    }

    public void applyFilter50to100$() {
        driver.findElement(filter50to100$).click();
    }

    public WebElement verify_filter() {
        WebElement AnyPrice = driver.findElement(By.partialLinkText("Any Price"));
        return AnyPrice;
    }

    public boolean verifyPricesUnderprice(double specific_price) {
        for (WebElement priceElement : driver.findElements(resultPriceLocator)) {
            String priceText = priceElement.getText().replace("$", "");
            if (priceText.isEmpty() || !priceText.matches("\\d+(\\.\\d+)?")) {
                continue;
            }

            double price = Double.parseDouble(priceText);
            if (price > specific_price) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyPrices(double min_price, double max_price) {
        for (WebElement priceElement : driver.findElements(resultPriceLocator)) {
            String priceText = priceElement.getText().replace("$", "");
            if (priceText.isEmpty() || !priceText.matches("\\d+(\\.\\d+)?")) {
                continue;
            }

            double price = Double.parseDouble(priceText);
            if (price < min_price || price > max_price) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllItemsDiscounted() {
        List<WebElement> priceElements = driver.findElements(Product_Price);
        for (WebElement priceElement : priceElements) {
            WebElement parentProduct = priceElement.findElement(By.xpath("./ancestor::div[1]"));
            List<WebElement> discountElements = parentProduct.findElements(productDiscount);
            if (discountElements.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void selectAndVerifyDiscountedItems() {
        List<WebElement> discountedItems = driver.findElements(productDiscount);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        for (int i = 0; i < discountedItems.size(); i++) {
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                WebElement searchResultsPriceElement = discountedItems.get(i).findElement(Product_Price);
                String searchResultsPrice = searchResultsPriceElement.getText().trim();
                System.out.println(searchResultsPrice);
                discountedItems.get(i).click();
                WebElement detailsPagePriceElement = driver.findElement(By.xpath("//span[@class='a-offscreen']"));
                System.out.println(detailsPagePriceElement);
                String detailsPagePrice = detailsPagePriceElement.getText().trim();
                Assert.assertEquals(detailsPagePrice, searchResultsPrice, "Price mismatch for the discounted item");
                driver.navigate().back();
            } catch (StaleElementReferenceException e) {
                discountedItems = driver.findElements(productDiscount);
                i--;
            }
        }
    }
}