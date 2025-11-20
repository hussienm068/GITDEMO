package com.appium.test.android;

import Utilities.AndroidGestures;
import Utilities.AppiumUtilities;
import com.appium.automation.pageObjects.android.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtilities
{
    private AppiumDriverLocalService service;
    private UiAutomator2Options options;
    private URL appiumServerURL;
   public AndroidDriver driver;
    private File appiumJSFile;
    public FormPage formPage;
    public FileInputStream fis;
    public Properties prop;
    String iPAddress;
    int port;
    String deviceName;



    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        // Load properties
        fis = new FileInputStream(new File(System.getProperty("user.dir") +
                "/src/main/java/com/appium/automation/resources/GlobalData.properties"));
        prop = new Properties();
        prop.load(fis);

        iPAddress = System.getProperty("ipAddress") != null ?
                System.getProperty("ipAddress") : prop.getProperty("ipAddress", "127.0.0.1");
        port = Integer.parseInt(prop.getProperty("port"));
        deviceName = prop.getProperty("AndroidDeviceName");

        // Start Appium server
        service = startAppium(iPAddress, port);

        // Set driver options
        options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(System.getProperty("user.dir") +
                "/src/test/java/com/appium/resources/General-Store.apk");
        options.setPlatformVersion("13");

        // Initialize driver
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize page objects
        formPage = new FormPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
