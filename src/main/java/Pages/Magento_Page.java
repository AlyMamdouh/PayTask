package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class BankXYZ_Pages
{
    private static WebDriver driver;

    public BankXYZ_Pages(WebDriver driver) {
        BankXYZ_Pages.driver = driver;
    }


    private final By CreatAccUpperBtn = By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']");
    private final By FirstName = By.xpath("//input[@id='firstname']");
    private final By LastName = By.xpath("//input[@id='lastname']");
    private final By Email = By.xpath("//input[@id='email_address']");
    private final By Password = By.xpath("//input[@id='password']");
    private final By ConfPassword = By.xpath("//input[@id='password-confirmation']");
    private final By CreatAccLowerBtn = By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]");
    private static final By FirstItemHov = By.xpath("(//div[@aria-label='Color'])[1]");
    private static final By FirstItemAddComp = By.xpath("(//a[@title='Add to Compare'])[1]");
    private static final By SecItemHov = By.xpath("(//div[@aria-label='Color'])[2]");
    private static final By SecItemAddComp = By.xpath("(//a[@title='Add to Compare'])[2]");





    public BankXYZ_Pages clickCreateAccUpper()
    {
        Utility.clickingOnElement(driver, CreatAccUpperBtn);
        return new BankXYZ_Pages(driver);
    }

    public BankXYZ_Pages clickCreateAccLower()
    {
        Utility.clickingOnElement(driver, CreatAccLowerBtn);
        return new BankXYZ_Pages(driver);
    }

    public BankXYZ_Pages MSGassertionAndGoHome()
    {
        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.get("https://magento.softwaretestingboard.com/");
        return new BankXYZ_Pages(driver);
    }






    public BankXYZ_Pages EnterFirstName(String firstN)
    {
        Utility.sendData(driver, FirstName, firstN);
        return this;
    }

    public BankXYZ_Pages EnterLastName(String lastN)
    {
        Utility.sendData(driver, LastName, lastN);
        return this;
    }

    public BankXYZ_Pages EnterEmail(String PCode)
    {
        Utility.sendData(driver, Email, PCode);
        return this;
    }

    public BankXYZ_Pages EnterPassword(String PCode)
    {
        Utility.sendData(driver, Password, PCode);
        return this;
    }

    public BankXYZ_Pages EnterConfPassword(String PCode)
    {
        Utility.sendData(driver, ConfPassword, PCode);
        return this;
    }






}

