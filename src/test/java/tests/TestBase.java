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
import org.openqa.selenium.firefox.FirefoxOptions;
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
    enum configuration {
        chrome,
        firefox,
        chromeDocker,
        firefoxDocker
    }

    public static WebDriver driver;

    public void StartDriver() throws IOException {
        configuration config = getDesiredExecution();
        if (config == configuration.chrome) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--use-fake-ui-for-media-stream");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

        }
        if (config == configuration.firefox) {
            System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--use-fake-ui-for-media-stream");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();

        }else if (config == configuration.chromeDocker) {
            Runtime.getRuntime().exec("docker-compose up");
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        } else if (config == configuration.firefoxDocker) {
            Runtime.getRuntime().exec("docker-compose up");
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(BrowserType.FIREFOX);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        }
        driver.navigate().to("https://www.google.com/?hl=en");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @BeforeMethod(alwaysRun = true)
    public void restart() throws IOException {
        StartDriver();
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
    private configuration getDesiredExecution() {
        String configExecution = System.getProperty("env.os");
        switch (configExecution) {
            case "chrome":
                return configuration.chrome;
            case "firefox":
                return configuration.firefox;
            case "chromeDocker":
                return configuration.chromeDocker;
            case "firefoxDocker":
                return configuration.firefoxDocker;
            default:
                return null;
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        Runtime.getRuntime().exec("allure serve target allure-results");
    }


}
