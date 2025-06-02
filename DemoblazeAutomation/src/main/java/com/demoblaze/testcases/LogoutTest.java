package com.demoblaze.testcases;

import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.LogoutPage;
import com.demoblaze.utilities.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private LogoutPage logoutPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(getDriver());
        logoutPage = new LogoutPage(getDriver());
    }

    @Test(priority = 1)
    public void testLogoutFunctionality() {
        try {
            // Login first
            loginPage.login("testuser", "testpass"); // Use valid credentials
            Thread.sleep(2000);  // Wait for login to process

            logoutPage.clickLogout();

            // Verify logout success by checking login button visibility or any logout indicator
            boolean isLoginVisible = logoutPage.isLoginButtonVisible();
            Assert.assertTrue(isLoginVisible, "Login button should be visible after logout.");
            
            // Optionally log success in ExtentReports
            BaseTest.getTest().pass("Logout test passed");

        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(getDriver(), "testLogoutFunctionality");
            BaseTest.getTest().fail("Logout test failed: " + e.getMessage());
            Assert.fail("Logout test failed: " + e.getMessage());
        }
    }
}
