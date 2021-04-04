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

    private static org.testng.log4testng.Logger logger;
    //Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected WebDriver driver;
   // public Logger logger = Logger.getLogger("PageBase");

//    public static Logger logConfig() throws IOException {
//        InputStream fis = new FileInputStream("src/main/java/properties/logging.properties");
//        LogManager.getLogManager().readConfiguration(fis);
//        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//        ConsoleHandler handler = new ConsoleHandler();
//        CustomFormatter formatter = new CustomFormatter();
//        logger.setUseParentHandlers(false);
//        handler.setLevel(Level.ALL);
//        handler.setFormatter(formatter);
//        logger.addHandler(handler);
//       // logger = Logger.getLogger(formatter);
//
//        return logger;
//    }


    //Create Constructor
    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    protected static void clickOnElement(WebDriver driver, By element) throws IOException {
        driver.findElement(element).click();
        System.out.println(element+" is clicked");

    }

    protected static void sendText(WebDriver driver, By element, String value) {
        driver.findElement(element).sendKeys(value);
        System.out.println(element+" is typed");
    }

    protected static String getElementText(WebDriver driver, By element) {
        return driver.findElement(element).getText();
    }

    protected static Boolean waitForElement(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println(element+" is appeared");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected static Boolean waitForElements(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
            System.out.println("Element is exist");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void selectElementInList(WebDriver driver, By element, int order) {
        List<WebElement> li = driver.findElements(element);
        li.get(order).click();
        System.out.println(element+" is clicked");

    }

    public void goBack(WebDriver driver) {
        driver.navigate().back();
    }
}
