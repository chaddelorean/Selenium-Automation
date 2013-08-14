package com.qatest.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 8/14/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class waitingroom {
    WebDriver driver;

    public waitingroom(WebDriver d)
    {
        driver = d;
    }

    public void wait(String element)
    {
        for (int second = 0; second <= 60; second++)
        {
            try{
                if (isElementPresent(By.id(element)))
                    break;
                if (isElementPresent(By.xpath(element)))
                    break;
                if (isElementPresent(By.name(element)))
                    break;
                if (isElementPresent(By.linkText(element)))
                    break;

                Thread.sleep(1000);
            }
            catch (Exception e)
            {

            }
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
