package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class LoginWithRealUserTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver.get(ConfigReader.getProperty("url"));
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        } catch (Exception ignored) {}

        WebElement girisYapLink = driver.findElement(By.xpath("//p[text()='Giriş Yap']"));
        girisYapLink.click();
        Thread.sleep(2000);

        WebElement emailInput = driver.findElement(By.id("login-email"));
        WebElement passwordInput = driver.findElement(By.id("login-password-input"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailInput.sendKeys(ConfigReader.getProperty("email"));
        passwordInput.sendKeys(ConfigReader.getProperty("password"));
        // loginButton.click();
        loginButton.click();

// Daha güvenli bir şekilde 'Hesabım' elementinin gelmesini bekleyelim:
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hesabim = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Hesabım']")));
        Assert.assertTrue(hesabim.isDisplayed(), "Giriş başarısız olabilir!");

    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}