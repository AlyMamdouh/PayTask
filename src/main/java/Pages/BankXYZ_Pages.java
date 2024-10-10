package Pages;

import Utilities.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BankXYZ_Pages
{
    private final WebDriver driver;
    // ده عبارة عن متغير private
    // برايفت: فايدتها إنه ما يقدرش أي كود خارج كلاس BankXYZ_Pages انه يوصل له
    // فاينال: فايدتها انه هياخد قيمة مرة واحدة بس لما تنشئ الـ object (في الـ constructor) وما تقدرش تغير قيمته بعد كده
    // الـ WebDriver اللي هيتعين للكلاس ده مش هيتغير طول مدة تشغيل الكود. ده بيضمن استقرار أكبر للكود لأنك بتتأكد إن الـ WebDriver اللي بتستخدمه ثابت

    public BankXYZ_Pages(WebDriver driver) {
        this.driver = driver;
    }
    // ده كونستراكتور و بستخدمه ف كل بيدج بعمل فيها تيست
    //لما تنشئ object من الكلاس BankXYZ_Pages، بتحتاج تربطه تمررله الدرايفر
    // السطر بتاع زيس درايفر ده بياخد الدرايفر اللي مررته ويخزنه في المتغير اللي انت عامله فوق خالص
    //فال constructor ده بيوصل الدرايفر اللي جاي من التيست بالكلاس بتاع البيدج دي عشان يخلي البراوزر يتعامل مع الWebElements اللي هنا



    private final By BankManagerLoginBtn = By.xpath("//button[@ng-click='manager()']");
    private final By AddingCustBtn = By.xpath("//button[@ng-click='addCust()']");
    private final By FirstName = By.xpath("//input[@ng-model='fName']");
    private final By LastName = By.xpath("//input[@ng-model='lName']");
    private final By PostCode = By.xpath("//input[@ng-model='postCd']");
    private final By AddCustomerBtn = By.cssSelector("button[type='submit']");
    private final By Home = By.cssSelector(".btn.home");
    private final By CustomLoginBtn = By.cssSelector("button[ng-click='customer()']");

    private final By CustomerDDL = By.id("userSelect");
    private final By CurrencyDDL = By.id("currency");
    private final By ProceedBTN = By.cssSelector("button[type='submit']");
    private final By OpenAccBtn = By.cssSelector(".btn.btn-lg.tab[ng-class='btnClass2']");

    private final By CustomersBtn = By.xpath("//button[@ng-click='showCust()']");
    private final By SearchFld = By.cssSelector("input[placeholder='Search Customer']");
    private final By SecondRow_FirstColumn = By.cssSelector("tbody tr:nth-child(1) td:nth-child(1)");
    private final By TablePostCode = By.xpath("//a[normalize-space()='Post Code']");
    private final By postCodeElements = By.xpath("//table//tbody//tr/td[3]");



    public BankXYZ_Pages clickBankManagerLoginBtn()
    {
        Utility.clickingOnElement(driver, BankManagerLoginBtn);
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages clickAddingCustBtn()
    {
        Utility.clickingOnElement(driver, AddingCustBtn);
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages clickAddCustBtn()
    {
        Utility.clickingOnElement(driver, AddCustomerBtn);
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages clickOpenAccBtn()
    {
        Utility.clickingOnElement(driver, OpenAccBtn);
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages clickHomeBtn()
    {
        Utility.clickingOnElement(driver, Home);
        return new BankXYZ_Pages(driver);
    }



    public BankXYZ_Pages clickonCustomLogin()
    {
        Utility.clickingOnElement(driver, CustomLoginBtn);
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages clickProceedBtn()
    {
        Utility.clickingOnElement(driver, ProceedBTN);
        return new BankXYZ_Pages(driver);
    }

    public BankXYZ_Pages clickonCustomers()
    {
        Utility.clickingOnElement(driver, CustomersBtn);
        return new BankXYZ_Pages(driver);
    }

    public BankXYZ_Pages clickonPostCode()
    {
        Utility.clickingOnElement(driver, TablePostCode);
        Utility.generalWait(driver);
        return new BankXYZ_Pages(driver);
    }














    public BankXYZ_Pages EnterFirstN(String firstN)
    {
        Utility.sendData(driver, FirstName, firstN);
        return this;
    }

    public BankXYZ_Pages EnterLastN(String lastN)
    {
        Utility.sendData(driver, LastName, lastN);
        return this;
    }

    public BankXYZ_Pages EnterPostcode(String PCode)
    {
        Utility.sendData(driver, PostCode, PCode);
        return this;
    }

    public BankXYZ_Pages SearchByName(String Name)
    {
        Utility.sendData(driver, SearchFld, Name);
        Utility.generalWait(driver);
        return this;
    }





















    public BankXYZ_Pages ChooseCustomer()
    {
        Utility.selectingFromDropDown(driver,CustomerDDL,"Aly Mamdouh");
        return new BankXYZ_Pages(driver);
    }

    public BankXYZ_Pages ChooseName()
    {
        Utility.selectingFromDropDown(driver,CustomerDDL,"Aly Mamdouh");
        return new BankXYZ_Pages(driver);
    }


    public BankXYZ_Pages ChooseCurrency()
    {
        Utility.selectingFromDropDown(driver,CurrencyDDL,"Dollar");
        return new BankXYZ_Pages(driver);
    }










    public BankXYZ_Pages CheckSearching(String Name)
    {
        Utility.sendData(driver, SearchFld, Name);
        Utility.generalWait(driver);

        String cellText = SecondRow_FirstColumn.toString();

        if (cellText.contains(Name))
        {
            System.out.println("Existed!");
        }
        else
        {
            System.out.println("Not Existed!");
        }
        return this;
    }




    public BankXYZ_Pages checkCustAdding()
    {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if(alertText.contains("Customer added successfully"))
        {
            System.out.println("Message appeared successfully");

        }
        else
        {
            System.out.println("Message didn't appear successfully");
        }
        alert.accept();
        return new BankXYZ_Pages(driver);

    }



    public BankXYZ_Pages checkproceeding()
    {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();


        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(alertText);
        String alertAccountNumber = "";
        if (matcher.find())
        {
            alertAccountNumber = matcher.group();

            System.out.println("Acc. Number is" + alertAccountNumber);
        }



        if(alertText.contains("Account created successfully with account Number"))
        {
            System.out.println("Message appeared successfully");

        }
        else
        {
            System.out.println("Message didn't appear successfully");
        }
        alert.accept();
        return new BankXYZ_Pages(driver);

    }



    public BankXYZ_Pages checkSortingOrder()
    {
        List<WebElement> elements = Utility.getElementsAsList(driver, postCodeElements);

        List<String> postCodes = new ArrayList<>();

        // جمع النصوص من عناصر عمود "Post Code"
        for (WebElement element : elements)
        {
            postCodes.add(element.getText().trim());
        }

        // عمل مقارنة مخصصة لترتيب النصوص والأرقام معًا
        Comparator<String> alphanumericComparator = (s1, s2) -> {
            // فصل الأرقام من النصوص
            String num1 = s1.replaceAll("[^\\d]", "");
            String num2 = s2.replaceAll("[^\\d]", "");
            int result = num1.compareTo(num2);

            // إذا كانت الأرقام متساوية، نقارن النصوص
            if (result == 0)
            {
                return s1.compareTo(s2);
            }
            return result;
        };

        // عمل نسخة مرتبة تصاعديًا
        List<String> sortedAsc = new ArrayList<>(postCodes);
        Collections.sort(sortedAsc, alphanumericComparator);

        // عمل نسخة مرتبة تنازليًا
        List<String> sortedDesc = new ArrayList<>(postCodes);
        Collections.sort(sortedDesc, alphanumericComparator.reversed());

        // مقارنة القائمة الأصلية مع النسخ المرتبة
        if (postCodes.equals(sortedAsc))
        {
            System.out.println("Items are ordered : Ascending");
        }
        else if (postCodes.equals(sortedDesc))
        {
            System.out.println("Items are ordered : Descending");
        }
        else
        {
            System.out.println("Items are NOT ordered neither Ascending nor Descending");
        }

        return new BankXYZ_Pages(driver);
    }

}

