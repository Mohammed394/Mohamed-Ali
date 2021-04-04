package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pages.PageBase;


public class TestBase {

	//For Initialize the Driver

	public static WebDriver driver;
	PageBase pageBase;
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browser"})
	public void StartDriver(@Optional("chrome") String browserName) throws IOException {

		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--use-fake-ui-for-media-stream");
			driver = new ChromeDriver(options);
		}


		else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		/// if headless


//		else if(browserName.equalsIgnoreCase("headless"))
//		{
//			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setJavascriptEnabled(true);
//			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir")+"\\Drivers\\phantomjs.exe");
//			String [] phantomJsArgs = {"--web-security=no","--ignore-ssl-errors=yes"};
//			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomJsArgs);
//
//			driver = new PhantomJSDriver(caps);
//		}
		
		//chrome headless
		else if(browserName.equalsIgnoreCase("chrome-headless")) 
		{
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(options);
			
		}


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to("https://www.google.com/?hl=en");

	}







	@AfterClass
	public void StopDriver() throws IOException {
		//Runtime.getRuntime().exec("allure serve target allure-results");
//		JavascriptExecutor JS = (JavascriptExecutor) driver;
//		JS.executeScript("alert('Test Completed and will close after 3 seconds')");
//		Thread.sleep(6000);
		driver.quit();
	}




}
