package com.qatest.automation;

import org.openqa.selenium.*;

public class Nonauthshopapp {
	
	private String[] results;
	private WebDriver driver;
	
	public Nonauthshopapp(WebDriver driver)
	{
		this.driver = driver;
		results = new String[2];
	}
	
	public String[] ShopApp(boolean place)
	{
		//shop app
	    try{

			try{
		    	Thread.sleep(100);
		    }
		    
		    catch (InterruptedException ex)
		    {
		    	Thread.currentThread().interrupt();
		    }
		    
		    driver.findElement(By.cssSelector("option[value=\"addPaymentType0\"]")).click();
	        driver.findElement(By.id("paymentNumber_id")).clear();
	        driver.findElement(By.id("paymentNumber_id")).sendKeys("4111111111111111");
	        driver.findElement(By.id("paymentName_id")).clear();
	        driver.findElement(By.id("paymentName_id")).sendKeys("bob");
	        driver.findElement(By.id("paymentSecurityNumber")).clear();
	        driver.findElement(By.id("paymentSecurityNumber")).sendKeys("456");
	        driver.findElement(By.xpath("/html/body/form/div/div[7]/div/div[5]/div/div/div/div[6]/div[3]/div[2]/button")).click();
		    try{
		    	Thread.sleep(5000);
		    }
		    
		    catch (InterruptedException ex)
		    {
		    	Thread.currentThread().interrupt();
		    }
		    driver.findElement(By.id("agreeToTerms2")).click();
	        if (place)
	        {
	        	driver.findElement(By.xpath("/html/body/form/div/div[13]/div/button")).click();
	        	if (isElementPresent(By.className("shopError")))
	    	    {
	    	    	results[0] = "Brunei: Failed: Someone Else After Shop\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	    	return results;
	    	    }
	        	
	        	if (!isElementPresent(By.id("productinformation-complete")))
	        	{
	        		results[0] = "Brunei: Failed: Order was not completed";
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
