package com.appium.automation.pageObjects.android;

import Utilities.AndroidGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidGestures
{
    public enum GenderType
    {
        male , female
    }

    private AndroidDriver driver;

    public FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver) , this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleGender;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleGender;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public WebElement scrollDownAndGetElementByText(String text)
    {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(" +"\"" +text + "\"" +"))"));
        return element;
    }
public void setNameField(String name)
{
    waitForElementToBevisible(nameField);
    nameField.sendKeys(name);
    driver.hideKeyboard();
}

public void setGender(String gender)
{
    if (gender == GenderType.male.toString())
    {
        maleGender.click();
    }
    else if (gender == GenderType.female.toString())
    {
        femaleGender.click();
    }
    else
    {
        System.out.println("Invalid gender ");
    }

}//End of setGender

    public void setCountry(String countryName)
    {
        countrySelection.click();
        scrollDownAndGetElementByText(countryName).click();
    }//End of setCountrySelection

    public ProductCatalog clickOnShopButton()
    {
        shopButton.click();
        return new ProductCatalog(driver);
    }//End of clickOnShopButton

}
