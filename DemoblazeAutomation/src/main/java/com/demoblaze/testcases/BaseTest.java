package com.demoblaze.testcases;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    // ThreadLocal for safe parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Driver getter/setter
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    // ExtentTest getter/setter
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    // Cleanup method to remove driver and test from ThreadLocal
    public static void unload() {
        driver.remove();
        test.remove();
    }
}
