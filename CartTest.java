package com.demoblaze.testcases;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.utilities.ConfigReader;
import com.demoblaze.utilities.ScreenshotUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.*;

public class CartTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        driver.get(ConfigReader.get("baseUrl"));

        // Initialize page objects
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void testAddToCartAndPlaceOrder() {
        try {
            loginPage.login("testuser", "testpass"); // use valid credentials
            cartPage.addProductToCart("Samsung galaxy s6");

            Assert.assertTrue(cartPage.isProductAddedToCart("Samsung galaxy s6"),
                "Product not added to cart");

            cartPage.placeOrder("John Doe", "USA", "New York", "1234567890123456", "12", "2025");

            Assert.assertTrue(cartPage.isOrderConfirmed(), "Order confirmation missing");

        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "testAddToCartAndPlaceOrder");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
