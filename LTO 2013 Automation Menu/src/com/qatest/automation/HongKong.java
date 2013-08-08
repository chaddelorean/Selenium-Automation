package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HongKong {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;
  private BuyerDataForm data;
  private ResetPLQuantity plquantity;
  private String omniCreate;
  private String omniLoad;

  public HongKong()
  {
      data = new BuyerDataForm();
      data.setLogin("HK1111111");
      data.setPassword("abc123");
      data.setBuyerID("HK0010165");
      data.setDistID("HK1111111");
      omniCreate = "";
      omniLoad = "";
  }
  public HongKong(BuyerDataForm d)
  {
      data = d;
      omniCreate = "";
      omniLoad = "";
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://ltotest.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    plquantity = new ResetPLQuantity(driver);
  }

  @Test
  public String[] testHongKong(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(data.getDistID(), "LTO_HK");
      plquantity.Reset(data.getBuyerID(), "LTO_HK");
	  Myself(place, screenshot, location);
	  if (results[0].equals("Hong Kong: Passed"))
		  someoneElse(place, screenshot, location);
	  
	  return results;
  }

  public void setupOmniture(String create, String load)
  {
      omniCreate = create;
      omniLoad = load;
  }

  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
	  	driver.get(baseUrl);
	    //global landing page
	    driver.findElement(By.linkText("香港 (Hong Kong)")).click();
	    //Hong Kong landing page - Order Now button
        LandingPage land = new LandingPage(driver);
        results[0] = land.landing(data.getLogin(), data.getPassword());
        if (results[0] != null)
        {
            results[0] = "Hong Kong: Failed: Myself\n" + results[0];
            if (screenshot)
                myScreenShot.takeScreenShot(location, "HongKong");
            return results;
        }

        BuyerSelect select = new BuyerSelect(driver);
        results[0] =  select.Select("myself");
        if (results[0] != null)
        {
            results[0] = "Hong Kong: Failed: Myself\n" + results[0];
            if (screenshot)
                myScreenShot.takeScreenShot(location, "HongKong");
            return results;
        }
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
	    results[0] = myBuyer.buyerPage(data);
	    if (results[0] != null)
	    {
	    	results[0] = "Hong Kong: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "HongKong");
	    	return results;
	    }

          if (LTO2013Menu.stopBuyer())
            return results;

	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();

        //omniture check
        Omniturevalidation omni = new Omniturevalidation(driver);
        if (!omniCreate.equals(""))
        {
            omni.createOmniCSV(omniCreate);
        }

        if (!omniLoad.equals(""))
        {
            omni.initializeCheckVariables(omniLoad);
            omni.getOmnitureDebuggerPage();
            if (!omni.compareOmniVariables())
            {
                results[0] = "Hong Kong: Failed: Myself\nError: Omniture variables did not match.";
                return results;
            }
        }
	    //product page
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
	    {
	    	results[0] = "Hong Kong: Failed: Myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "HongKong");
	    	return results;
	    }

	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getBuyerID());
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    {
	    	results[0] = "Hong Kong: Failed: Myself \n" + results[0];
            results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "HongKong");
	    	return results;
	    }
	   
	    results[0] = "Hong Kong: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Hong Kong: Failed: My Self \n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "HongKong");
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place, boolean screenshot, String location)
  {
	  try{
		  driver.get(baseUrl);
		  //global landing page
          driver.findElement(By.linkText("香港 (Hong Kong)")).click();
		  //Hong Kong landing page - Order Now button
          LandingPage land = new LandingPage(driver);
          results[0] = land.landing(data.getLogin(), data.getPassword());
          if (results[0] != null)
          {
              results[0] = "Hong Kong: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "HongKong");
              return results;
          }

          BuyerSelect select = new BuyerSelect(driver);
          results[0] =  select.Select("other");
          if (results[0] != null)
          {
              results[0] = "Hong Kong: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "HongKong");
              return results;
          }
		    
		    //buyer page info
		    Buyer myBuyer = new Buyer(driver, LTO2013Menu.stopBuyer());
		    results[0] = myBuyer.buyerPage(data);
		    if (results[0] != null)
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n" + results[0];
		    	if (screenshot)
					myScreenShot.takeScreenShot(location, "HongKong");
		    	return results;
		    }

            if (LTO2013Menu.stopBuyer())
                return results;
		    //Buyer validation page
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();

            //product page
		    //new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
		    driver.findElement(By.id("checkout")).click();
		    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
		    	if (screenshot)
					myScreenShot.takeScreenShot(location, "HongKong");
		    	return results;
		    }

		    //shop app
		    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
		    String[] temp = shopapp.ShopApp(place);
		    results[0] = temp[0];
		    results[2] = temp[1];
		    if (results[0] != null)
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n" + results[0];
                results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
		    	if (screenshot)
					myScreenShot.takeScreenShot(location, "HongKong");
		    	return results;
		    }
		    
		    results[0] = "Hong Kong: Passed";
            results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
		    return results;
		  }
		  
		  catch (Exception e)
		  {
			  results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
			  if (screenshot)
					myScreenShot.takeScreenShot(location, "HongKong");
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
