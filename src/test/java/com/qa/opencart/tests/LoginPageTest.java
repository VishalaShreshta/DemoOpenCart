package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
		
	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority = 2)
	public void verifyNewCustomerBtn() {
		
		Assert.assertTrue(loginPage.newCustBtnExist());
	}
	
	@Test(priority = 3)
	public void verifyForgotPwdLink() {
		
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());;
	}
	
	@Test(priority = 4)
	public void verifyRegisterAccntText() {
		
		Assert.assertTrue(loginPage.regAccntTextExist());
				
	}
	
	@Test(priority = 5)
	public void userLogin() {
		
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	

}
