package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	//1. By Locators
	
	private By email = By.cssSelector("#input-email");
	private By password = By.cssSelector("#input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotPwd = By.xpath("//a[text()='Forgotten Password']");
	private By newCustomer = By.xpath("//a[text()='Continue']");
	private By regAccntLabel = By.xpath("//p[contains(text(),'creating an account')]");
	
	//2. Constructor
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	//3. Page Actions
	
	public String getLoginPageTitle() {
		
		return driver.getTitle();
	}
	
	public boolean newCustBtnExist() {
		
		return driver.findElement(newCustomer).isDisplayed();
	}
	
	public boolean isForgotPwdLinkExist() {
		
		return driver.findElement(forgotPwd).isDisplayed();
		
	}
	
	public boolean regAccntTextExist() {
		
		return driver.findElement(regAccntLabel).isDisplayed();
		
	}
	
	public void doLogin(String un, String pwd) {
		
		driver.findElement(email).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
	}
	

}
