package com.qatest.webdriver;

public class Creditcard {

		private String cardNum;
		private String expMonth;
		private String expYear;
		private String ccName;
		private String ccSecurity;
		private String cvvNum;
		
		public Creditcard(String num, String month, String year, String name, String cc, String cvv)
		{
			cardNum = num;
			expMonth = month;
			expYear = year;
			ccName = name;
			ccSecurity = cc;
			cvvNum = cvv;
		}
		
		
		public String getCardNum()
		{
			return cardNum;
		}
		
		public String getExpMonth() {
			return expMonth;
		}
	
		public String getExpYear() {
			return expYear;
		}
	
		public String getCcName() {
			return ccName;
		}
	
		public String getCcSecurity() {
			return ccSecurity;
		}
	
		public String getCvvNum() {
			return cvvNum;
		}
	
}