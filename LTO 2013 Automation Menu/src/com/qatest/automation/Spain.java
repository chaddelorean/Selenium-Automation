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

public class Spain {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;
  private ResetPLQuantity plquantity;
  private BuyerDataForm data;

  public Spain()
  {
      data = new BuyerDataForm();
      data.setLogin("ES3404590");
      data.setPassword("abc123");
      data.setDistID("ES3404590");
      data.setBuyerID("none");
  }
  public Spain(BuyerDataForm d)
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
  public String[] testSpain(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_EMEA");
	  Myself(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
		driver.get(baseUrl);
		driver.findElement(By.linkText("España (Spain)")).click();
		//United States landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "Spain: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Spain");
			return results;
		}
	    
	    //product page
		DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "Spain: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Spain");
			return results;
		}
	    
	    
	    //shop app
	    Nonauthshopapp shopapp = new Nonauthshopapp(driver, data.getDistID());
	    results = shopapp.ShopApp(place);
	    if (results[0] != null)
	    {
	    	results[0] = "Spain: Failed: Myself\n" + results[0];
            results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Spain");
	    	return results;
	    }

        if (screenshot)
            myScreenShot.takeScreenShot(location, "Spain");

	    results[0] = "Spain: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
        String[] ordernum = results[1].split(" ");
        LTO2013Menu.getLog().appendLog(data.getDistID(), data.getBuyerID(), ordernum[3], "Spain");
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Spain: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Spain");
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
