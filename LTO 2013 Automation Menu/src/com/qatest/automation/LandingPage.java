package com.qatest.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

public class LandingPage {
	WebDriver driver;
	String results;
	
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
		results = null;
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}
	
	public String landing(String username, String password)
	{
		try
		{
			if (isElementPresent(By.id("promoCode")))
			{
				driver.findElement(By.id("promoCode")).clear();
				driver.findElement(By.id("promoCode")).sendKeys("testcode");
			}
			
			if (isElementPresent(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div[2]/div/a")))
				driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div[2]/div/a")).click();
			
			if (isElementPresent(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")))
				driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();

            if (isElementPresent(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/div/a")))
                driver.findElement((By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/div/a"))).click();
			
		    try{
		    	Thread.sleep(3000);
		    }
		    
		    catch (InterruptedException ex)
		    {
		    	Thread.currentThread().interrupt();
		    }
		    if (isElementPresent(By.id("defaultLogInForm-username")) && isElementPresent(By.id("defaultLogInForm-password")))
		    {
			    driver.findElement(By.id("defaultLogInForm-username")).clear();
			    driver.findElement(By.id("defaultLogInForm-username")).sendKeys(username);
			    driver.findElement(By.id("defaultLogInForm-password")).clear();
			    driver.findElement(By.id("defaultLogInForm-password")).sendKeys(password);
			    driver.findElement(By.id("signinButton2")).click();
		    }
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    return results;
		}
		catch (Exception e)
		{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
