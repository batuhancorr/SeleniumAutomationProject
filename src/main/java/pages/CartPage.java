package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToHomePage() {
        driver.get("https://automationexercise.com");
    }

    public void goToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();
    }

    public void addFirstProductToCart() {
        // Sayfadaki tüm iframeleri kaldır (reklamları sil)
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe').forEach(e => e.remove());"
        );

        // Kısa bir bekleme (iframe silindikten sonra DOM güncellensin)
        try {
            Thread.sleep(1000); // 1 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add to cart butonunu bulun
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[text()='Add to cart'])[1]")));

        // JS ile tıklama yap (engel kalmasa bile garanti)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
    }

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']"))).click();
    }

    public boolean isProductInCart() {
        return driver.getPageSource().contains("Shopping Cart");
    }
}