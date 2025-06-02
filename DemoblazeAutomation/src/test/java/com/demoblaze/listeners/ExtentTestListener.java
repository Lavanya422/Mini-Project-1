package com.demoblaze.listeners;

import com.aventstack.extentreports.*;
import com.demoblaze.testcases.BaseTest;
import com.demoblaze.utilities.ExtentManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        BaseTest.setTest(test);
        BaseTest.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        WebDriver driver = BaseTest.getDriver();
        if (driver != null) {
            String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
            // No IOException here, so no need to catch it
            BaseTest.getTest().addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Throwable throwable = result.getThrowable();
        String message = (throwable != null) ? throwable.getMessage() : "No Exception";
        BaseTest.getTest().log(Status.SKIP, "Test Skipped: " + message);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        BaseTest.getTest().log(Status.WARNING,
                "Test Failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // Nothing here for now
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
        BaseTest.unload(); // Remove ThreadLocal variables to avoid memory leaks
    }

    private String captureScreenshot(WebDriver driver, String methodName) {
        String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destination = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + dateName + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File finalDestination = new File(destination);

        try {
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
