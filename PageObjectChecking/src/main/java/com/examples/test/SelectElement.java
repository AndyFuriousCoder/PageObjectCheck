package com.examples.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectElement{

    private Select select;

    public SelectElement(WebDriver driver, String name)
    {
       new Select(driver.findElement(By.cssSelector("select.uui-form-element"))).selectByVisibleText(name);
    }
}
