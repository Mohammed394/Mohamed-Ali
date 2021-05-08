package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import properties.SpeakUtil;

import java.io.IOException;
import java.util.List;

public class SearchPage extends PageBase {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    By searchTxt = By.xpath("//input[@class='gLFyf gsfi']");
    By searchResult = By.tagName("span");
    By voiceBtn  = By.xpath("//div[@aria-label='Search by voice']");
    By searchSuggestionFrame  = By.xpath("//div[@class='A8SBwf emcav']");
    By clearBtn = By.xpath("//span[@aria-label='Clear']");
    By iAMFeelingLuckyBtn = By.xpath("//input[@class='RNmpXc']");

    public void clickOnAndSetSearchView(WebDriver driver, String keyword) throws IOException {
        if (waitForElement(driver, searchTxt)) {
            clickOnElement(driver, searchTxt);
            sendText(driver,searchTxt,keyword);
        }
    }
    public void clickOnSearchBtn(WebDriver driver){
       driver.findElement(searchTxt).sendKeys(Keys.ENTER);
    }
    public void assertOnSearchSuggestionContainingKeyword(WebDriver driver, String keyword) {
        int count = 0;
        waitForElement(driver,searchSuggestionFrame);
        List<WebElement> li = driver.findElements(searchResult);
        for (WebElement webElement1 : li) {
            if(webElement1.getText().toLowerCase().contains(keyword)){
                count++;
            }
        }
        Assert.assertEquals(count,10);
    }
    public void assertOnAutoComplete(WebDriver driver, String keyword) {
        boolean exist = false;
        waitForElement(driver,searchSuggestionFrame);
        List<WebElement> li = driver.findElements(searchResult);
        for (WebElement webElement1 : li) {
            if(webElement1.getText().toLowerCase().contains(keyword)){
                exist = true;
                break;
            }
        }
        Assert.assertTrue(exist);
    }
    public void sendVoice(WebDriver driver,String keyword) throws InterruptedException, IOException {
        if (waitForElement(driver, voiceBtn)) {
            clickOnElement(driver, voiceBtn);
            SpeakUtil.allocate();
            SpeakUtil.speak(keyword);
        }
    }
    public void assertOnDriverTitle(WebDriver driver,String keyword){
       Assert.assertTrue(driver.getTitle().toLowerCase().contains(keyword));
    }
    public void assertOnDriverUrl(WebDriver driver,String keyword){
        keyword  = keyword.replace(" ","+");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(keyword));
    }
    public void clearTxt(WebDriver driver) throws IOException {
        waitForElement(driver,clearBtn);
        clickOnElement(driver,clearBtn);
    }
    public void clickOnFeelingLucky(WebDriver driver) throws IOException {
        waitForElement(driver,iAMFeelingLuckyBtn);
        clickOnElement(driver,iAMFeelingLuckyBtn);
    }


}
