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
  private ScreenShot myScreenShot;
  private String userName;
  private String distID;
  private String password;
  private ResetPLQuantity plquantity;

  public UnitedKingdom()
  {
      this.userName = "gaaker";
      this.password = "test123";
      this.distID = "US8128558";
  }
  public UnitedKingdom(String username, String password, String dist)
  {
	  this.userName = username;
	  this.password = password;
      this.distID = dist;
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    plquantity = new ResetPLQuantity(driver);
  }

  @Test
  public String[] testUnitedKingdom(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(distID, "LTO_EMEA");
	  Myself(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
		driver.get(baseUrl + "/content/lto/2013.html");
		driver.findElement(By.linkText("United Kingdom")).click();
		//United States landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(userName, password);
		if (results[0] != null)
		{
			results[0] = "United Kingdom: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "UnitedKingdom");
			return results;
		}
	    
	    //product page
		DropDownProduct product = new DropDownProduct(driver);
		results[0] = product.Product();
		if (results[0] != null)
		{
			results[0] = "United Kingdom: Failed: Myself\n" + results[0];
			if (screenshot)
				myScreenShot.takeScreenShot(location, "UnitedKingdom");
			return results;
		}
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver, distID, password);
	    results = shopapp.ShopApp(place);
	    if (results[0] != null)
	    {
	    	results[0] = "United Kingdom: Failed: Myself\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "UnitedKingdom");
	    	return results;
	    }
	 
	    results[0] = "United Kingdom: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "United Kingdom: Script Error: Buy for myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "UnitedKingdom");
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
