package com.demoblaze.testcases;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.PlaceOrderPage;
import com.demoblaze.utilities.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;
    private PlaceOrderPage placeOrderPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(getDriver());
        cartPage = new CartPage(getDriver());
        placeOrderPage = new PlaceOrderPage(getDriver());
    }

    @Test(priority = 1)
    public void testPlaceOrder() {
        try {
            // 1. Login with valid credentials
            loginPage.login("testuser", "testpass");
            Thread.sleep(2000); // Wait for login to complete

            // 2. Navigate to Cart page
            cartPage.goToCart();

            // 3. Place the order with sample details
            placeOrderPage.placeOrder("John Doe", "USA", "New York", "1234567890", "12", "2025");

            // 4. Verify order confirmation dialog appears
            Assert.assertTrue(placeOrderPage.isOrderConfirmed(), "Order confirmation message should be displayed");

            // 5. Close the confirmation dialog
            placeOrderPage.confirmOrder();

            BaseTest.getTest().pass("Place Order test passed");

        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(getDriver(), "testPlaceOrder");
            BaseTest.getTest().fail("Place Order test failed: " + e.getMessage());
            Assert.fail("Place Order test failed: " + e.getMessage());
        }
    }
}
