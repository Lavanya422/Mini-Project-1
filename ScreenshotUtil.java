package com.demoblaze.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        try {
            Files.createDirectories(new File("screenshots").toPath());
            Files.copy(src.toPath(), new File(destPath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}