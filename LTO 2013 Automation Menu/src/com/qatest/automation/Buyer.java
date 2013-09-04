package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;

public class Buyer {
	private WebDriver driver;
	private String results;
    private boolean stopOnBuyer;
	
	public Buyer(WebDriver driver, boolean stop)
	{
        stopOnBuyer = stop;
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
				 driver.findElement(By.id("zip_postalLookup")).sendKeys("57384");
			}

            if (isElementPresent(By.id("distributorID")) && !isAttributePresent("distributorID", "readonly") && driver.findElement(By.id("distributorID")).isDisplayed())
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

            if (isElementPresent(By.id("address_id")) && !isAttributePresent("address_id", "readonly"))
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
			
			if (isElementPresent(By.id("address_address1")) && !isAttributePresent("address_address1", "readonly"))
			{
		        driver.findElement(By.id("address_address1")).clear();
		        driver.findElement(By.id("address_address1")).sendKeys(data.getAddress());
			}
			
			if (isElementPresent(By.id("address_address2")) && !isAttributePresent("address_address2", "readonly"))
			{
		        driver.findElement(By.id("address_address2")).clear();
		        driver.findElement(By.id("address_address2")).sendKeys("Test Address Line 2");
			}
			
			if (isElementPresent(By.id("address_city")) && driver.findElement(By.id("address_city")).isDisplayed() && !isAttributePresent("address_address1", "readonly"))
			{
				driver.findElement(By.id("address_city")).clear();
			    driver.findElement(By.id("address_city")).sendKeys(data.getAddress());
			}

            if (isElementPresent(By.id("address_district")) && !isAttributePresent("address_district", "readonly"))
            {
                driver.findElement(By.id("address_district")).clear();
                driver.findElement(By.id("address_district")).sendKeys(data.getCity());
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
	        
			if (isElementPresent(By.id("address_postalCode")) && !isAttributePresent("address_postalCode", "readonly"))
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

            if (isElementPresent(By.id("address_region_selector")))
            {
                driver.findElement(By.id("address_region_selector")).click();
                driver.findElement(By.xpath("//div[@id='address_region_rightcol']/div/ul/li[2]")).click();
            }

            if (isElementPresent(By.id("instructions")))
            {
                driver.findElement(By.id("instructions")).clear();
                driver.findElement(By.id("instructions")).sendKeys("Shipping Instructions Field");
            }

            if (!stopOnBuyer)
            {
                if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")))
                {
                    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")).click();
                }

                if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/a")))
                {
                    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/a")).click();
                }
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

    public boolean isAttributePresent(String id, String attribute)
    {
        try{
            if (driver.findElement(By.id(id)).getAttribute(attribute).equals("true"))
            {
                return true;
            }
        }

        catch (NullPointerException e)
        {
            return false;
        }
        return false;
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
