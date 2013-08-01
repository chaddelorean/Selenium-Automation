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


public class Canada {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;
  private ResetPLQuantity plquantity;
  private BuyerDataForm data;

  public Canada()
  {
      data = new BuyerDataForm();
      data.setLogin("gaaker");
      data.setPassword("krist90");
      data.setDistID("US8128558");
      data.setBuyerID("US00179669");
  }
  public Canada(BuyerDataForm d)
  {
	  data = d;
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://ltotest.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    plquantity = new ResetPLQuantity(driver);
  }

  @Test
  public String[] testCanada(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_Americas");
      plquantity.Reset(data.getBuyerID(), "LTO_Americas");
      someoneElse(place, screenshot, location);
	  if (results[0].equals("Canada: Passed"))
          Myself(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
		driver.get(baseUrl);
		driver.findElement(By.linkText("Canada")).click();
		//Canada landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "Canada: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Canada");
			return results;
		}

          BuyerSelect select = new BuyerSelect(driver);
          results[0] =  select.Select("myself");
          if (results[0] != null)
          {
              results[0] = "Canada: Failed: Myself\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Canada");
              return results;
          }

          DropDownProduct product = new DropDownProduct(driver);
          results[0] = product.Product();
          if (results[0] != null)
          {
              results[0] = "Canada: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Canada");
              return results;
          }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Canada: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "Canada");
	    	return results;
	    }	    
	    
	    results[0] = "Canada: Passed" + data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Canada: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
	    		myScreenShot.takeScreenShot(location, "Canada");
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
	  	driver.get(baseUrl);
	  	driver.findElement(By.linkText("Canada")).click();
	  	//Canada landing page - Order Now button
	  	LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "Canada: Failed: Myself\n" + results[0];
			if (screenshot)
	    		myScreenShot.takeScreenShot(location, "Canada");
			return results;
		}

        Thread.sleep(3000);

        BuyerSelect select = new BuyerSelect(driver);
        results[0] =  select.Select("other");
        if (results[0] != null)
        {
            results[0] = "Canada: Failed: Myself\n" + results[0];
            if (screenshot)
                myScreenShot.takeScreenShot(location, "Canada");
            return results;
        }

	    
	    //buyerOther page
	    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
	    results[0] = myBuyer.buyerPage(data);
	    if (results[0] != null)
	    {
	    	results[0] = "Canada: Failed: Myself\n" + results[0];
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "Canada");
	    	return results;
	    }

        if (LTO2013Menu.stopBuyer())
            return results;

	    //Verification Page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
          DropDownProduct product = new DropDownProduct(driver);
          results[0] = product.Product();
          if (results[0] != null)
          {
              results[0] = "Canada: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Canada");
              return results;
          }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[2] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Canada: Failed: Someone Else\n" + results[0];
	    	return results;
	    }    
	    
	    results[0] = "Canada: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Canada: Script Error: Buy for someone else:\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
	    		myScreenShot.takeScreenShot(location, "Canada");
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
