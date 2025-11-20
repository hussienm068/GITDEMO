package com.appium.automation.pageObjects.android;

import Utilities.AndroidGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AndroidGestures
{
    private AndroidDriver driver ;
    public ProductCatalog (AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver) , this);
    }//End Constructor

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartIcon;

    public void setAddToCartByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public CartPage goToCartPage() throws InterruptedException
    {
        cartIcon.click();
        Thread.sleep(2000);
        return  new CartPage(driver);
    }

}
