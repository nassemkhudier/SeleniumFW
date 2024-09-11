package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class CustomWebdriverlistener implements WebDriverListener {
    static WebDriver driver;
    private final String screenshots_path = System.getProperty("user.dir") + File.separator + ("failure_screenshots");

    public void beforeFindElement(WebDriver driver, By locator) {
        this.driver = driver;
    }


    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("====== After find element >>> " + result + "======");
    }

    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        Random random = new Random();
        int randomNumber = random.nextInt(10) + 1;
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String name_image = screenshots_path + File.separator + method.getName() + randomNumber + ".png";
        try {
            FileUtils.copyFile(file, new File(name_image));
        } catch (IOException a) {
            throw new RuntimeException(a);
        }
    }
}
