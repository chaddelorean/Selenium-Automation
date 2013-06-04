package com.qatest.automation;

import org.openqa.selenium.*;

public class Authshopapp {
	
	private String[] results;
	private WebDriver driver;
	
	public Authshopapp(WebDriver driver)
	{
		this.driver = driver;
		results = new String[2];
	}
	
	public String[] ShopApp(boolean place)
	{
		//shop app
	    try{

	    	driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).clear();
		    driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).sendKeys("123");
		    driver.findElement(By.id("agreeToTerms2")).click();
		    if (place)
		    {
		    	driver.findElement(By.xpath("/html/body/form/div/div[13]/div/button")).click();
		    	if (isElementPresent(By.className("shopError")))
			    {
			    	results[0] = "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
			    	return results;
			    }
		    	if (!isElementPresent(By.id("productinformation-complete")))
		    	{
			    	results[0] = "United Kingdom: Failed - Order did not take place";
			    	return results;
		    	}
		    	results[1] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
		    }
	        return results;
	    }
	    
	    catch (Exception e)
	    {
	    	results[0] = "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
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
