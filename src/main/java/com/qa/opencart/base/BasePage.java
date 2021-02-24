package com.qa.opencart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	
	/*
	 * This method is used to initiate browser based on the browser value from properties file 
	 * 
	 * @param browser
	 * @return This returns webdriver driver
	 */
	
	public WebDriver init_driver(String browser) {
		
		System.out.println("Browser value is: "+ browser);
		
		if(browser.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if(browser.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
		}
		else {
			
			System.out.println("Please enter the correct Browser name");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}
	
	/*
	 * This method is used to load properties from config.properties file
	 * @return it returns Properties prop reference
	 */
	
	public Properties init_prop() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/opencart/config/config.properties");
			prop.load(ip);
			}
		catch(FileNotFoundException e) {			
			e.printStackTrace();			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return prop;
	}

}
