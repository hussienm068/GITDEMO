package com.appium.automation.pageObjects.android;

import Utilities.AndroidGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidGestures {

    private AndroidDriver driver;
    private int productsCount;
    private double productsSum;
    private double sum = 0;


    public CartPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver) , this);

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> productsList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement displayedTotalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsAndConditions;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeTermsAndConditionsButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;

    public int getProductsCount()
    {
        productsCount = productsList.size();
        return productsCount;
    }


    public double getDisplayedTotalAmount()
    {
        return  Double.parseDouble(displayedTotalAmount.getText().replace("$" , ""));
    }

    public double getProductsSum()
    {
        for (int i = 0 ; i<getProductsCount() ; i++)
        {
            String amount = productPrice.get(i).getText().replace("$" , "");
            productsSum = productsSum+getFormattedAmount(amount);
        }
        return productsSum;
    } //End getProductSum
    public void setCloseTermsAndConditions()
    {
        closeTermsAndConditionsButton.click();
    }//End setCloseTermsAndConditions

    public void clickOnTermsAndConditions()
    {
        longPress(termsAndConditions);
        setCloseTermsAndConditions();
    }//End clickOnTermsAndConditions

    public void markCheckBox()
    {
        checkBox.click();
    }//End markCheckBox

    public void clickOnProceedButton()
    {
        proceedButton.click();
    }//End clickOnProceedButton

}
