package com.qatest.automation;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Brunei {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];
  private ScreenShot myScreenShot;
  private String userName;
  private String password;
  private ResetPLQuantity plquantity;
  private String buyer;
  
  public Brunei(String username, String password)
  {
	  if (username.equals("") || password.equals(""))
	  {
		  this.userName = "SG1111111";
		  this.password = "abc123";
	  }
	  else
	  {
		  this.userName = username;
		  this.password = password;
	  }

      buyer = "SG2067156";
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    plquantity = new ResetPLQuantity(driver);
  }

  @Test
  public String[] testBrunei(boolean place, boolean screenshot, String location) throws Exception {
	  //Myself(place);
	  //if (results[0].equals("Brunei: Passed"))
	  myScreenShot = new ScreenShot(driver);
      plquantity.Reset(userName, "LTO_SEA");
      plquantity.Reset(buyer, "LTO_SEA");
	  someoneElse(place, screenshot, location);
	  
	  return results;
  }

  
  public String[] someoneElse(boolean place, boolean screenshot, String location)
  {
	  try{
		driver.get(baseUrl + "/content/lto/2013.html");
	    //global landing page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li/a")).click();
	    //Brunei landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[7]/div/div/div/a")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver);
	    results[0] = myBuyer.buyerPage(userName, buyer);
	    if (results[0] != null)
	    {
	    	results[0] = "Brunei: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Brunei");
	    	return results;
	    }   
	    
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    DropDownProduct product = new DropDownProduct(driver);
          results[0] = product.Product();
          if (results[0] != null)
          {
              results[0] = "Brunei: Failed: Someone Else\n" + results[0];
              if (screenshot)
                  myScreenShot.takeScreenShot(location, "Brunei");
              return results;
          }
	    
	    //shop app
	    Nonauthshopapp shopapp = new Nonauthshopapp(driver, userName, password);
	    results = shopapp.ShopApp(place);
	    if (results[0] != null)
	    {
	    	results[0] = "Brunei: Failed: Someone Else\n" + results[0];
	    	if (screenshot)
				myScreenShot.takeScreenShot(location, "Brunei");
	    	return results;
	    }
	    
        results[0] = "Brunei: Passed";
        return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Brunei: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  if (screenshot)
				myScreenShot.takeScreenShot(location, "Brunei");
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
