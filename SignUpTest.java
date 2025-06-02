package com.demoblaze.testcases;

import com.demoblaze.pages.SignUpPage;
import com.demoblaze.utilities.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    private SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() {
        signUpPage = new SignUpPage(getDriver());
    }

    @Test(priority = 1)
    public void testValidSignUp() {
        try {
            // Open signup modal and submit valid user details
            signUpPage.signUp("testuser", "testpass");

            // Wait briefly to catch alert popup
            Thread.sleep(2000);

            // Capture and verify alert text (example: "Sign up successful.")
            String alertText = signUpPage.getAlertText();
            Assert.assertTrue(alertText.toLowerCase().contains("successful"), "Signup should be successful");

            // Accept alert to close it
            signUpPage.acceptAlert();

            BaseTest.getTest().pass("Sign Up test passed");

        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(getDriver(), "testValidSignUp");
            BaseTest.getTest().fail("Sign Up test failed: " + e.getMessage());
            Assert.fail("Sign Up test failed: " + e.getMessage());
        }
    }
}
