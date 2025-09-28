package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;

public class CartTest {
    WebDriver driver;
    CartPage cartPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void testAddToCart() {
        cartPage.goToHomePage();
        cartPage.goToProducts();
        cartPage.addFirstProductToCart();
        cartPage.continueShopping();
        cartPage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart(), "Sepet boş veya ürün eklenemedi.");
    }
}