package com.appium.test.android;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentDemo {
    ExtentSparkReporter reporter;
    ExtentReports extent;

    //We should be familiar with the core extent report classes(Extent Report , ExtentSparkReporter)
    @BeforeTest
    public void config()
    {
        String path = "//Users//hussien//Documents//AppiumRevision//src//test//reports//index.html";
        reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("R09 Regression");
        reporter.config().setTheme(Theme.STANDARD);
         extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester" , "Hussien Mohamed");
    }

    @Test
    public void initialDemo()
    {

       ExtentTest myTest =  extent.createTest("Test Extent Report");
       System.setProperty("webdriver.chrome.driver" , "//Users//hussien//Downloads//ChromeDriver142//chromedriver-mac-arm64//chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        driver.close();
        myTest.fail("Results do not match");
        extent.flush();//To stop monitoring the test execution

    }

}
