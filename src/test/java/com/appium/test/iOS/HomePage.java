package com.appium.test.iOS;

import com.appium.automation.pageObjects.iOS.AlertViews;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage extends BaseTest
{
    @Test
    public void basicTest()
    {
       AlertViews alertViews =  homePage.selectAlertViews();
       alertViews.sendTextEntry("Vendetta");
        Assert.assertEquals(alertViews.getConfirmCancelMessage() , "A message should be a short, complete sentence.");


    }


}
