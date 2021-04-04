package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class MapSearchPage extends PageBase {
    public MapSearchPage(WebDriver driver) {
        super(driver);
    }
    By mapsBtn = By.xpath(("//a[@class='hide-focus-ring']"));
    By mapResult = By.xpath("//a[@aria-label='Instabug']");
    By mapTxtSearch = By.xpath("//input[@aria-label='Search Google Maps']");

    public void assertOnLocation(WebDriver driver , String lat , String lon) throws InterruptedException, IOException {
        waitForElement(driver,mapsBtn);
        clickOnElement(driver,mapsBtn);
        selectElementInList(driver,mapResult,0);
        waitForElement(driver,mapTxtSearch);
        Thread.sleep(4000);
        String driverUrl = driver.getCurrentUrl();
        Assert.assertTrue(driverUrl.contains(lat));
        Assert.assertTrue(driverUrl.contains(lon));

    }
}
