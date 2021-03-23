package com.qa.opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	
	public OptionsManager optionsManager;
	public static String highlight;
	
	
	// To run tests in parallel mode, webDriver will maintain the same session id.
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/*
	 * This method is used to initiate browser, based on the browser value from properties file 
	 * 
	 * @param browser
	 * @return This returns webDriver driver
	 */
	
	public WebDriver init_driver(String browser, String browserVersion ) {
		
		System.out.println("Browser value is: "+ browser);
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome")){			
			WebDriverManager.chromedriver().setup();			
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome", browserVersion);
			} else {
//				driver = new ChromeDriver();
//				tlDriver.set(new ChromeDriver());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean("remote")) {
				init_remoteDriver("firefox", browserVersion);
			}else {				
				//driver = new FirefoxDriver();
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));				
			}
			
		} else if(browser.equalsIgnoreCase("safari")) {
			
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			
			System.out.println("Please enter the correct Browser name");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	// Will pass set and pass the desired capabilities to RemoteWebDriver
	private void init_remoteDriver(String browser, String browserVersion) {
		
		System.out.println("Running test on remote grid: " + browser);
		
		if(browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(ChromeOptions.CAPABILITY,optionsManager.getChromeOptions());
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));			
			}
			catch(MalformedURLException e) {				
				e.printStackTrace();
			}
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities().firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			}
			catch(MalformedURLException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	/**
	 * getDriver using ThreadLocal
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
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
	
	/**
	 * This method is used to take the screenshot It will return the path of
	 * screenshot
	 */
	
	public String getScreenshot() {
		
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}

}
