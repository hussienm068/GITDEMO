package Utilities;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.android.AndroidDriver;
import Utilities.AppiumUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class AndroidGestures extends AppiumUtilities
{
    private AndroidDriver driver;
    private JavascriptExecutor jse;
    private HashMap<String , Object> hashMap;

    public AndroidGestures (AndroidDriver driver)
    {

        this.driver = driver;
    }




    public WebElement scrollDownAndGetElementByText(String text)
    {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(" +"\"" +text + "\"" +"))"));
        return element;
    }

    public void invokeActivity(String appPackage , String appActivity)
    {
        jse = (JavascriptExecutor) driver;
        hashMap = new HashMap<>();
        hashMap.put("appPackage" , appPackage);
        hashMap.put("appActivity" , appActivity);
        jse.executeScript("mobile: startActivity" , hashMap);

    }
    public void invokeActivityByIntentID(String appPackage , String activityName)
    {
        jse = (JavascriptExecutor) driver;
        hashMap = new HashMap<>();
        hashMap.put("intent" , appPackage+"/."+activityName);
        jse.executeScript("mobile: startActivity" , hashMap);
    }

    public void longPress(WebElement element)
    {
        jse = (JavascriptExecutor) driver;
        hashMap = new HashMap<>();
        hashMap.put("elementId" ,((RemoteWebElement)element).getId());
        jse.executeScript("mobile: longClickGesture" , hashMap);

    }

}
