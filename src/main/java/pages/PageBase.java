package pages;


import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class PageBase {


    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    protected static void clickOnElement(WebDriver driver, By element) throws IOException {
        driver.findElement(element).click();
        System.out.println(element + " is clicked");
    }

    protected static void sendText(WebDriver driver, By element, String value) {
        driver.findElement(element).sendKeys(value);
        System.out.println(element + " is typed");
    }

    protected static String getElementText(WebDriver driver, By element) {
        return driver.findElement(element).getText();
    }

    protected static Boolean waitForElement(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println(element + " is appeared");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected static Boolean waitUrl(WebDriver driver, String word) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.urlContains(word));
            System.out.println(word + " is appeared");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void selectElementInList(WebDriver driver, By element, int order) {
        List<WebElement> li = driver.findElements(element);
        li.get(order).click();
        System.out.println(element + " is clicked");
    }

    public void goBack(WebDriver driver) {
        driver.navigate().back();
    }
}
