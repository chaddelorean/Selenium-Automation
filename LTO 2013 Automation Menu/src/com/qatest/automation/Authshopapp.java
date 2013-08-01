package com.qatest.automation;

import org.openqa.selenium.*;

public class Authshopapp {
	
	private String[] results;
	private WebDriver driver;
    private String username;
	
	public Authshopapp(WebDriver driver, String user)
	{
		this.driver = driver;
		results = new String[4];
        this.username = user;
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
                //Shop App Home Button
                driver.findElement(By.xpath("/html/body/form/div/div[7]/div[2]/button")).click();
                //Order Status Button
                //driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div[2]/div/div[2]/ul/li/div/div/a")).click();

                String[] ordernum = results[1].split(" ");
                driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div/div[2]/div/div[2]/ul/li[3]/div/div/a")).click();
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("input.orderNumberVal")).clear();
                driver.findElement(By.cssSelector("input.orderNumberVal")).sendKeys(ordernum[2]);
                driver.findElement(By.cssSelector("input.distributorIdVal")).clear();
                driver.findElement(By.cssSelector("input.distributorIdVal")).sendKeys(username);
                driver.findElement(By.linkText("SUBMIT")).click();
                Thread.sleep(5000);
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
