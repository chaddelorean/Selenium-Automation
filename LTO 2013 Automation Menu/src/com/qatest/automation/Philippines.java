package com.qatest.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Philippines {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];
  private ScreenShot myScreenShot;
  private String userName;
  private String password;

  public Philippines(String username, String password)
  {
	  if (username.equals("") || password.equals(""))
	  {
		  this.userName = "HK1111111";
		  this.password = "abc123";
	  }
	  
	  else
	  {
		  this.userName = username;
		  this.password = password;
	  }
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public String[] testPhilippines(boolean place,  boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
	  //Myself(place, screenshot, location);
	  //if (results[0].equals("Philippines: Passed"))
		  someoneElse(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place,  boolean screenshot, String location)
  {
	  try{
		  driver.get(baseUrl + "/content/lto/2013.html");
	    //global landing page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[9]/a")).click();
	    //Philippines landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver);
	    results[0] = myBuyer.buyerPage(userName, "SG3110554");
	    if (results[0] != null)
	    {
	    	results[0] = "Philippines: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
	    	return results;
	    }   
	    
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "Philippines: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
			return results;
		}

	    //shop app
	    Nonauthshopapp shopapp = new Nonauthshopapp(driver);
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Philippines: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
	    	return results;
	    }
	    
	    results[0] = "Philippines: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Philippines: Buy for Myself \n"+ "URL: " + driver.getCurrentUrl() + "\n" +  "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place,  boolean screenshot, String location)
  {
	  try{
		driver.get(baseUrl + "/content/lto/2013.html");
	    //global landing page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[8]/a")).click();
	    //Philippines landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
	    //buyer select radio button
	    //driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
	    //buyer select continue button
	    //driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver);
	    results[0] = myBuyer.buyerPage(userName, "SG3110554");
	    if (results[0] != null)
	    {
	    	results[0] = "Philippines: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
	    	return results;
	    }  
	    
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "Philippines: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
			return results;
		}

	    //shop app
		Nonauthshopapp shopapp = new Nonauthshopapp(driver);
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Philippines: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
	    	return results;
	    }
	    
	    results[0] = "Philippines: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] =  "Philippines: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Philippines");
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
