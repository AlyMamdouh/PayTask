package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage
{
    private final WebDriver driver;
    // ده عبارة عن متغير private
    // برايفت: فايدتها إنه ما يقدرش أي كود خارج كلاس P01_LoginPage انه يوصل له
    // فاينال: فايدتها انه هياخد قيمة مرة واحدة بس لما تنشئ الـ object (في الـ constructor) وما تقدرش تغير قيمته بعد كده
    // الـ WebDriver اللي هيتعين للكلاس ده مش هيتغير طول مدة تشغيل الكود. ده بيضمن استقرار أكبر للكود لأنك بتتأكد إن الـ WebDriver اللي بتستخدمه ثابت

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    // ده كونستراكتور و بستخدمه ف كل بيدج بعمل فيها تيست
    //لما تنشئ object من الكلاس P01_LoginPage، بتحتاج تربطه تمررله الدرايفر
    // السطر بتاع زيس درايفر ده بياخد الدرايفر اللي مررته ويخزنه في المتغير اللي انت عامله فوق خالص
    //فال constructor ده بيوصل الدرايفر اللي جاي من التيست بالكلاس بتاع البيدج دي عشان يخلي البراوزر يتعامل مع الWebElements اللي هنا



    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    // هنا انا بحط الWebElements اللي هستخدمها بتاعت البيدجات اللي هشتغل عليها



    public P01_LoginPage enterUsername(String usernameText)
    {
        Utility.sendData(driver, username, usernameText);
        return this;
    }

    public P01_LoginPage enterPassword(String passwordText) {
        Utility.sendData(driver, password, passwordText);
        return this;
    }

    public P02_LandingPage clickOnLoginButton() {
        Utility.clickingOnElement(driver, loginButton);
        return new P02_LandingPage(driver);
    }

    public boolean assertLoginTc(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }

}
