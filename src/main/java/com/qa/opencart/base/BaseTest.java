package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;


public class BaseTest {
	
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	
	
	
	public Properties prop;
	public WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browser","version"})
	public void setUp(String browserName, String browserVersion) {
		
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
		
		if(browserName != null) {
			browser = browserName;
		}
		driver = basePage.init_driver(browser,browserVersion);		
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterMethod
	public void tearDown() {		
		driver.quit();
	}
	

}
