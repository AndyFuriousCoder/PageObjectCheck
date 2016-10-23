package testCode;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.CheckBoxElement;
import pageObjects.LoginElement;
import pageObjects.RadioButtonElement;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class PageObjectChecking {
    private WebDriver driver; //used WebDriver
  

    //Select browser
    @Parameters("browser")

    @BeforeTest(alwaysRun = true)
    public void selectBrowser(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) driver = new FirefoxDriver();
        if (browser.equalsIgnoreCase("ie")) driver = new InternetExplorerDriver();
        if (browser.equalsIgnoreCase("chrome")) driver = new ChromeDriver();
        if (browser.equalsIgnoreCase("opera")) driver = new OperaDriver();
        if (browser.equalsIgnoreCase("safari")) driver = new SafariDriver();
    }

    @BeforeClass(alwaysRun = true)
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
        }
        else {
            assertTrue((driver.findElement(By.xpath("//button[@type='submit']"))).isEnabled());
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

        //get screenshot
        File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        String path = "./src/test/java/logsAndScreenshots/screenshots/" + screenshot.getName();
        try
        {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (Exception e) {}
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        //Logs--------------------------------------------
        Logs logs = driver.manage().logs();
        LogEntries logEntries = logs.get(LogType.DRIVER);
        try{
        FileWriter writer = new FileWriter("./src/test/java/logsAndScreenshots/Logs.txt");
            for (LogEntry logEntry : logEntries) {
                writer.write(logEntry.getMessage() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception o) {System.out.println("File not found!");}
        //-------------------------------------------------

        driver.close();
        driver.quit();
    }
}
