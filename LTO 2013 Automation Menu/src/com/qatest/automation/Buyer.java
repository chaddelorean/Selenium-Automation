package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;

public class Buyer {
	private WebDriver driver;
	private String results;
	
	public Buyer(WebDriver driver)
	{
		this.driver = driver;
		results = null;
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}
	
	public String buyerPage(String qualify, String buyer)
	{
		try
		{
			if (isElementPresent(By.id("zip_postalLookup")))
			{
				 driver.findElement(By.id("zip_postalLookup")).clear(); 
				 driver.findElement(By.id("zip_postalLookup")).sendKeys("BM1326");
			}
			
			if (isElementPresent(By.id("user_phone_2")))
			{
				driver.findElement(By.id("user_phone_2")).clear();
		        driver.findElement(By.id("user_phone_2")).sendKeys("456");
			}
			
			if (isElementPresent(By.id("user_phone_3")))
			{
		        driver.findElement(By.id("user_phone_3")).clear();
		        driver.findElement(By.id("user_phone_3")).sendKeys("456");
			}
			
			if (isElementPresent(By.id("buyerID")))
			{
		        driver.findElement(By.id("buyerID")).clear();
		        driver.findElement(By.id("buyerID")).sendKeys(buyer);
			}

            if (isElementPresent(By.id("address_id")))
            {
                driver.findElement(By.id("address_id")).clear();
                driver.findElement(By.id("address_id")).sendKeys(buyer);
            }

            if (isElementPresent(By.id("address_mobile")))
            {
                driver.findElement(By.id("address_mobile")).clear();
                driver.findElement(By.id("address_mobile")).sendKeys("801-444-5555");
            }

            if (isElementPresent(By.id("mobilePhone")))
            {
                driver.findElement(By.id("mobilePhone")).clear();
                driver.findElement(By.id("mobilePhone")).sendKeys("801-444-5555");
            }

			if (isElementPresent(By.id("buyerPhone_2")))
			{
		        driver.findElement(By.id("buyerPhone_2")).clear();
		        driver.findElement(By.id("buyerPhone_2")).sendKeys("456");
			}
			
			if (isElementPresent(By.id("buyerPhone_3")))
			{
		        driver.findElement(By.id("buyerPhone_3")).clear();
		        driver.findElement(By.id("buyerPhone_3")).sendKeys("4565");
			}
			
			if (isElementPresent(By.id("nameOfPerson")))
			{
		        driver.findElement(By.id("nameOfPerson")).clear();
		        driver.findElement(By.id("nameOfPerson")).sendKeys("Test User");
			}
			
			if (isElementPresent(By.id("address_address1")))
			{
		        driver.findElement(By.id("address_address1")).clear();
		        driver.findElement(By.id("address_address1")).sendKeys("75 West Center Street");
			}
			
			if (isElementPresent(By.id("address_address2")))
			{
		        driver.findElement(By.id("address_address2")).clear();
		        driver.findElement(By.id("address_address2")).sendKeys("Test Address");
			}
			
			if (isElementPresent(By.id("address_city")))
			{
				driver.findElement(By.id("address_city")).clear();
			    driver.findElement(By.id("address_city")).sendKeys("Provo");
			}
			
			if (isElementPresent(By.id("distributorID")))
			{
			    driver.findElement(By.id("distributorID")).clear();
			    driver.findElement(By.id("distributorID")).sendKeys(qualify);
			}
			
			if (isElementPresent(By.id("email")))
			{
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys("test@test.com");
			}
		
			if (isElementPresent(By.id("mobile_2")))
			{
				driver.findElement(By.id("mobile_2")).clear();
		        driver.findElement(By.id("mobile_2")).sendKeys("456");
			}
			
			if (isElementPresent(By.id("mobile_3")))
			{
		        driver.findElement(By.id("mobile_3")).clear();
		        driver.findElement(By.id("mobile_3")).sendKeys("456");
			}
	        
			if (isElementPresent(By.id("address_postalCode")))
			{
				driver.findElement(By.id("address_postalCode")).clear();
		        driver.findElement(By.id("address_postalCode")).sendKeys("8460");
			}
	        
			if (isElementPresent(By.id("buyerMobile_2")))
			{
				driver.findElement(By.id("buyerMobile_2")).clear();
		        driver.findElement(By.id("buyerMobile_2")).sendKeys("456");
			}
			
			if (isElementPresent(By.id("buyerMobile_3")))
			{
		        driver.findElement(By.id("buyerMobile_3")).clear();
		        driver.findElement(By.id("buyerMobile_3")).sendKeys("4565");
			}
			
	        if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")))
			{
				driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")).click();
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
