package com.examples.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class PageObjectChecking {
    private WebDriver driver;

    @Parameters("browser")

    @BeforeClass(alwaysRun = true)
    public void selectBrowser(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) driver = new FirefoxDriver();
        if (browser.equalsIgnoreCase("ie")) driver = new InternetExplorerDriver();
        if (browser.equalsIgnoreCase("chrome")) driver = new ChromeDriver();
        if (browser.equalsIgnoreCase("opera")) driver = new OperaDriver();
        if (browser.equalsIgnoreCase("safari")) driver = new SafariDriver();
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() {
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       driver.manage().window().maximize();

    }

    //Login test
    @Test(enabled = true, dataProviderClass = LoginData.class, dataProvider = "LoginTest")
    //use DataProvider
    public void testingLogin(boolean statement, String login, String password) {

        new LoginElement(driver).authorize(login, password);
        //check authorizing
        if (statement) {
            WebElement logoutElement = driver.findElement(By.cssSelector(".logout"));
            assertTrue((logoutElement).isEnabled());
            logoutElement.click();
            System.out.println("Login test done successfuly!");
        }
        else {
            assertTrue((driver.findElement(By.xpath("//button[@type='submit']"))).isEnabled());
            System.out.println("Login test done successfuly!");
        }
    }
    //PageObject test
    @Test(enabled = true, dependsOnMethods = "testingLogin")
    public void testingPageObjects()
    {
        new LoginElement(driver).authorize("epam", "1234");
        //go to page with checkboxes and radioboxes
        driver.navigate().to("https://jdi-framework.github.io/tests/page8.htm");
        //check checkboxes using page object CheckBoxElement
        new CheckBoxElement(driver, "Water").check();
        new CheckBoxElement(driver, "Earth").check();
        new CheckBoxElement(driver, "Wind").check();
        new CheckBoxElement(driver, "Fire").check();
        //check radioboxes using page object RadioButtonElement
        new RadioButtonElement(driver, "Gold").check();
        new RadioButtonElement(driver, "Silver").check();
        new RadioButtonElement(driver, "Bronze").check();
        new RadioButtonElement(driver, "Selen").check();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
