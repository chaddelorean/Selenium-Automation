package com.qatest.webdriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DowicWebDriver{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	String pathToBinary = "C:\\Users\\cowens\\AppData\\Local\\Mozilla Firefox\\firefox.exe";
	System.setProperty("webdriver.firefox.bin", pathToBinary);
    driver = new FirefoxDriver();
    baseUrl = "https://test.nuskin.com/dowicSAP/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  
  public Product[] getProduct()
	{
		Product[] myProducts = new Product[100];
		try
		{
			Path path = Paths.get("C:/Dowic/product.csv");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			Iterator<String> i = lines.iterator();		
			int j = 0;
			
			while(i.hasNext())
			{
				String[] temp;
				temp = i.next().split(",");
				String[] skuholder = new String[(temp.length)];
				for (int m = 0; m < temp.length; m++)
				{
					if (m+1 < temp.length)
					{
						if (temp[m + 1] != null)
							skuholder[m] = temp[m + 1];
					}
				}
	
				myProducts[j] = new Product(temp[0], skuholder);
				myProducts[j].rebuildSku();
				j++;
			}
		}
		
		catch (Exception e)
		{
			System.out.println("Error getProduct(): " + e);
		}
		
		return myProducts;
	}
  
  public Creditcard getCreditCard()
	{
		Creditcard myCreditCard = null;
		try
		{
			Path path = Paths.get("C:/Dowic/creditcard.csv");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			Iterator<String> i = lines.iterator();
			
			while(i.hasNext())
			{
				String[] temp;
				temp = i.next().split(",");
				if (!temp[0].equals("ccNumber"))
				{
					myCreditCard = new Creditcard(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error getCreditCard():" + e);
		}
		
		return myCreditCard;
	}
	
	public User[] getUser()
	{
		
		User[] myUsers = new User[100];
		try
		{
			Path path = Paths.get("C:/Dowic/user.csv");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			Iterator<String> i = lines.iterator();
			int j = 0;	
			while(i.hasNext())
			{
				String[] temp;
				temp = i.next().split(",");
				if (!temp[0].equalsIgnoreCase("username") && !temp[0].equals(null))
				{
					myUsers[j] = new User(temp[0], temp[1], temp[2], temp[3]);
				}
				else
					continue;
				j++;
			}
		}
		
		catch (Exception e)
		{
			System.out.println("Error getUser():" + e);
		}
		return myUsers;
	}

  
  @Test
  public void test() throws Exception {
	  
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		BufferedWriter log = new BufferedWriter(new FileWriter("C:/Dowic/log.txt", true));
		Creditcard myCreditcard = getCreditCard();
		User[] myUsers = getUser();
		Product[] myProducts = getProduct();
		
		if (myUsers.length > myProducts.length)
		{
			System.out.println("Error: Users and Products list not equal size");
			return;
		}
		log.write(" ");
		log.newLine();
		log.write("Date: " + timeStamp);
		log.newLine();
		for (int i = 0; i < myUsers.length; i++)
		{
			for (int j = 0; j < myProducts.length; j++ )
			{
				if (myUsers[i] != null && myUsers[i].getUserName().equals(myProducts[j].getUserMarket()))
				{
					log.write(CreateOrder(myCreditcard, myUsers[i], myProducts[j]));
					log.newLine();
					j = 0;
					break;
				}
			}
		}
		log.close();
  }

  
  public String CreateOrder(Creditcard myCreditCard, User myUser, Product myProduct)
	{			
		
	  	driver.get(baseUrl);
				
		while((isElementPresent(By.id("userName"))))
		{
			driver.findElement(By.id("userName")).sendKeys(myUser.getUserName());
			driver.findElement(By.id("password")).sendKeys(myUser.getUserPassword());
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		}
			
		driver.findElement(By.id("createOrder")).click();
		
		driver.findElement(By.id("memberId")).sendKeys(myUser.getMemberId());
		driver.findElement(By.cssSelector("input.mainbutton")).click();
		
		if (isElementPresent(By.id("member.errors")))
		{
			System.out.println("Failed: " + myUser.getUserName() + "Error Message: " + driver.findElement(By.id("member.errors")).getText());
			return ("Failed: " + myUser.getUserName() + "Error Message: " + driver.findElement(By.id("member.errors")).getText());
		}
		
		if (isElementPresent(By.name("_target0")))
		{
			driver.findElement(By.name("_target0")).click();
		}
		
		String[] products = myProduct.getSku();
		int i = 0;
		String temp;
		boolean loop;
		do{
			temp = "";
			loop = false;
			driver.findElement(By.id("prod")).clear();
			driver.findElement(By.id("prod")).sendKeys(products[i]);
			driver.findElement(By.id("qty")).clear();
			driver.findElement(By.id("qty")).sendKeys("1");
			driver.findElement(By.name("_target2")).click();
			
			if (isElementPresent(By.id("orderForm.errors")))
			{
				temp = trimSpace(driver.findElement(By.id("orderForm.errors")).getText());
				
				if (!temp.equalsIgnoreCase(trimSpace("Item " + products[i] + " is out of stock and has been removed from the order.")))
				{
					System.out.println("Failed: " + myUser.getUserName() + " Error Message: " + driver.findElement(By.id("orderForm.errors")).getText());
					return ("Failed: " + myUser.getUserName() + " Error Message: " + driver.findElement(By.id("orderForm.errors")).getText());
				}
				else 
					loop = true;
			}
			i++;
		} while(loop);
		
		driver.findElement(By.id("continue")).click();
		String amount = "";
		List<WebElement> element = driver.findElements(By.id("payAmt"));
		for (WebElement j : element)
		{
			if (!(j.getAttribute("value").equals("")))
			{
			    amount = j.getAttribute("value");
			    j.clear();
			    driver.findElement(By.id("payAmt")).clear();
				driver.findElement(By.id("payAmt")).sendKeys(amount);
			    
				break;
			}
		} 
		
		if (driver.findElement(By.id("payTypeCD")).isEnabled())
			driver.findElement(By.id("payTypeCD")).sendKeys(myUser.getCardType());
		if (driver.findElement(By.id("accNum")).isEnabled())
		{
			driver.findElement(By.id("accNum")).sendKeys(myCreditCard.getCardNum());
			driver.findElement(By.id("accNum")).clear();
			driver.findElement(By.id("accNum")).sendKeys(myCreditCard.getCardNum());
		}
		if (driver.findElement(By.id("month")).isEnabled())
			driver.findElement(By.id("month")).sendKeys(myCreditCard.getExpMonth());
		if (driver.findElement(By.id("year")).isEnabled())
			driver.findElement(By.id("year")).sendKeys(myCreditCard.getExpYear());
		if (driver.findElement(By.id("cardHolder")).isEnabled())
			driver.findElement(By.id("cardHolder")).sendKeys(myCreditCard.getCcName());
		if (driver.findElement(By.id("authNumber")).isEnabled())
			driver.findElement(By.id("authNumber")).sendKeys(myCreditCard.getCcSecurity());
		if (driver.findElement(By.id("cvv")).isEnabled())
			driver.findElement(By.id("cvv")).sendKeys(myCreditCard.getCvvNum());
		
		List<WebElement> button = driver.findElements(By.className("mainbutton"));
		for (WebElement b : button)
		{
			if (b.getAttribute("value").equalsIgnoreCase("Save Sales Order"))
			{
				b.click();
				break;
			}
		}
		
		if (isElementPresent(By.id("orderForm.errors")))
		{
			System.out.println("Failed: " + myUser.getUserName() + " Error Message: " + driver.findElement(By.id("orderForm.errors")).getText());
			return ("Failed: " + myUser.getUserName() + " Error Message: " + driver.findElement(By.id("orderForm.errors")).getText());
		}
		
		
		String ordernum = "";
		ordernum = driver.findElement(By.cssSelector("strong")).getText();
		
		driver.get("https://test.nuskin.com/dowicSAP/menu.htm");
		driver.findElement(By.xpath("//input[@value='Order  Management']")).click();
		driver.findElement(By.id("orderRefNumberRadio")).click();
		driver.findElement(By.id("orderRefNumber")).sendKeys(ordernum);
		driver.findElement(By.name("_target1")).click();
		
		String orderrefnum = driver.findElement(By.id("id")).getText();

		if (trimSpace(ordernum).equals(trimSpace(orderrefnum)))
		{
			System.out.println("Success: " + myUser.getUserName() + " Reference Number: " + trimSpace(ordernum) + " Order Amount: $" + amount);
			return ("Success: " + myUser.getUserName() + " Reference Number: " + trimSpace(ordernum) + " Order Amount: $" + amount);
		} 
		
		System.out.println("Failed: " + myUser.getUserName() + " Error Message: Order not found");
		return "Failed: " + myUser.getUserName() + " Error Message: Order not found"; 
	}
	
	public String trimSpace(String temp)
	{
		String newString = "";
		char[] nospace = temp.toCharArray();
		for (int i = 0; i < nospace.length; i++)
		{
			if (nospace[i] != ' ')
			{
				newString += nospace[i];
			}
		}
		
		return newString;
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
	  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    try {
      driver.findElement(by);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      return true;
    } catch (NoSuchElementException e) {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
