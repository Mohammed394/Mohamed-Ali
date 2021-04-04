package tests;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.sun.jndi.toolkit.url.Uri;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.PageBase;
import utilites.Helper;


public class TestBase {

    //For Initialize the Driver

    public static WebDriver driver;
    Capabilities chromeCapabilities = DesiredCapabilities.chrome();
    Capabilities firefoxCapabilities = DesiredCapabilities.firefox();

    //@BeforeSuite(alwaysRun = true)
    @Parameters({"browser"})
    public void StartDriver(@Optional("chrome") String browserName) throws IOException {

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--use-fake-ui-for-media-stream");
            driver = new ChromeDriver(options);


        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to("https://www.google.com/?hl=en");


        //For Docker
//		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setBrowserName(BrowserType.CHROME);
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
//		driver.navigate().to("https://www.google.com/?hl=en");

    }


    @BeforeMethod(alwaysRun = true)
    public void restart() throws IOException {
        StartDriver("chrome");
    }

    @AfterMethod(alwaysRun = true)
    public void quit(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenshot(driver, result.getName());
        }

        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        Runtime.getRuntime().exec("allure serve target allure-results");
    }


}
