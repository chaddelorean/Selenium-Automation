package com.qatest.automation;

/**
 * Created with IntelliJ IDEA.
 * User: chad
 * Date: 6/25/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;

import java.io.File;
import java.security.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Omniturevalidation {
    private WebDriver driver;
    private TreeMap<String, String> omnivariables;
    private String baseWindow;

    public Omniturevalidation(WebDriver d)
    {
        this.driver = d;
        omnivariables = new TreeMap<String, String>();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    public void getOmnitureDebuggerPage()
    {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("javascript:void(window.open(\"\",\"dp_debugger\",\"width=600,height=600,location=0,menubar=0,status=1,toolbar=0,resizable=1,scrollbars=1\").document.write(\"<script language=\\\"JavaScript\\\" id=dbg src=\\\"http://www.digitalpulse.omniture.com/dp/debugger.js\\\"></\"+\"script>\"));");
            Set windowsids = driver.getWindowHandles();
            Iterator iter = windowsids.iterator();
            baseWindow = iter.next().toString();
            String OmnitureWindow = iter.next().toString();
            driver.switchTo().window(OmnitureWindow);
            String source = driver.getPageSource();

            Date date = new Date();
            String curdate = trimSpace(String.valueOf(date.getTime()));
            PrintWriter omniSource = new PrintWriter(curdate + "omniSource.html", "UTF-8");
            omniSource.print(source);
            omniSource.close();
            driver.close();
            File omniFile = new File(curdate + "omniSource.html");
            WebDriver omnidriver = new FirefoxDriver();
            omnidriver.get("file://" + omniFile.getAbsolutePath());

            WebElement table = omnidriver.findElement(By.className("debugtable"));
            List<WebElement> allTDElements = table.findElements(By.tagName("td"));

            int i = 1;
            for (WebElement element: allTDElements)
            {
                if (element.getAttribute("align").equals("right"))
                {
                    //System.out.println(element.getText() + " Before");
                    String xpath;
                    if (i == 1)
                        xpath = "//table/tbody/tr[2]/td/table/tbody/tr/td[2]";
                    else
                        xpath = "//table/tbody/tr[2]/td/table/tbody/tr[" + (i) + "]/td[2]";

                    omnivariables.put(element.getText(), table.findElement(By.xpath(xpath)).getText());
                    i++;
                }
            }
            omniFile.delete();
            omnidriver.close();
            driver.switchTo().window(baseWindow);
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        }

        catch (Exception e)
        {
            System.out.println(e);
            driver.close();
            driver.switchTo().window(baseWindow);
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        }

    }

    public String trimSpace(String temp)
    {
        char[] tempchar = temp.toCharArray();
        String newString = "";
        for (int i = 0; i < tempchar.length; i++)
        {
            if (tempchar[i] != ' ' && tempchar[i] != ':')
                newString += tempchar[i];
        }

        return newString;
    }

    public boolean compareOmniVariables()
    {
        for (Map.Entry<String, String> text: omnivariables.entrySet())
        {
            System.out.println(text.getKey() + ": " +text.getValue());
        }
        return false;
    }

    public void initializeCheckVariables(String path)
    {


    }

    public void createOmniCSV()
    {


    }

}
