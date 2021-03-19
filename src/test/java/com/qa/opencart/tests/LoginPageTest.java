package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)

@Epic("Epic 100: define login page features....")
@Story("US 101: define the login page class features with title, forgot pwd link and login functionality")
public class LoginPageTest extends BaseTest {
	
	@Description("verify LoginPage Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {		
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);		
	}
	
	@Description("verify NewCustomer button present Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifyNewCustomerBtnTest() {		
		Assert.assertTrue(loginPage.newCustBtnExist());
	}
	
	@Description("verify forgot password link displayed Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void verifyForgotPwdLinkTest() {		
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());;
	}
	
	@Description("verify RegisterAccount text is displayed Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void verifyRegisterAccntTextTest() {		
		Assert.assertTrue(loginPage.regAccntTextExist());
				
	}
	
	@Description("Login page test with username and password")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void userLoginTest() {		
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	

}
