package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility
{

    public static WebElement findWebElement(WebDriver driver, By locator)
    {
        return driver.findElement(locator);

        // فانكشن بتخليك تستخدم الWebElement كـ Object و تتعامل معاه .. افهم من ال usages
        // بتديها الدرايفر و الWebElement
    }




    public static void scrolling(WebDriver driver, By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));

        // فانكشن بتعمل سكرول لحد ما تلاقي WebElement معين
        // بتديها الدرايفر getDriver() و الWebElement
        // مستخدمة فانكشن findWebElement اللي انت عاملها فوق خلي بالك
    }




    public static void selectingFromDropDown(WebDriver driver, By locator, String option)
    {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);

        // فانكشن بتفتح القائمة و تختار منها اختيار
        // بتديها الدرايفر .. و الWebElement بتاع القائمة .. و النص اللي عايز تختاره
        // مستخدمة فانكشن findWebElement اللي انت عاملها فوق خلي بالك
    }




    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());

        // yyyy-MM-dd-hh-mm-ssa فانكشن بتجبلك التاريخ و الوقت الحالي على هيئة كلام
        //مبتاخدش حاجه و في الاغلب بتستخدمها مع السكرينشوت
    }




    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";
    public static void takeScreenShot(WebDriver driver, String screenshotName)
    {

        try
        {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }

        // المتغير ده SCREENSHOTS_PATH بيخزن المسار اللي هيتم حفظ السكرين شوت فيه
        // برايفت: يعني المتغير ده مش ممكن الوصول إليه من خارج الكلاس
        // ستاتيك: المتغير مرتبط بالكلاس نفسه، مش بالobjects اللي هتنشأ منه
        // فاينال: هياخد قيمة مرة واحدة بس وما تقدرش تغيرها بعد كده

        // الفانكشن بتاخد سكرينشوت وتضيفها لريبورت Allure .. و بتديها driver و اسم السكرين شوت و غالبا بيكونو getDriver() و testResult.getName()
        // تراي: حاول تنفذ الكود ده .. كاتش: لو حصل ايرور سجله بدل ما التيست يقف
        // اللي جوه الكاتش ده : بتسجل الايرور فال logs، علشان تقدر تعرف الايرور وتتعامل معاه بعدين
    }

    public static void takeHighlightedScreenshot(WebDriver driver, By locator)
    {
        try
        {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOTS_PATH);
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }

        // الفانكشن بتاخد سكرينشوت و بتديها driver و الWebElement اللي انت عايز تـ highlight عليه
        // مستخدمة فانكشن findWebElement اللي انت عاملها فوق خلي بالك
    }




    public static void clickingOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

        // الفانكشن دي بتستنى لحد ما الWebElement ده يكون clickable لمده اقصاها 5ث بعد كده تضغط عليه
        // الفانكشن دي بتاخد driver و WebElement اللي بتكون معرفها ف البيدج اصلا
    }




    public static void sendData(WebDriver driver, By locator, String data)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);

        // الفانكشن دي بتستنى لحد ما الWebElement ده يكون Visible لمده اقصاها 5ث بعد كده تدخل الداتا
        // الفانكشن دي بتاخد driver و WebElement اللي بتكون معرفها ف البيدج اصلا و الداتا اللي عايز تدخلها مـنغيــر ""
    }




    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

        // الفانكشن دي بتستنى لحد ما الWebElement ده يكون Visible لمده اقصاها 5ث بعد كده تجيب الكلام اللي جواه
        // الفانكشن دي بتاخد driver و WebElement اللي بتكون معرفها ف البيدج اصلا
    }




    public static WebDriverWait generalWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(5));

        //فانكشن بتخلي الدرايفر يستنى 5 ثواني
        // بتاخد الدرايفر او getDriver()
    }




    public static int generateRandomNumber(int MaxNumber)
    {
        return new Random().nextInt(MaxNumber) + 1;

        // فانكشن بتـ generate رقم عشوائي من 1 لحد الرقم اللي انت بتديهولها
        // لو عايزها تبدأ من صفر شيل ال+1
    }




    public static Set<Integer> generateUniqueNumber(int NoOfUniqueNumbers, int MaxNumber)
    {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < NoOfUniqueNumbers)
        {
            int randomNumber = generateRandomNumber(MaxNumber);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;

        // فانكشن بتديلك مجموعة من unique random numbers بعدد ارقام معين و من 1 لحد ال MaxNumber
        // وانت بتديها عدد الارقام اللي عايزها و الMaxNumber
        // لو عايز الارقام تجيلك مترتبه تصاعدي استخدم TreeSet بدل HashSet اللي ف اخر اول سطر
    }




    public static boolean VerifyURL(WebDriver driver, String expectedURL)
    {
        try
        {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        }
        catch (Exception e)
        {
            return false;
        }
        return true;

        // الفانكشن بتتشيك ان ال URL هوا هوا اللي انت مدخلهولها ولا لا لو تمام بترجع true لو مش هوا بترجع false
        // بتاخد الدرايفر getDriver() او driver .. و الURL المتوقع بين ""
    }




    public static Set<Cookie> getAllCookies(WebDriver driver)
    {
        return driver.manage().getCookies();

        // فانكشن بتجبلك الكوكيز اللي مع الدرايفر وانت بتحطها ف متغير بعد كده زي مثلا .. cookies = getAllCookies(getDriver())
        // و بتكون معرف cookies الاول طبعا قبل اي حاجه زي كده .. private Set<Cookie> cookies
        // بتديها الدرايفر و غالبا بيكون getDriver()
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies)
    {
        for (Cookie cookie : cookies) driver.manage().addCookie(cookie);

        //فانكشن بتخلي الدرايفر يفتح البراوزر عن طريق الكوكيز اللي المفروض تكون خزنتها من الفانكشن اللي فاتت
        // بتديها الدرايفر و غالبا بيكون getDriver() و كمان طبعا المتغير اللي فيه الكوكيز
    }




    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];

        // فانكشن بتسيرش و تجبلك احدث فايل موجود في فولدر معين
        // بتديها ال path بتاع الفولدر اللي عايز تدور فيه
    }
}
