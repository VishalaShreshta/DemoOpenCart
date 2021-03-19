package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By Locators: Object Repository of Locators	
	private By email = By.cssSelector("#input-email");
	private By password = By.cssSelector("#input-password");
	private By loginButton = By.xpath("//input[@type='submit'and @value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By newCustomer = By.xpath("//a[text()='Continue']");
	private By regAccntLabel = By.xpath("//p[contains(text(),'creating an account')]");
	
	private By registerLink = By.linkText("Register");
	
	//2. Constructor of the page class:
	
	public LoginPage(WebDriver driver) {		
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions: features(Behavior) of the page in the form methods:
	
	@Step("getting the login page title....")
	public String getLoginPageTitle() {
		
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("checking if New Customer button exists")
	public boolean newCustBtnExist() {
		return elementUtil.doIsDisplayed(newCustomer);		
	}
	
	@Step("checking if forgot pwd link is available")
	public boolean isForgotPwdLinkExist() {
		System.out.println("Checking for forgot password link....");
		return elementUtil.doIsDisplayed(forgotPwdLink);		
	}
	
	@Step("Checking if ")
	public boolean regAccntTextExist() { 		
		return driver.findElement(regAccntLabel).isDisplayed();		
	}
	
	@Step("login with username: {0} and passowrd: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with: " + un + " and " + pwd);
		
		elementUtil.doSendKeys(email, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);		
		return new AccountsPage(driver);		
		
	}
	
	@Step("navigating to the register page.....")
	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	

}
