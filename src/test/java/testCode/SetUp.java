package testCode;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class SetUp {

    private static WebDriver driver;
    //Select browser
    //@Parameters("browser")
    public static WebDriver getWebdriver()
    {
        //String browser = java.lang.System.getProperties().getProperty("webbrowser");
        String browser = "chrome";
        if (browser.equalsIgnoreCase("firefox")) driver = new FirefoxDriver();
        if (browser.equalsIgnoreCase("ie")) driver = new InternetExplorerDriver();
        if (browser.equalsIgnoreCase("chrome")) driver = new ChromeDriver();
        if (browser.equalsIgnoreCase("opera")) driver = new OperaDriver();
        if (browser.equalsIgnoreCase("safari")) driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
