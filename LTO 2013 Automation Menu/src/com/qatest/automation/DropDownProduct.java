package com.qatest.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;

public class DropDownProduct {
	private WebDriver driver;
	private String results;
	
	public DropDownProduct(WebDriver driver)
	{
		this.driver = driver;
		results = null;
	}
	
	public String Product()
	{
		try
		{
			new Select(driver.findElement(By.name("qty"))).selectByVisibleText("1");
		    driver.findElement(By.id("checkout")).click();
		    if (isElementPresent(By.className("shopError")))
		    {
		    	results = "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
		    	return results;
		    }
		    
		    return results;
		}
		
		catch (Exception e)
		{
			results = "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
			return results;
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
