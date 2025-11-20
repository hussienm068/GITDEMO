package com.appium.automation.pageObjects.iOS;

import Utilities.IOSGestures;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViews  extends IOSGestures {
    private IOSDriver driver;

    public AlertViews(IOSDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver) , this);
    }
    @iOSXCUITFindBy(accessibility = "Text Entry")
    private WebElement textEntry;

    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    private WebElement textField;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement confirmTextEntry;

    @iOSXCUITFindBy(accessibility = "Cancel")
    private WebElement cancelTextEntry;

    @iOSXCUITFindBy(accessibility = "Confirm / Cancel")
    private WebElement confirmCancel;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A Message'")
    private WebElement confirmCancelMessage;

    public void sendTextEntry(String text)
    {
        textEntry.click();
        textField.sendKeys(text);
        confirmTextEntry.click();

    }

    public void selectConfirmCancel()
    {
        confirmCancel.click();
    }
    public String getConfirmCancelMessage()
    {
        selectConfirmCancel();
        return confirmCancelMessage.getText();
    }

}
