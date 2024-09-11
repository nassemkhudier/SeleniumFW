import Utils.RetryAnalyzer;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TestAmazon extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    WebElement AnyPrice;
    String searchKeyword = file.get("Keyword").toString();

    @Test(groups = {"Regression"}, priority = 1, description = "Add item to cart and proceed to check out", retryAnalyzer = RetryAnalyzer.class)
    public void testCase1() {
        homePage.open();
        homePage.searchForProduct(searchKeyword);
        String firstResultTitle = productPage.getFirstResultTitle();
        System.out.println(firstResultTitle);
        assert firstResultTitle.toLowerCase().contains(searchKeyword.toLowerCase());
        productPage.selectFirstItem();
        String productPrice = productPage.getProductPrice();
        assert !productPrice.isEmpty();
        productPage.addToCart();
        String cartQuantity = cartPage.getCartQuantity();
        assert cartQuantity.equals("1");
        cartPage.proceedToCheckout();
        assert loginPage.isLoginPageDisplayed();
    }

    @Test(groups = {"Regression"}, priority = 2, description = "Verify the items under specific price", retryAnalyzer = RetryAnalyzer.class)
    public void testCase2() {
        homePage.open();
        homePage.searchForProduct(searchKeyword);
        String firstResultTitle = productPage.getFirstResultTitle();
        System.out.println(firstResultTitle);
        assert firstResultTitle.toLowerCase().contains(searchKeyword.toLowerCase());
        productPage.applyUnder25Filter();
        AnyPrice = productPage.verify_filter();
        softAssert.assertTrue(AnyPrice.isDisplayed());
        softAssert.assertTrue(productPage.verifyPricesUnderprice(25), "Not all items have prices under $25");
        softAssert.assertAll();
    }

    @Test(groups = {"Regression"}, priority = 3, description = "Verify the price filter", retryAnalyzer = RetryAnalyzer.class)
    public void testCase3() {
        homePage.open();
        homePage.searchForProduct(searchKeyword);
        String firstResultTitle = productPage.getFirstResultTitle();
        System.out.println(firstResultTitle);
        assert firstResultTitle.toLowerCase().contains(searchKeyword.toLowerCase());
        productPage.applyFilter50to100$();
        AnyPrice = productPage.verify_filter();
        softAssert.assertTrue(AnyPrice.isDisplayed());
        softAssert.assertTrue(productPage.verifyPrices(50, 100), "Not all items have prices between $50 and $100");
        softAssert.assertAll();
    }

    @Test(groups = "Regression", priority = 4, description = "Verify the items have a discount", retryAnalyzer = RetryAnalyzer.class)
    public void testcase4() {
        homePage.open();
        homePage.searchForProduct(searchKeyword);
        softAssert.assertTrue(productPage.areAllItemsDiscounted(), "Not all items have a discount.");
        softAssert.assertAll();
        productPage.selectAndVerifyDiscountedItems();
    }

}
