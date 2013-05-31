package com.qatest.webdriver;


class User{
	
	private String userName;
	private String userPassword;
	private String memberId;
	private String cardType;
	
	public User(String name, String password, String member, String type)
	{
		userName = name;
		userPassword = password;
		memberId = member;
		cardType = type;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getUserPassword()
	{
		return userPassword;
	}
	
	public String getMemberId()
	{
		return memberId;
	}
	
	public String getCardType()
	{
		return cardType;
	}
}
