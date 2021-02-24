package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	public BasePage basePage;
	public LoginPage loginPage;
	public Properties prop;
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	

}
