package com.qatest.automation;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class Nonauthshopapp {
	
	private String[] results;
	private WebDriver driver;
    private String username;
    private String password;
	
	public Nonauthshopapp(WebDriver driver, String user, String pass)
	{
		this.driver = driver;
		results = new String[2];
        this.username = user;
        this.password = pass;
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
	    	    	results[0] = "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	    	return results;
	    	    }
                /*if (isElementPresent(By.tagName("input")))
                {
                    driver.findElement(By.tagName("input")).click();
                }  */

	        	if (!isElementPresent(By.id("productinformation-complete")))
	        	{
	        		results[0] = "Order was not completed";
	        		return results;
	        	}
	        	results[1] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
                driver.manage().timeouts().implicitlyWait(500, TimeUnit.MICROSECONDS);
                if (isElementPresent(By.name("_eventId_complete")))
                {
                    driver.findElement((By.name("_eventId_complete"))).click();
                }

                if (isElementPresent(By.xpath("/html/body/form/div/div[7]/div[2]/button")))
                {
                    driver.findElement(By.xpath("/html/body/form/div/div[7]/div[2]/button")).click();
                }
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div[2]/div/div[2]/ul/li/div/div/a")).click();
                Thread.sleep(3000);
                driver.findElement(By.id("defaultLogInForm-username")).clear();
                driver.findElement(By.id("defaultLogInForm-username")).sendKeys(username);
                driver.findElement(By.id("defaultLogInForm-password")).clear();
                driver.findElement(By.id("defaultLogInForm-password")).sendKeys(password);
                driver.findElement(By.id("signinButton2")).click();
                Thread.sleep(5000);
                String[] ordernum = results[1].split(" ");
                if (!driver.getPageSource().contains(ordernum[2]))
                {
                     results[0] = results[1] + " was not found on order status page.";
                }
	        	
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
