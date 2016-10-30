package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class Element {

    protected By locator;
    protected String name;
    protected WebElement element;
    protected WebDriver driver;

    //constructor
    public Element(WebDriver driver, String name){
        this.driver = driver;
        this.name = name;
        this.locator = By.xpath("//label[contains(.,'" + name + "')]/input");
    }
    //check selection
    public void check()
    {
        WebElement chekedElement = getElement();
        chekedElement.click();
        assertTrue(chekedElement.isSelected());


    }
    public WebElement getElement()
    {
        if(element == null)
        {
            element = driver.findElement(locator);
        }
        return element;
    }

}
