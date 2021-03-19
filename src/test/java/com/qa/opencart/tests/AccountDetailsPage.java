package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AccountDetailsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;

	public AccountDetailsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

}
