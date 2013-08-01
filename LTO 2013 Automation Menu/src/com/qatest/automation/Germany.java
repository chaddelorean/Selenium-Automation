package com.qatest.automation;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Germany {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[4];
  private ScreenShot myScreenShot;
  private BuyerDataForm data;
  private ResetPLQuantity plquantity;

  public Germany()
  {
    data = new BuyerDataForm();
    data.setDistID("US1111111");
    data.setLogin("US1111111");
    data.setPassword("abc123");
    data.setBuyerID("none");
  }
  public Germany(BuyerDataForm d)
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
  public String[] testGermany(boolean place, boolean screenshot, String location) throws Exception {
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
		driver.findElement(By.linkText("Deutschland (Germany)")).click();
		//Germany landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(data.getLogin(), data.getPassword());
		if (results[0] != null)
		{
			results[0] = "Germany: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Germany");
			return results;
		}

	    
	    //product page
		DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "Germany: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "Germany");
			return results;
		}
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, data.getDistID());
	    results = shopapp.ShopApp(place);
	    if (results[0] != null)
	    {
	    	results[0] = "Germany: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Germany");
	    	return results;
	    }

	    results[0] = "Germany: Passed";
        results[3] = "ExecutiveID: " +data.getDistID() + " BuyerID: " + data.getBuyerID();
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Germany: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Germany");
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
