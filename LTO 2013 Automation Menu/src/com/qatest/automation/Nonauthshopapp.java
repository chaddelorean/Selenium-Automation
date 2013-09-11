package com.qatest.automation;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class Nonauthshopapp {
	
	private String[] results;
	private WebDriver driver;
    private String username;
	
	public Nonauthshopapp(WebDriver driver, String user)
	{
		this.driver = driver;
		results = new String[4];
        this.username = user;
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

            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if (isElementPresent(By.xpath("/html/body/form/div/div[22]/div/div/button")) && driver.findElement(By.xpath("/html/body/form/div/div[22]/div/div/button")).isDisplayed())
            {
                driver.findElement(By.xpath("/html/body/form/div/div[22]/div/div/button")).click();
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		    driver.findElement(By.cssSelector("option[value=\"addPaymentType0\"]")).click();
	        driver.findElement(By.id("paymentSelection_order_1_paymentNumber_id")).clear();
	        driver.findElement(By.id("paymentSelection_order_1_paymentNumber_id")).sendKeys("4111111111111111");
	        driver.findElement(By.id("paymentSelection_order_1_paymentName_id")).clear();
	        driver.findElement(By.id("paymentSelection_order_1_paymentName_id")).sendKeys("Bob User");
	        driver.findElement(By.id("paymentSelection_order_1_paymentSecurityNumber")).clear();
	        driver.findElement(By.id("paymentSelection_order_1_paymentSecurityNumber")).sendKeys("456");
	        //driver.findElement(By.xpath("/html/body/form/div/div[7]/div/div[5]/div/div/div/div[6]/div[3]/div[2]/button")).click();
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
                String[] ordernum = results[1].split(" ");
                driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div[2]/div/div[2]/ul/li[3]/div/div/a")).click();
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("input.orderNumberVal")).clear();
                driver.findElement(By.cssSelector("input.orderNumberVal")).sendKeys(ordernum[3]);
                driver.findElement(By.cssSelector("input.distributorIdVal")).clear();
                driver.findElement(By.cssSelector("input.distributorIdVal")).sendKeys(username);
                driver.findElement(By.linkText("SUBMIT")).click();
                Thread.sleep(5000);

                if (!driver.getPageSource().contains(ordernum[3]))
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
