package com.awesomeqa.listeners;

import com.awesomeqa.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest baseTest)) {
            return;
        }

        WebDriver driver = baseTest.getDriver();
        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            return;
        }

        try {
            File source = screenshotDriver.getScreenshotAs(OutputType.FILE);
            Path folder = Path.of("screenshots");
            Files.createDirectories(folder);
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path destination = folder.resolve(result.getMethod().getMethodName() + "_" + time + ".png");
            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Failure screenshot: " + destination.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Unable to save failure screenshot: " + e.getMessage());
        }
    }
}
