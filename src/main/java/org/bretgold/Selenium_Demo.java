package org.bretgold;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Selenium_Demo {

    private WebDriver driver;
    private JavascriptExecutor js;
    private static String browser = "chrome";
    private static String currentTime = DateTimeFormatter.ofPattern("MMddyy_HHmmss").format(LocalDateTime.now());

    @Before
    public void setUp() {
        js = (JavascriptExecutor) driver;
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chromedriver","resources\\drivers\\chrome\\chromedriver");
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
        }
        File directory = new File("./results/" + currentTime + "/screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @After
    public void tearDown() {
        driver.quit();

    }

    public WebDriverWait waitTime(int wait) {
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(wait));
        return waitTime;
    }

    public ExpectedConditions visibleElement(String elementName, String elementType) {
        ExpectedConditions expected = null;
        switch (elementType) {
            case "id":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.id(elementName));
                return expected;
            case "xpath":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.xpath(elementName));
                return expected;
            case "className":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.className(elementName));
                return expected;
            case "name":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.name(elementName));
                return expected;
            case "cssSelector":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementName));
                return expected;
            case "linkText":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.linkText(elementName));
                return expected;
            case "partialLinkText":
                expected = (ExpectedConditions) ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(elementName));
                return expected;
        }
        return expected;
    }

    @Test
    public void Test_1() throws InterruptedException {
        driver.get("https://google.com");
        waitTime(300).until(ExpectedConditions.visibilityOfElementLocated(By.className("gLFyf")));
        driver.findElement(By.className("gLFyf")).sendKeys("Who's your daddy?");
//        Thread.sleep(Duration.ofSeconds(300));
        driver.findElement(By.className("gLFyf")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

//        id="APjFqb"

    }

}
