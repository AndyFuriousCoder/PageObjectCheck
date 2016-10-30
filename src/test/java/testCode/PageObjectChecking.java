package testCode;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import pageObjects.CheckBoxElement;
import pageObjects.LoginElement;
import pageObjects.RadioButtonElement;

import static org.testng.Assert.assertTrue;

public class PageObjectChecking extends AbstractTest{
    private static WebDriver driver; //used WebDriver




    @BeforeTest(alwaysRun = true)
    public static void setUp()
    {
       driver = SetUp.getWebdriver();
    }


    //Login test
    @Test(enabled = true, dataProviderClass = LoginData.class, dataProvider = "LoginTest")
    public void testingLogin(boolean statement, String login, String password) {

        LoginElement loginElement = new LoginElement(driver);
        loginElement.authorize(login, password);
        loginElement.checkAuthorizing(statement);
    }

    @Test(enabled = true)
    public void goingToPage()
    {
        new LoginElement(driver).authorize("epam", "1234");
        //go to page with checkboxes and radioboxes
        driver.navigate().to("https://jdi-framework.github.io/tests/page8.htm");
        assertTrue(false);
    }

    @Test(enabled = true, dataProviderClass = CheckBoxElementData.class, dataProvider = "testingCheckBoxElements")
    public void testingCheckBoxElements(String checkBoxValue)
    {
        //check checkboxes using page object CheckBoxElement
        new CheckBoxElement(driver, checkBoxValue).check();
    }

    @Test(enabled = true, dataProviderClass = RadioButtonElementData.class, dataProvider = "testingRadioButtonsElements")
    public void testingRadioButtonsElements(String radioButtonValue)
    {
        //check radioButton using page object RadioButtonElement
        new RadioButtonElement(driver, radioButtonValue).check();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
