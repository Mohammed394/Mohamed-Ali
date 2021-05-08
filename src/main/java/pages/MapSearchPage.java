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

    public void assertOnLocation(WebDriver driver, String lat, String lon) throws  IOException {
        waitForElement(driver, mapsBtn);
        clickOnElement(driver, mapsBtn);
        selectElementInList(driver, mapResult, 0);
        Assert.assertTrue(waitUrl(driver, lat));
        Assert.assertTrue(waitUrl(driver, lon));
    }
}
