package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.qa.opencart.utils.ElementUtil;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkBook {
	
	
	
//	public WorkBook(WebDriver driver) {	
//
//		elementUtil = new ElementUtil(this.driver);		
//	}
	
	public static void main(String[] args) {	
		
		WebDriver driver;
		ElementUtil elementUtil;
		Properties prop = null;		

		 By email = By.cssSelector("#input-email");
		 By password = By.cssSelector("#input-password");
		 By loginButton = By.xpath("//input[@type='submit'and @value='Login']");
		 By subHeaderSection = By.xpath("//div[@id='content']//h2");
//		 By subHeaderSectionLink = By.xpath("//div[@id='content']//ul");
		 By accntPageSubHeaderLink = By.cssSelector("div#content ul.list-unstyled li");
//		//div[@id='content']//ul[]/li[]
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		elementUtil = new ElementUtil(driver);		
		
		driver.get("https://demo.opencart.com/index.php?route=account/login");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(email).sendKeys("vishala.sharada@gmail.com");
		driver.findElement(password).sendKeys("test@123");
		driver.findElement(loginButton).click();
		int len1 = 0;
//		int len = driver.findElements(subHeaderSectionLink).size();
//		System.out.println("length of subheader " + len);		
		
		
		// getAccountSubSectionsList:
		
		List<String> acctSubSectionList = new ArrayList<>();
		List<WebElement> eleList = driver.findElements(subHeaderSection);		
		List<WebElement> eleSubLinks = driver.findElements(accntPageSubHeaderLink);
		
//		for(WebElement e:eleList) {
//			System.out.println("SubSecList is " + e.getText());
//			acctSubSectionList.add(e.getText());
//		}
		
//		for (int i = 0; i < eleList.size(); i++) {
//			String text = eleList.get(i).getText();
//			System.out.println("Subsection" + i+ ": "+text);
			for(int j = 0; j < eleSubLinks.size(); j++) {
				String linkText = eleSubLinks.get(j).getText();
				acctSubSectionList.add(linkText);
			}
		System.out.println("subsection links "+acctSubSectionList);

		
		driver.close();
	}
//}
	

}
