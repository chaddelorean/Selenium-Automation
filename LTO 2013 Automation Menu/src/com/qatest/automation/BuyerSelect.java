package com.qatest.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 7/25/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuyerSelect {

    private WebDriver driver;

    public BuyerSelect(WebDriver driver)
    {
        this.driver = driver;
    }

    public String Select(String flow)
    {
        try
        {
            if (flow.equals("myself"))
            {
                //buyer select radio button
                driver.findElement(By.id("radio1")).click();
                //buyer select continue button
                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div")).click();
            }

            if (flow.equals("other"))
            {
                //buyer select radio button
                driver.findElement(By.id("radio2")).click();
                //buyer select continue button
                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div")).click();
            }
        }

        catch (Exception e)
        {
            return e.toString();
        }

        return null;
    }
}
