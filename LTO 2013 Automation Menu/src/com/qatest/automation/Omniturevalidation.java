package com.qatest.automation;

/**
 * Created with IntelliJ IDEA.
 * User: chad
 * Date: 6/25/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Omniturevalidation {
    private WebDriver driver;
    private TreeMap<String, String> omnivariables;

    public Omniturevalidation(WebDriver d)
    {
        this.driver = d;
        omnivariables = new TreeMap<String, String>();
    }

    public void getOmnitureDebuggerPage()
    {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("javascript:void(window.open(\"\",\"dp_debugger\",\"width=600,height=600,location=0,menubar=0,status=1,toolbar=0,resizable=1,scrollbars=1\").document.write(\"<script language=\\\"JavaScript\\\" id=dbg src=\\\"http://www.digitalpulse.omniture.com/dp/debugger.js\\\"></\"+\"script>\"));");
            Set windowsids = driver.getWindowHandles();
            Iterator iter = windowsids.iterator();
            String baseWindow = iter.next().toString();
            String OmnitureWindow = iter.next().toString();
            driver.switchTo().window(OmnitureWindow);
            String source = driver.getPageSource();

            //Thread.sleep(10000);
            //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement table = driver.findElement(By.className("debugtable"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            List <WebElement> cells = null;
            for (WebElement r : rows)
            {
                cells = r.findElements((By.tagName("td")));
                for (WebElement c : cells)
                {
                    System.out.println(c.findElement(By.tagName("td")).getText());
                }
            }

            driver.close();
            driver.switchTo().window(baseWindow);
        }

        catch (Exception e)
        {
            System.out.println(e);

        }

    }

    public boolean compareOmniVariables()
    {

        return false;
    }

}
