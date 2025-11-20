package com.appium.test.android;
import Utilities.AndroidGestures;
import com.appium.automation.pageObjects.android.CartPage;
import com.appium.automation.pageObjects.android.FormPage;
import com.appium.automation.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FormPageTest extends BaseTest
{
    AndroidGestures androidGestures;

    @BeforeMethod
    public void activateApp()
    {
        driver.activateApp("com.androidsample.generalstore");
        androidGestures = new AndroidGestures(driver);
        androidGestures.invokeActivityByIntentID("com.androidsample.generalstore" , "com.androidsample.generalstore.MainActivity");
    }

    @Test(priority = 2 , enabled = false)
    public void fillForm_ErrorValidation()
    {
        formPage.setNameField("Hello");
        formPage.clickOnShopButton();
    }


    @Test(priority = 1 , dataProvider = "getData" , groups = "smoke")
    public void fillForm(HashMap<String , Object> input) throws InterruptedException
    {
        SoftAssert softAssert = new SoftAssert();
        formPage.setNameField(input.get("name").toString());
        formPage.setGender(input.get("gender").toString());
        formPage.setCountry(input.get("country").toString());
        ProductCatalog pc = formPage.clickOnShopButton();
        pc.setAddToCartByIndex(0);
        pc.setAddToCartByIndex(0);
        CartPage cp = pc.goToCartPage();
       // Assert.assertEquals(cp.getDisplayedTotalAmount(), cp.getProductsSum());
       softAssert.assertEquals(cp.getDisplayedTotalAmount(), cp.getProductsSum());
        cp.clickOnTermsAndConditions();
        cp.markCheckBox();
        cp.clickOnProceedButton();
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeApp()
    {
        driver.terminateApp("com.androidsample.generalstore");
    }
    //Creating two dimensional array
    @DataProvider
    public Object[][] getData() throws IOException
    {
      List<HashMap<String , Object>> data =  new AndroidGestures(driver).getJsonData(System.getProperty("user.dir") +"//src//test//java//com//appium//testData//testData.json");
        return new Object[][]
                {
                        {data.get(0)} ,
                        {data.get(1)}
                };
    }

}
