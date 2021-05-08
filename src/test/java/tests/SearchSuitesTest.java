package tests;

import io.qameta.allure.Description;
import kotlin.jvm.Throws;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MapSearchPage;
import pages.SearchPage;
import pages.SearchResultPage;
import properties.TestData;

import java.io.IOException;

public class SearchSuitesTest extends TestBase {
    SearchPage searchPage;
    SearchResultPage searchResultPage;
    MapSearchPage mapSearchPage;
    TestData testData;


    @BeforeMethod(alwaysRun = true)
    public void setup() {

        searchPage = new SearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        mapSearchPage = new MapSearchPage(driver);
        testData = new TestData();
    }

    @Test
    @Description("Search Suggestions")
    public void checkOnSearchSuggestion() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.keyword);
        searchPage.assertOnSearchSuggestionContainingKeyword(driver, testData.keyword);
    }

    @Test(groups = {"regressionTest"})
    @Description("Auto Completion")
    public void autoCompletion() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.SubKeyword);
        searchPage.assertOnAutoComplete(driver, testData.keyword);
    }

    @Test(groups = {"regressionTest", "smokeTest"})
    @Description("Check Search results and redirection")
    public void checkOnSearchResultAndRedirection() throws InterruptedException, IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.keyword);
        searchPage.clickOnSearchBtn(driver);
        searchResultPage.assertOnSearchResultHeader(driver, testData.keyword);
        searchPage.assertOnDriverTitle(driver, testData.keyword);
        searchPage.assertOnDriverUrl(driver, testData.keyword);
        searchResultPage.assertOnSearchResult(driver);
        searchResultPage.selectFirstSearchResult(driver);
        searchPage.goBack(driver);
        searchPage.assertOnDriverTitle(driver, testData.keyword);
        searchPage.assertOnDriverUrl(driver, testData.keyword);
        searchResultPage.assertOnSearchResultHeader(driver, testData.keyword);
        searchResultPage.assertOnSearchResult(driver);
    }

    @Test(groups = "smokeTest")
    @Description("Check type multiple keywords")
    public void checkRedirection() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.MultipleKeyword);
        searchPage.clickOnSearchBtn(driver);
        searchPage.assertOnDriverTitle(driver, testData.MultipleKeyword);
        searchPage.assertOnDriverUrl(driver, testData.MultipleKeyword);
        searchResultPage.assertOnSearchResultHeader(driver, testData.MultipleKeyword);
    }

    @Test
    @Description("Check clearing Txt and type again")
    public void clearTextAndSearchAgain() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.keyword);
        searchPage.clearTxt(driver);
        searchPage.clickOnAndSetSearchView(driver, testData.MultipleKeyword);
    }

    @Test
    @Description("Feeling Lucky feature")
    public void checkFeelingLucky() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.keyword);
        searchPage.clickOnFeelingLucky(driver);
        searchPage.assertOnDriverUrl(driver, testData.keyword);
    }


    @Test
    @Description("Check search with Google Map")
    public void assertOnMapCoordinates() throws InterruptedException, IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.instabugOfficeKeyword);
        searchPage.clickOnSearchBtn(driver);
        mapSearchPage.assertOnLocation(driver, testData.instabugLat, testData.intsabugLong);
    }

    @Test
    @Description("Search by Voice")
    public void searchByVoice() throws InterruptedException, IOException {
        searchPage.sendVoice(driver, testData.voice);
        searchResultPage.assertOnSearchResultHeader(driver, testData.voiceResult);
        searchPage.assertOnDriverTitle(driver, testData.voiceResult);
        searchPage.assertOnDriverUrl(driver, testData.voiceResult);
        searchResultPage.assertOnSearchResult(driver);
    }

    @Test
    @Description("Search by Special Character")
    public void searchBySpecialCharacter() throws IOException {
        searchPage.clickOnAndSetSearchView(driver, testData.specialChar);
        searchPage.clickOnSearchBtn(driver);
        searchResultPage.assertOnSearchResult(driver);
    }

}
