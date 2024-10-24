package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
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

    }




    public static void scrolling(WebDriver driver, By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));

    }




    public static void selectingFromDropDown(WebDriver driver, By locator, String option)
    {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);

    }





    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());

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

    }




    public static void clickingOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }




    public static void sendData(WebDriver driver, By locator, String data)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);

         }




    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }




    public static WebDriverWait generalWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }




    public static int generateRandomNumber(int MaxNumber)
    {
        return new Random().nextInt(MaxNumber) + 1;
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
    }




    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
   }



    public static class DataGenerator {

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private static final String NUMBERS = "0123456789";
        private static final String EMAIL_DOMAIN = "@example.com";

        // دالة لتوليد اسم عشوائي
        public static String getRandomString(int length) {
            Random random = new Random();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < length; i++) {
                builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return builder.toString();
        }

        // دالة لتوليد بريد إلكتروني عشوائي
        public static String getRandomEmail() {
            String firstName = getRandomString(6).toLowerCase();
            String lastName = getRandomString(6).toLowerCase();
            return firstName + "." + lastName + NUMBERS.charAt(new Random().nextInt(NUMBERS.length())) + EMAIL_DOMAIN;
        }

        // دالة لتوليد كلمة مرور عشوائية
        public static String getRandomPassword(int length) {
            String charsAndNums = CHARACTERS + NUMBERS;
            Random random = new Random();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < length; i++) {
                builder.append(charsAndNums.charAt(random.nextInt(charsAndNums.length())));
            }
            return builder.toString();
        }

        // دالة لإدخال بيانات عشوائية في الـ WebElements
        public static void fillInFields(WebElement firstNameField, WebElement lastNameField, WebElement emailField, WebElement passwordField) {
            firstNameField.sendKeys(getRandomString(6));
            lastNameField.sendKeys(getRandomString(6));
            emailField.sendKeys(getRandomEmail());
            passwordField.sendKeys(getRandomPassword(10));
        }
    }



}
