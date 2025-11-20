package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppiumUtilities
{
    private AppiumDriver driver;
    public AppiumDriverLocalService service;
    private File appiumJSFile;

    public AppiumDriverLocalService startAppium(String appiumServerURL , int port)
    {
        appiumJSFile = new File("//opt//homebrew/lib//node_modules//appium//build//lib//main.js");
        service = new AppiumServiceBuilder().withAppiumJS(appiumJSFile).withIPAddress(appiumServerURL).usingPort(port).build();
        service.start();
        return service;
    }

    public double getFormattedAmount (String amount)
    {
        double formattedAmount = Double.parseDouble(amount);
        return formattedAmount;
    }

    public void waitForElementToBevisible(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public List<HashMap<String , Object>> getJsonData(String jsonFilePath) throws IOException
    {
       // File jsonFile = new File(System.getProperty("user.dir") +"//src//test//java//com//appium//testData//testData.json");
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath) , StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String , Object>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {
        });
        return data;

    }

    public String getScreenShotPath(AppiumDriver driver , String testCaseName) throws IOException
    {
        String timeStamp  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      File source = driver.getScreenshotAs(OutputType.FILE);
      String destination = "/Users/hussien/Documents/AppiumRevision/src/test/reports/" + testCaseName+timeStamp +".png";
      FileUtils.copyFile(source , new File(destination));
      System.out.println(destination);
      return destination;
    }



}
