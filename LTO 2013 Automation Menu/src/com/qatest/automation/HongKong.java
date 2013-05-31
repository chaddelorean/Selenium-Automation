package com.qatest.automation;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HongKong {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public String[] testHongKong(boolean place) throws Exception {
	  Myself(place);
	  if (results[0].equals("Hong Kong: Passed"))
		  someoneElse(place);
	  
	  return results;
  }
  
  public String[] Myself(boolean place)
  {
	  try{
	  	driver.get(baseUrl + "/content/lto/2013.html");
	    //global landing page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[3]/a")).click();
	    //Hong Kong landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyer page info
	    driver.findElement(By.id("distributorID")).clear();
	    driver.findElement(By.id("distributorID")).sendKeys("HK1111111");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@test.com");
	    //driver.findElement(By.cssSelector("a.selector")).click();
	    //driver.findElement(By.xpath("//div[@id='user_phone_1_rightcol']/div/ul/li[2]")).click();
	    driver.findElement(By.id("user_phone_2")).clear();
	    driver.findElement(By.id("user_phone_2")).sendKeys("555");
	    driver.findElement(By.id("user_phone_3")).clear();
	    driver.findElement(By.id("user_phone_3")).sendKeys("5555");
	    //driver.findElement(By.cssSelector("#address_country_rightcol > div.custom.dropdown > a.selector")).click();
	   // driver.findElement(By.xpath("//div[@id='address_country_rightcol']/div/a[2]")).click();
	   // driver.findElement(By.cssSelector("#address_country_rightcol > div.custom.dropdown > ul > li")).click();
	    driver.findElement(By.id("nameOfPerson")).clear();
	    driver.findElement(By.id("nameOfPerson")).sendKeys("Test User");
	    driver.findElement(By.id("address_address1")).clear();
	    driver.findElement(By.id("address_address1")).sendKeys("75 West Center Street");
	    driver.findElement(By.id("address_address2")).clear();
	    driver.findElement(By.id("address_address2")).sendKeys("Test Address");
	    //driver.findElement(By.linkText("South")).click();
	    //driver.findElement(By.cssSelector("#district_rightcol > div.custom.dropdown > ul > li")).click();
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")).click();
	    
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    //new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
	    {
	    	results[0] = "Hong Kong: Failed: My Self\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
	    	return results;
	    }

	    //shop app
	    try{
	    	Thread.sleep(5000);
	    }
	    
	    catch (InterruptedException ex)
	    {
	    	Thread.currentThread().interrupt();
	    }
	    driver.findElement(By.cssSelector("option[value=\"addPaymentType0\"]")).click();
	    driver.findElement(By.id("paymentNumber_id")).clear();
        driver.findElement(By.id("paymentNumber_id")).sendKeys("4111111111111111");
        driver.findElement(By.id("paymentName_id")).clear();
        driver.findElement(By.id("paymentName_id")).sendKeys("Bob");
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
	    if (place)
	    {
	    	driver.findElement(By.xpath("/html/body/form/div/div[12]/div/button")).click();
	    	if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
		    {
		    	results[0] = "Hong Kong: Failed: My Self\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
		    	return results;
		    }
	    	if (!isElementPresent(By.id("productinformation-complete")))
	    	{
		    	results[0] = "United States: Failed - Order did not take place";
		    	return results;
	    	}
	    	results[1] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
	    }
	    results[0] = "Hong Kong: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Hong Kong: Failed: My Self \n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place)
  {
	  try{
		  	driver.get(baseUrl + "/content/lto/2013.html");
		    //global landing page
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[3]/a")).click();
		    //Hong Kong landing page - Order Now button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
		    //buyer select radio button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
		    //buyer select continue button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
		    
		    //buyer page info
		    driver.findElement(By.id("distributorID")).clear();
		    driver.findElement(By.id("distributorID")).sendKeys("HK1111111");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("test@test.com");
		    //driver.findElement(By.cssSelector("a.selector")).click();
		    //driver.findElement(By.xpath("//div[@id='user_phone_1_rightcol']/div/ul/li[2]")).click();
		    driver.findElement(By.id("user_phone_2")).clear();
		    driver.findElement(By.id("user_phone_2")).sendKeys("555");
		    driver.findElement(By.id("user_phone_3")).clear();
		    driver.findElement(By.id("user_phone_3")).sendKeys("5555");
		    driver.findElement(By.id("buyerID")).clear();
		    driver.findElement(By.id("buyerID")).sendKeys("US8128558");
		    driver.findElement(By.id("buyerPhone_2")).clear();
		    driver.findElement(By.id("buyerPhone_2")).sendKeys("456");
		    driver.findElement(By.id("buyerPhone_3")).clear();
		    driver.findElement(By.id("buyerPhone_3")).sendKeys("4562");
		   // driver.findElement(By.cssSelector("#address_country_rightcol > div.custom.dropdown > a.selector")).click();
		    //driver.findElement(By.xpath("//div[@id='address_country_rightcol']/div/a[2]")).click();
		    //driver.findElement(By.cssSelector("#address_country_rightcol > div.custom.dropdown > ul > li")).click();
		    driver.findElement(By.id("nameOfPerson")).clear();
		    driver.findElement(By.id("nameOfPerson")).sendKeys("Test User");
		    driver.findElement(By.id("address_address1")).clear();
		    driver.findElement(By.id("address_address1")).sendKeys("75 West Center Street");
		    driver.findElement(By.id("address_address2")).clear();
		    driver.findElement(By.id("address_address2")).sendKeys("Test Address");
		    //driver.findElement(By.linkText("South")).click();
		    //driver.findElement(By.cssSelector("#district_rightcol > div.custom.dropdown > ul > li")).click();
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")).click();
		    
		    //Buyer validation page
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
		    
		    //product page
		    //new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
		    driver.findElement(By.id("checkout")).click();
		    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
		    	return results;
		    }

		    //shop app
		    try{
		    	Thread.sleep(5000);
		    }
		    
		    catch (InterruptedException ex)
		    {
		    	Thread.currentThread().interrupt();
		    } 
		    driver.findElement(By.cssSelector("option[value=\"addPaymentType0\"]")).click();
		    driver.findElement(By.id("paymentNumber_id")).clear();
	        driver.findElement(By.id("paymentNumber_id")).sendKeys("4111111111111111");
	        driver.findElement(By.id("paymentName_id")).clear();
	        driver.findElement(By.id("paymentName_id")).sendKeys("Bob");
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
		    if (place)
		    {
		    	driver.findElement(By.xpath("/html/body/form/div/div[12]/div/button")).click();
		    	if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
			    {
			    	results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
			    	return results;
			    }
		    	if (!isElementPresent(By.id("productinformation-complete")))
		    	{
			    	results[0] = "Hong Kong: Failed - Order did not take place";
			    	return results;
		    	}
		    	results[2] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
		    }
		    results[0] = "Hong Kong: Passed";
		    return results;
		  }
		  
		  catch (Exception e)
		  {
			  results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
			  return results;
		  }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
