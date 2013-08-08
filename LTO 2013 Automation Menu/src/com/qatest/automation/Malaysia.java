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

public class Malaysia {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;
  private ResetPLQuantity plquantity;
  private BuyerDataForm data;

  public Malaysia()
  {
      data = new BuyerDataForm();
      data.setLogin("MY1111111");
      data.setDistID("MY1111111");
      data.setPassword("abc123");
      data.setBuyerID("MY4118279");
  }
  public Malaysia(BuyerDataForm d)
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
  public String[] testMalaysia(boolean place,  boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_SEA");
      plquantity.Reset(data.getBuyerID(), "LTO_SEA");
	  someoneElse(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] someoneElse(boolean place,  boolean screenshot, String location)
  {
	  try{
		driver.get(baseUrl);
	    //global landing page
	    driver.findElement(By.linkText("Malaysia")).click();
	    //Malaysia landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/div/a")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
	    results[0] = myBuyer.buyerPage(data);
	    if (results[0] != null)
	    {
	    	results[0] = "Malaysia: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Malaysia");
	    	return results;
	    }

        if (LTO2013Menu.stopBuyer())
            return results;

	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "Malaysia: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Malaysia");
			return results;
		}

	    //shop app
		Nonauthshopapp shopapp = new Nonauthshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Malaysia: Failed: Someone Else\n" + results[0];
            results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Malaysia");
	    	return results;
	    }
	    
	    results[0] = "Malaysia: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] =  "Malaysia: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Malaysia");
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
