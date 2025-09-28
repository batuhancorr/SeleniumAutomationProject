package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProductsButton() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();
    }

    public WebElement getAllProductsTitle() {
        return driver.findElement(By.xpath("//h2[text()='All Products']"));
    }

    public void searchFor(String keyword) {
        WebElement searchInput = driver.findElement(By.id("search_product"));
        WebElement searchButton = driver.findElement(By.id("submit_search"));

        searchInput.sendKeys(keyword);
        searchButton.click();
    }

    public WebElement getSearchedProductsTitle() {
        return driver.findElement(By.xpath("//h2[text()='Searched Products']"));
    }

    public boolean isSearchedProductVisible(String productName) {
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='productinfo text-center']/p"));
        for (WebElement product : productNames) {
            if (product.getText().equalsIgnoreCase(productName)) {
                return product.isDisplayed();
            }
        }
        return false;
    }
}