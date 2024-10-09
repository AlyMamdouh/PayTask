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




import org.testng.annotations.Listeners;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC03_Searching
{

    private final String FIRSTNAME = DataUtils.getJsonData("addingCustomer", "firstname");
    private final String LASTNAME = DataUtils.getJsonData("addingCustomer", "lastname");
    private final String POSTCODE = DataUtils.getJsonData("addingCustomer", "postcode");



    @BeforeClass
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
                .checkCustAdding()
                .clickHomeBtn()
                .clickBankManagerLoginBtn()
                .clickonCustomers()
                .SearchByName(FIRSTNAME)
                .CheckSearching(FIRSTNAME);

    }

    @AfterClass
    public void quit()
    {
        quitDriver();
    }


}
