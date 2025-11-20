package com.appium.test.iOS;

import com.appium.automation.pageObjects.android.FormPage;
import com.appium.automation.pageObjects.iOS.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private AppiumDriverLocalService service;
    private IOSDriver driver;
    private XCUITestOptions options;
    private URL appiumServerURL;
    private File appiumJSFile;
   public HomePage homePage;

    @BeforeClass
    public void startAppiumServer() throws MalformedURLException {
        options = new XCUITestOptions();
        appiumServerURL = new URL("http://127.0.0.1:4723");
        appiumJSFile = new File("//opt//homebrew/lib//node_modules//appium//build//lib//main.js");
        service = new AppiumServiceBuilder().withAppiumJS(appiumJSFile).withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
        options.setDeviceName("iPhone 16");
        options.setUdid("584A5ACE-D3D0-44AD-95B8-06388AAFF742");
        options.setApp("//Users//hussien//Downloads//resources//UIKitCatalog.app");
        options.setAutomationName("XCUITest");
        options.setPlatformVersion("");
        driver = new IOSDriver(appiumServerURL, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);

    }


}
