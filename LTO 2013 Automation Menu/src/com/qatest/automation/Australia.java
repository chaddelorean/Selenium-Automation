package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Australia {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;

  private ResetPLQuantity plquantity;
  private BuyerDataForm data;
  
  public Australia()
  {
      data = new BuyerDataForm();
      data.setLogin("homersimpsonAU@qa.com");
      data.setPassword("abc123");
      data.setDistID("AS00007660");
      data.setBuyerID("US1111111");
  }
  public Australia(BuyerDataForm d)
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
  public String[] testAustralia(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_Pacifics");
      plquantity.Reset(data.getBuyerID(), "LTO_Pacifics");
	  Myself(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		 driver.get(baseUrl);
	    //global landing page
	    driver.findElement(By.linkText("Australia")).click();
	    //Australia landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/div/a")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
	    results[0] = myBuyer.buyerPage(data);
	    if (results[0] != null)
	    {
	    	results[0] = "Australia: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Australia");
	    	return results;
	    }

        if (LTO2013Menu.stopBuyer())
            return results;

        //Thread.sleep(5000);

	    //driver.findElement(By.id("choose_btnsubmit")).click();
	 	
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.className("shopError")))
	    {
	    	results[0] = "Australia: Failed: Myself \n" + "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Australia");
	    	return results;
	    }

	    //shop app
	    Nonauthshopapp shopapp = new Nonauthshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Australia: Failed: Myself\n" + results[0];
            results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    	return results;
	    }
	   	    
	    results[0] = "Australia: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Australia: Buy for Myself \n"+ "URL: " + driver.getCurrentUrl() + "\n" +  "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Australia");
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
