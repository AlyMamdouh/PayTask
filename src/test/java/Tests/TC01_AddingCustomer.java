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










@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})


public class TC01_AddingCustomer
{

    public final String FIRSTNAME = DataUtils.getJsonData("addingCustomer", "firstname");
    public final String LASTNAME = DataUtils.getJsonData("addingCustomer", "lastname");
    private final String POSTCODE = DataUtils.getJsonData("addingCustomer", "postcode");


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
    public void validLoginTC()
    {
        new BankXYZ_Pages(getDriver())
                .clickBankManagerLoginBtn()
                .clickAddingCustBtn()
                .EnterFirstN(FIRSTNAME)
                .EnterLastN(LASTNAME)
                .EnterPostcode(POSTCODE)
                .clickAddCustBtn()
                .checkCustAdding();
    }


    @AfterTest
    public void quit() {
        quitDriver();
    }
}
