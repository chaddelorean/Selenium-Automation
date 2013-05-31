package com.qatest.automation;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HongKong {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String[] results = new String[3];

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://test.nuskin.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public String[] testHongKong(boolean place) throws Exception {
	  Myself(place);
	  if (results[0].equals("Hong Kong: Passed"))
		  someoneElse(place);
	  
	  return results;
  }
  
  public String[] Myself(boolean place)
  {
	  try{
	  	driver.get(baseUrl + "/content/lto/2013.html");
	    //global landing page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[3]/a")).click();
	    //Hong Kong landing page - Order Now button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
	    //buyer select radio button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div/div/span")).click();
	    //buyer select continue button
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
	    
	    //buyer page info
	    Buyer myBuyer = new Buyer(driver);
	    results[0] = myBuyer.buyerPage();
	    if (results[0] != null)
	    {
	    	results[0] = "Hong Kong: Failed: Myself\n" + results[0];
	    	return results;
	    }
	    
	    //Buyer validation page
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
	    
	    //product page
	    //new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
	    driver.findElement(By.id("checkout")).click();
	    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
	    {
	    	results[0] = "Hong Kong: Failed: My Self\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
	    	return results;
	    }

	    //shop app
	    Nonauthshopapp shopapp = new Nonauthshopapp(driver);
	    String[] temp = shopapp.ShopApp(place);
	    results[0] = temp[0];
	    results[1] = temp[1];
	    if (results[0] != null)
	    	return results;
	   
	    results[0] = "Hong Kong: Passed";
	    return results;
	  }
	  
	  catch (Exception e)
	  {
		  results[0] = "Hong Kong: Failed: My Self \n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
		  return results;
	  }
  }
  
  public String[] someoneElse(boolean place)
  {
	  try{
		  	driver.get(baseUrl + "/content/lto/2013.html");
		    //global landing page
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[3]/ul/li[3]/a")).click();
		    //Hong Kong landing page - Order Now button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[3]/div/div[3]/div/div/a")).click();
		    //buyer select radio button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[2]/div/span")).click();
		    //buyer select continue button
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/form/div/div[3]/div/div/div/p")).click();
		    
		    //buyer page info
		    Buyer myBuyer = new Buyer(driver);
		    results[0] = myBuyer.buyerPage();
		    if (results[0] != null)
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n" + results[0];
		    	return results;
		    }
		    		    
		    //Buyer validation page
		    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div/div/div/form/div/div/div/a")).click();
		    
		    //product page
		    //new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
		    driver.findElement(By.id("checkout")).click();
		    if (isElementPresent(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")))
		    {
		    	results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Error: " + driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div")).getText();
		    	return results;
		    }

		    //shop app
		    Nonauthshopapp shopapp = new Nonauthshopapp(driver);
		    String[] temp = shopapp.ShopApp(place);
		    results[0] = temp[0];
		    results[2] = temp[1];
		    if (results[0] != null)
		    	return results;
		    
		    results[0] = "Hong Kong: Passed";
		    return results;
		  }
		  
		  catch (Exception e)
		  {
			  results[0] = "Hong Kong: Failed: Someone Else\n"+ "URL: " + driver.getCurrentUrl() + "\n" + "Script Error: " + e;
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
