package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchResultPage extends PageBase {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    By searchResultHeaders = By.xpath("//h3[@class='LC20lb DKV0Md']");
    By resultStatus = By.id("result-stats");

    public void assertOnSearchResultHeader(WebDriver driver,String keyword){
        boolean notFound = false;
        waitForElement(driver,resultStatus);
        List<WebElement> li = driver.findElements(searchResultHeaders);
        for (WebElement webElement1 : li) {
            if(!webElement1.getText().toLowerCase().contains(keyword)&& !webElement1.getText().equals("")){
                notFound = true;
                break;
            }
            else{
                System.out.println(webElement1.getText());
            }
        }
        Assert.assertFalse(notFound);
    }
    public void assertOnSearchResult(WebDriver driver){
        waitForElement(driver,resultStatus);
        Assert.assertTrue(getElementText(driver,resultStatus).contains("results"));
    }
    public void selectFirstSearchResult(WebDriver driver){
        waitForElement(driver,resultStatus);
        selectElementInList(driver,searchResultHeaders,0);
    }
}
