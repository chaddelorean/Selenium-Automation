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

public class UnitedStates {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];
  private ScreenShot myScreenShot;
  private String userName;
  private String password;
  
  public UnitedStates(String username, String password)
  {
	  this.userName = username;
	  this.password = password;
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public String[] testUnitedStates(boolean place, boolean screenshot, String location) throws Exception {
	  myScreenShot = new ScreenShot(driver);
	  Myself(place, screenshot, location);
	  if (results[0].equals("United States: Passed"))
		  someoneElse(place, screenshot, location);
	  
	  return results;
   
  }
  
  public String[] Myself(boolean place, boolean screenshot, String location)
  {
	  try{
		//global landing page
		driver.get(baseUrl + "/content/lto/2013.html");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/ul/li[2]/a")).click();
		//United States landing page - Order Now button
		LandingPage land = new LandingPage(driver);
		results[0] = land.landing(userName, password);
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
	    
	    //product page
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.className("shopError")))
	    {
	    	results[0] = "United States: Failed Myself\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
	    	return results;
	    }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver);
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
	  	driver.get(baseUrl + "/content/lto/2013.html");
	  	driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/ul/li[2]/a")).click();
	  	//United States landing page - Order Now button
	  	LandingPage land = new LandingPage(driver);
		results[0] = land.landing(userName, password);
		if (results[0] != null)
		{
			results[0] = "United States: Failed: Myself\n" + results[0];
			if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
			return results;
		}
		
	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyerOther page
	    Buyer myBuyer = new Buyer(driver);
	    results[0] = myBuyer.buyerPage();
	    if (results[0] != null)
	    {
	    	results[0] = "United States: Failed: Myself\n" + results[0];
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
	    	return results;
	    }   
	  
	    //Verification Page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.className("shopError")))
	    {
	    	results[0] = "United States: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.className("shopError")).getText();
	    	if (screenshot)
	    		myScreenShot.takeScreenShot(location, "UnitedStates");
	    	return results;
	    }
	    
	    
	    //shop app
	    Authshopapp shopapp = new Authshopapp(driver);
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
