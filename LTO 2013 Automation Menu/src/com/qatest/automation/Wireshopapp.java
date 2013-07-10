package com.qatest.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Wireshopapp {

	private String[] results;
	private WebDriver driver;
    private String username;
    private String password;

	public Wireshopapp(WebDriver driver, String user, String pass)
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

            new Select(driver.findElement(By.id("paymentSelection_order_1"))).selectByVisibleText("Add a WIRE");
            driver.findElement(By.cssSelector("option[value=\"addPaymentType8\"]")).click();
            driver.findElement(By.cssSelector("button.mainbutton")).click();
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
	        	
	        	if (!isElementPresent(By.id("productinformation-complete")))
	        	{
	        		results[0] = "Order was not completed";
	        		return results;
	        	}
	        	results[1] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
                driver.findElement(By.xpath("/html/body/form/div/div[7]/div[2]/button")).click();
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