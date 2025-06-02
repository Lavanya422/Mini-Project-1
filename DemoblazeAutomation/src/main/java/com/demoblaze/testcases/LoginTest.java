package com.demoblaze.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    protected WebDriver driver;

    public void initializeDriver() {
        // Set driver executable path if needed, e.g.,
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void setup() {
        initializeDriver();
        getDriver().get("https://www.demoblaze.com/");
    }

    @Test
    public void validLoginTest() {
        String pageTitle = getDriver().getTitle();
        Assert.assertEquals(pageTitle, "STORE");
    }

    @AfterMethod
    public void teardown() {
        quitDriver();
    }
}
