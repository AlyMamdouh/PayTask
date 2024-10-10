package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.BankXYZ_Pages;
import Utilities.LogsUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;




import org.testng.annotations.Listeners;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC04_Sorting
{

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
    public void SortingPostcode()
    {
        new BankXYZ_Pages(getDriver())
                .clickBankManagerLoginBtn()
                .clickonCustomers()
                .clickonPostCode()
                .checkSortingOrder()
                .clickonPostCode()
                .checkSortingOrder();


    }

    @AfterClass
    public void quit()
    {
        quitDriver();
    }


}
