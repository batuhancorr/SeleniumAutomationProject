package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class RegisterUserTest {

    WebDriver driver;

    @Test
    public void testUserRegistration() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("url"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 🔹 Tek bir defa tanımlandı

        // 3. Ana sayfa görünürlüğünü kontrol et
        Assert.assertTrue(driver.getTitle().contains("Automation"));

        // 4. Signup / Login butonuna tıkla
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        // 5. 'New User Signup!' görünür mü?
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='New User Signup!']")).isDisplayed());

        // 6. İsim ve e-posta gir
        driver.findElement(By.name("name")).sendKeys("Batuhan");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("batutest1234@gmail.com");

        // 7. Signup butonuna tıkla
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        // 8. ENTER ACCOUNT INFORMATION görünür mü?
        WebElement accountInfoHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Enter Account Information')]")
        ));
        Assert.assertTrue(accountInfoHeader.isDisplayed(), "'Enter Account Information' görünmedi!");

        // 9. Hesap bilgilerini doldur
        driver.findElement(By.id("id_gender1")).click(); // Mr seç
        driver.findElement(By.id("password")).sendKeys("batu1234");
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("January");
        driver.findElement(By.id("years")).sendKeys("1994");

        // 10. Bülten kutusu
        driver.findElement(By.id("newsletter")).click();

        // 11. Özel teklif kutusu
        driver.findElement(By.id("optin")).click();

        // 12. Ad, soyad, firma, adres, ülke, şehir, posta, telefon
        driver.findElement(By.id("first_name")).sendKeys("Batuhan");
        driver.findElement(By.id("last_name")).sendKeys("Çor");
        driver.findElement(By.id("company")).sendKeys("Test Company");
        driver.findElement(By.id("address1")).sendKeys("Hamidiye Mahallesi");
        driver.findElement(By.id("country")).sendKeys("Canada"); // Türkiye bazen sorun çıkarıyor
        driver.findElement(By.id("state")).sendKeys("Sinop");
        driver.findElement(By.id("city")).sendKeys("Gerze");
        driver.findElement(By.id("zipcode")).sendKeys("57600");
        driver.findElement(By.id("mobile_number")).sendKeys("5449304798");

        // 13. Create Account
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();

        // 14. 'ACCOUNT CREATED!' görünür mü?
        WebElement accountCreatedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//b[text()='Account Created!']")));
        Assert.assertTrue(accountCreatedMsg.isDisplayed(), "'ACCOUNT CREATED!' mesajı görünmüyor!");

        // 15. Continue
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Continue']")));
        continueBtn.click();

        //test satırıdır

        // 16. 'Logged in as username' görünür mü?
        WebElement loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as')]")));
        Assert.assertTrue(loggedInText.isDisplayed(), "'Logged in as username' görünmedi!");

        // 17. Delete Account
        WebElement deleteAccountBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href='/delete_account']")));

// Alternatif: JavaScript ile tıkla (gerekirse)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteAccountBtn);

        // 18. 'ACCOUNT DELETED!' görünür mü ve continue'ya tıkla
        WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//b[text()='Account Deleted!']")));
        Assert.assertTrue(deletedMsg.isDisplayed(), "'ACCOUNT DELETED!' mesajı görünmüyor!");

        WebElement continueAfterDelete = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Continue']")));
        continueAfterDelete.click();

        Driver.closeDriver();
    }
}