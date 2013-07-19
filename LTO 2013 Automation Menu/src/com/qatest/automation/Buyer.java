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
	
	public String buyerPage(BuyerDataForm data)
	{
		try
		{

            if (isElementPresent(By.id("zip_postalLookup")) && driver.getCurrentUrl().equals("http://test.nuskin.com/content/lto/2013/sea/en_MY/buyerOther.html"))
            {
                driver.findElement(By.id("zip_postalLookup")).clear();
                driver.findElement(By.id("zip_postalLookup")).sendKeys("79000");
            }

            else if (isElementPresent(By.id("zip_postalLookup")))
            {
				 driver.findElement(By.id("zip_postalLookup")).clear(); 
				 driver.findElement(By.id("zip_postalLookup")).sendKeys("BM1326");
			}

            if (isElementPresent(By.id("distributorID")))
            {
                driver.findElement(By.id("distributorID")).clear();
                driver.findElement(By.id("distributorID")).sendKeys(data.getDistID());
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
		        driver.findElement(By.id("buyerID")).sendKeys(data.getBuyerID());
			}

            if (isElementPresent(By.id("address_id")))
            {
                driver.findElement(By.id("address_id")).clear();
                driver.findElement(By.id("address_id")).sendKeys(data.getBuyerID());
            }

            if (isElementPresent(By.id("address_mobile")))
            {
                driver.findElement(By.id("address_mobile")).clear();
                driver.findElement(By.id("address_mobile")).sendKeys(data.getPhone());
            }

            if (isElementPresent(By.id("mobile")) && !driver.findElement(By.id("mobile")).getAttribute("type").equals("hidden"))
            {
                driver.findElement(By.id("mobile")).clear();
                driver.findElement(By.id("mobile")).sendKeys(data.getPhone());
            }

            if (isElementPresent(By.id("mobilePhone")))
            {
                driver.findElement(By.id("mobilePhone")).clear();
                driver.findElement(By.id("mobilePhone")).sendKeys(data.getPhone());
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
		        driver.findElement(By.id("address_address1")).sendKeys(data.getAddress());
			}
			
			if (isElementPresent(By.id("address_address2")))
			{
		        driver.findElement(By.id("address_address2")).clear();
		        driver.findElement(By.id("address_address2")).sendKeys("Test Address Line 2");
			}
			
			if (isElementPresent(By.id("address_city")) && driver.findElement(By.id("address_city")).isDisplayed())
			{
				driver.findElement(By.id("address_city")).clear();
			    driver.findElement(By.id("address_city")).sendKeys(data.getAddress());
			}

			if (isElementPresent(By.id("email")))
			{
			    driver.findElement(By.id("email")).clear();
			    driver.findElement(By.id("email")).sendKeys(data.getEmail());
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
		        driver.findElement(By.id("address_postalCode")).sendKeys(data.getPostalcode());
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

            if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/a")))
            {
                driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/a")).click();
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

        catch (ElementNotVisibleException e)
        {
            return false;
        }
	  }
}
