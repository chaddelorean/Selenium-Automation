package com.qatest.webdriver;

public class Product {
	private String userMarket;
	private String[] sku;
	private String[] tempsku;
	
	public Product(String market, String[] psku)
	{
		userMarket = market;
		tempsku = psku;
	}
	
	public String getUserMarket()
	{
		return userMarket;
	}
	
	public String[] getSku()
	{
		return sku;
	}
	
	public void rebuildSku()
	{
		sku = new String[tempsku.length - 1];
		for (int i = 0; i < sku.length; i++)
		{
			if (tempsku[i] != null)
				sku[i] = tempsku[i];
		}
	}	
}
