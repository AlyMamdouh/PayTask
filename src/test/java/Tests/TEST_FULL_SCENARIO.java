package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.BankXYZ_Pages;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
import io.qameta.allure.*;









@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

@Epic("Giza Systems Assessment")
@Severity(SeverityLevel.CRITICAL)

public class TC01_CreateAccount
{

    public final String FIRSTNAME = DataUtils.getJsonData("addingCustomer", "firstname");
    public final String LASTNAME = DataUtils.getJsonData("addingCustomer", "lastname");
    private final String EMAIL = DataUtils.getJsonData("addingCustomer", "email");
    private final String PASSWORD = DataUtils.getJsonData("addingCustomer", "password");
    private final String CONFPASSWORD = DataUtils.getJsonData("addingCustomer", "confirm_password");


    @BeforeTest
    public void setup() throws IOException
    {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("environment", "Browser");
        LogsUtils.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtils.info(browser + " driver is opened");
        getDriver().get(getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("Page is redirected to the URL");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void AddingCust()
    {
        new BankXYZ_Pages(getDriver())
                .clickCreateAccUpper()
                .EnterFirstName(FIRSTNAME)
                .EnterLastName(LASTNAME)
                .EnterEmail(EMAIL)
                .EnterPassword(PASSWORD)
                .EnterConfPassword(PASSWORD)
                .clickCreateAccLower()
                .MSGassertionAndGoHome()
                .HoverAndClick1()
                .HoverAndClick2();

    }


    @AfterTest
    public void quit() {
        quitDriver();
    }
}
