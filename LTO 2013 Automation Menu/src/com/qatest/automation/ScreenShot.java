package com.qatest.automation;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenShot {
	private WebDriver driver;
	private Date date = new Date();
	
	public ScreenShot(WebDriver driver)
	{
		this.driver = driver;
		
	}
	
	public void takeScreenShot(String location, String market)
	{
		String stringDate = date.toString();
		stringDate = trimSpace(stringDate);
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
			FileUtils.copyFile(srcFile,  new File(location + "\\" + market + stringDate + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String trimSpace(String temp)
	{
		char[] tempchar = temp.toCharArray();
		String newString = "";
		for (int i = 0; i < tempchar.length; i++)
		{
			if (tempchar[i] != ' ' && tempchar[i] != ':')
				newString += tempchar[i];
		}
		
		return newString;
	}

}
