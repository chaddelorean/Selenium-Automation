package com.qatest.webdriver.CQ5;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Day {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	String pathToBinary = "C:\\Users\\cowens\\AppData\\Local\\Mozilla Firefox\\firefox.exe";
	System.setProperty("webdriver.firefox.bin", pathToBinary);
	driver = new FirefoxDriver();
    baseUrl = "http://testauthor.nuskin.com/cf#/content/nuskin/en_US/test.html";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test() throws Exception {
	  WebElement component = driver.findElement(By.className("x-btn-text cq-_47apps_47foundation_47components_47image"));  
	  Actions builder = new Actions(driver);
	  Actions action = builder.clickAndHold(component);
	  builder.build();
	  action.perform();
	  
	  //driver.switchTo().frame();
	  WebElement location = driver.findElement(By.id("cq-gen19"));
	  Actions builder2 = new Actions(driver);
	  Actions action2 = builder.moveToElement(location);
	  builder2.release();
	  builder2.build();
	  action2.perform();
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
