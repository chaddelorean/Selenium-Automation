package com.qatest.automation;

/**
 * Created with IntelliJ IDEA.
 * User: cowens
 * Date: 6/13/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */

import org.openqa.selenium.*;


public class ResetPLQuantity {

    private WebDriver driver;

    public ResetPLQuantity(WebDriver d)
    {
        this.driver = d;
    }

    public void Reset(String user, String event)
    {
        driver.get("http://aws.nuskin.com/api/qualification/pl/"+user+"/"+event+"/clear");
    }
}
