package com.qatest.automation;

/**
 * Created with IntelliJ IDEA.
 * User: chad
 * Date: 6/25/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Omniturevalidation {
    private WebDriver driver;
    private TreeMap<String, String> omnivariables;
    private TreeMap<String, String> checkvariables;
    private String baseWindow;

    public Omniturevalidation(WebDriver d)
    {
        this.driver = d;
        omnivariables = new TreeMap<String, String>();
        checkvariables = new TreeMap<String, String>();
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
            for (Map.Entry<String, String> item : omnivariables.entrySet())
            {
                if (item.getValue().contains(",") || item.getValue().contains(";"))
                {
                    item.setValue(item.getValue().replace(",", ""));
                    item.setValue(item.getValue().replace(";", ""));
                }
            }
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
        for (Map.Entry<String, String> text: checkvariables.entrySet())
        {
            System.out.println("Check: " + text.getValue() + " Omni: " + omnivariables.get(text.getKey()));
            if (text.getValue().equals(""))
            {
                if (omnivariables.get(text.getKey()).equals(null))
                {
                    return false;
                }
            }
            else if (!text.getValue().equalsIgnoreCase(omnivariables.get(text.getKey())))
            {
                return false;
            }
        }
        return true;
    }

    public boolean initializeCheckVariables(String fileLocation)
    {
        try
        {
            if (fileLocation.equals(null))
            {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Open Omniture .CSV File");
                fc.setMultiSelectionEnabled(false);
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Values (CSV)", "csv");
                fc.setFileFilter(filter);
                int option = fc.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    fileLocation = fc.getSelectedFile().getAbsolutePath();
                }
            }

            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] parse = line.split(",");
                if (parse[0].equalsIgnoreCase("Variables"))
                    continue;
                if (parse.length == 2)
                    checkvariables.put(parse[0], parse[1]);
                else if (parse.length == 1)
                    checkvariables.put(parse[0], "");
                else
                {
                    JOptionPane.showMessageDialog(null,"CSV File is Corrupt. Please remake CSV file.","CSV File Corrupt", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
            }

            br.close();
        }

        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public void createOmniCSV(String location)
    {
        try
        {
            if (location.equals(null))
            {
                final JFileChooser fc = new JFileChooser();
                String fileLocation;
                fc.setDialogTitle("Save Omniture .CSV File");
                fc.setMultiSelectionEnabled(false);
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Values (CSV)", "csv");
                fc.setFileFilter(filter);
                int option = fc.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    location = fc.getSelectedFile().getAbsolutePath();

                }
            }

            getOmnitureDebuggerPage();

            if (!location.contains(".csv"))
            {
                location += ".csv";
            }
            PrintWriter omniCSV = new PrintWriter(location, "UTF-8");
            omniCSV.append("Variables,Values\n");
            for (Map.Entry<String, String> item : omnivariables.entrySet())
            {
                omniCSV.append(item.getKey() + "," + item.getValue() + "\n");
            }
            omniCSV.close();

        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
