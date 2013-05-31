package com.qatest.automation;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.Click;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnitedKingdom {
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
  public String[] testUnitedKingdom(boolean place) throws Exception {
	  Myself(place);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place)
  {
	  try{
		//global landing page
		driver.get(baseUrl + "/content/lto/2013.html");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[2]/div/div[2]/ul/li[13]/a")).click();
		//United States landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing();
		if (results[0] != null)
		{
			results[0] = "United Kingdom: Failed: Myself\n" + results[0];
			return results;
		}
	    
	    //product page
		DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "United Kingdom: Failed: Myself\n" + results[0];
		}
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver);
	    results = shopapp.ShopApp(place);
	    if (results[0] != null)
	    {
	    	results[0] = "United Kingdom: Failed: Myself\n" + results[0];
	    	return results;
	    }
	    /*driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).clear();
	    driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).sendKeys("123");
	    
	    if (place)
	    {
	    	driver.findElement(By.xpath("/html/body/form/div/div[12]/div/button")).click();
	    	if (isElementPresent(By.className("shopError")))
		    {
		    	results[0] = "United Kingdom: Failed Myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
		    	return results;
		    }
	    	if (!isElementPresent(By.id("productinformation-complete")))
	    	{
		    	results[0] = "United Kingdom: Failed - Order did not take place";
		    	return results;
	    	}
	    	results[1] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
	    }*/

	    results[0] = "United Kingdom: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "United Kingdom: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place)
  {
	  try{
		//global landing page
	  	driver.get(baseUrl + "/content/lto/2013.html");
	  	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[2]/div/div[2]/ul/li[13]/a")).click();
	  	//United States landing page - Order Now button
	  		LandingPage land = new LandingPage(driver);
	  		results[0] = land.landing();
	  		if (results[0] != null)
	  		{
	  			results[0] = "United States: Failed: Myself\n" + results[0];
	  			return results;
	  		}
	     
	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyerOther page
	    
	    /*driver.findElement(By.id("mobile_2")).clear();
	    driver.findElement(By.id("mobile_2")).sendKeys("456");
	    driver.findElement(By.id("mobile_3")).clear();
	    driver.findElement(By.id("mobile_3")).sendKeys("4565");
	    driver.findElement(By.id("buyerID")).clear();
	    driver.findElement(By.id("buyerID")).sendKeys("US8128558");
	    driver.findElement(By.id("buyerMobile_2")).clear();
	    driver.findElement(By.id("buyerMobile_2")).sendKeys("444");
	    driver.findElement(By.id("mobile_3")).clear();
	    driver.findElement(By.id("mobile_3")).sendKeys("5555");
	    driver.findElement(By.id("nameOfPerson")).clear();
	    driver.findElement(By.id("nameOfPerson")).sendKeys("Test User");
	    driver.findElement(By.id("address_address1")).clear();
	    driver.findElement(By.id("address_address1")).sendKeys("75 west center street provo");
	    driver.findElement(By.id("address_address2")).clear();
	    driver.findElement(By.id("address_address2")).sendKeys("Test Address");
	    driver.findElement(By.id("address_city")).clear();
	    driver.findElement(By.id("address_city")).sendKeys("Provo");
	    driver.findElement(By.id("address_postalCode")).clear();
	    driver.findElement(By.id("address_postalCode")).sendKeys("84601");
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/p/a")).click();*/
	    
	    //Verification Page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.className("shopError")))
	    {
	    	results[0] =  "United Kingdom: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	return results;
	    }
	    
	    
	    //shop app
	    driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).clear();
	    driver.findElement(By.id("paymentSelection_order_1_cvv2_order_input")).sendKeys("123");
	    
	    if (place)
	    {
	    	driver.findElement(By.xpath("/html/body/form/div/div[12]/div/button")).click();
	    	if (isElementPresent(By.className("shopError")))
		    {
		    	results[0] = "United Kingdom: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
		    	return results;
		    }
	    	if (!isElementPresent(By.id("productinformation-complete")))
	    	{
		    	results[0] = "United Kingdom: Failed: Soneone Else - Order did not take place";
		    	return results;
	    	}
	    	results[2] = driver.findElement(By.xpath("/html/body/form/div/div[2]/h2")).getText();
	    }
	    
	    
	    results[0] = "United Kingdom: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "United Kingdom: Script Error: Buy for someone else:\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
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
