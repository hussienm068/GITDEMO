package com.appium.testUtilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter
{
    public static ExtentSparkReporter reporter ;
    public static ExtentReports extent;
    public static String path;

    public  static ExtentReports getReporter()
    {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
         path = System.getProperty("user.dir") + "//src//test//reports//index" +timeStamp+".html";
        reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Ro9 Regression");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("My Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester" , "Hussien Mohamed");
        return  extent;
    }
    public static String getReportPath()
    {
        return path;
    }



}
