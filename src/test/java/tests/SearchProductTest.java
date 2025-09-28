package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage;
import utilities.ConfigReader;
import utilities.Driver;

public class SearchProductTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("url"));
    }

    @Test
    public void searchBlueTopProduct() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("automation exercise"));

        // 4. Click on 'Products' button
        searchPage.clickProductsButton();

        // 5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(searchPage.getAllProductsTitle().isDisplayed());

        // 6. Enter product name in search input and click search button
        searchPage.searchFor("Blue Top");

        // 7. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(searchPage.getSearchedProductsTitle().isDisplayed());

        // 8. Verify all the products related to search are visible
        Assert.assertTrue(searchPage.isSearchedProductVisible("Blue Top"));
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}