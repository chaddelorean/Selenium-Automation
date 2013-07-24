package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class UnitedStates {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];
  private ScreenShot myScreenShot;
  private ResetPLQuantity plquantity;
  private BuyerDataForm data;

  public UnitedStates()
  {
      data = new BuyerDataForm();
      data.setLogin("gaaker");
      data.setPassword("krist90");
      data.setDistID("US8128558");
      data.setBuyerID("US00179669");
  }
  public UnitedStates(BuyerDataForm d)
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
  public String[] testUnitedStates(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_Americas");
      plquantity.Reset(data.getBuyerID(), "LTO_Americas");
      someoneElse(place, screenshot, location);
	  if (results[0].equals("United States: Passed"))
          Myself(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
		driver.get(baseUrl);
		driver.findElement(By.linkText("United States")).click();
		//United States landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "United States: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "UnitedStates");
			return results;
		}

	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();

          DropDownProduct product = new DropDownProduct(driver);
          results[0] = product.Product();
          if (results[0] != null)
          {
              results[0] = "United States: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Brunei");
              return results;
          }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "United States: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
	    	return results;
	    }	    
	    
	    results[0] = "United States: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "United States: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
	  	driver.get(baseUrl);
	  	driver.findElement(By.linkText("United States")).click();
	  	//United States landing page - Order Now button
	  	LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "United States: Failed: Myself\n" + results[0];
			if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
			return results;
		}

        Thread.sleep(3000);
        //buyer select radio button
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
        //buyer select continue button
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyerOther page
	    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
	    results[0] = myBuyer.buyerPage(data);
	    if (results[0] != null)
	    {
	    	results[0] = "United States: Failed: Myself\n" + results[0];
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
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
              results[0] = "United States: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Brunei");
              return results;
          }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[2] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "United States: Failed: Someone Else\n" + results[0];
	    	return results;
	    }    
	    
	    results[0] = "United States: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "United States: Script Error: Buy for someone else:\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
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
