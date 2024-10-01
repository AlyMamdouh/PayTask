package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory
{
        private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
        // متغير driverThreadLocal من نوع ThreadLocal
        // و هي بتخلي كل Thread عنده نسخة مستقلة من WebDriver
        // ده بيساعد لو بتشغل اختبارات متعددة في نفس الوقت (Parallel Testing)
        // فكل اختبار بيشتغل على WebDriver الخاص بيه


        public static void setupDriver(String browser) //default is Edge
        {
            switch (browser.toLowerCase())
            {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverThreadLocal.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    driverThreadLocal.set(new FirefoxDriver());
                    break;
                default:
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--start-maximized");
                    driverThreadLocal.set(new EdgeDriver(options));
            }
            // ده الميثود اللي بتستخدمه علشان تحدد نوع المتصفح اللي عايز تشغل الاختبارات عليه
            // بتاخد ال browser كـ parameter
            // بتستخدم جملة switch علشان تشوف إذا كان المتصفح اللي تم اختياره هو Chrome أو Firefox أو Edge
            // بيتم إنشاء ChromeOptions وتحديد الإعدادات اللي عايز تطبقها (زي تكبير الشاشة مثلا)
            // بتنشئ ChromeDriver باستخدام الـ options اللي جهزتها
            // بتحط الـ ChromeDriver ده في ThreadLocal عن طريق driverThreadLocal.set()
            // لو المتصفح مش Chrome أو Firefox بيكون المتصفح الافتراضي هو Edge

        }

        public static WebDriver getDriver()
        {
          return driverThreadLocal.get();
          //جيت درايفر دي الميثود اللي بترجع الـ WebDriver اللي متخزن في الـ ThreadLocal الخاص بالـ Thread اللي شغال دلوقتي
        }

        public static void quitDriver()
        {
            getDriver().quit();
            driverThreadLocal.remove();

            //ده الميثود اللي بيقفل ال WebDriver بعد ما التيست يخلص
            //أول حاجة بتعمل quit للـ WebDriver اللي تم إنشاؤه علشان تقفل البراوزر
            //بعد كده بتعمل remove لل WebDriver من ال ThreadLocal علشان تفضيه وميبقاش فيه بقايا أو مشاكل لما تشغل تست تاني
        }
}
