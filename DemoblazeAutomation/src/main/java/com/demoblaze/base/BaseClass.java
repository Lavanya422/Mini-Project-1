package com.demoblaze.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseClass {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to your chromedriver executable if needed
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\chromedriver-win64\\chromedriver.exe");

        // Initialize Chrome driver
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open the base URL (change this as needed)
        driver.get("https://www.demoblaze.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
