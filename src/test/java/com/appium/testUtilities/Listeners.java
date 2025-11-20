package com.appium.testUtilities;

import Utilities.AppiumUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners extends AppiumUtilities implements ITestListener {
    ExtentReports extent = ExtentReporter.getReporter();
    ExtentTest test;
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS , "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());
       try
       {
           driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
       }
       catch (Exception e1)
       {
           e1.printStackTrace();
       }
       try {
           test.addScreenCaptureFromPath(getScreenShotPath(driver, result.getMethod().getMethodName()));
       }
       catch (IOException e2)
       {
           e2.printStackTrace();
       }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        try
        {
            Desktop.getDesktop().browse(new File(ExtentReporter.getReportPath()).toURI());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
