package com.qa.opencart.tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-300: Design Products Page")
@Story("US -301: Designing the products page")
public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void ProductsPageSetUp() {
		
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Description("Getting Product info page title ")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void ProductInfoPageTitleTest_iMac() {
		
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
	}
	
	@Description("Getting Product info page for MacBook ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifyProductInfoTest_MacBook() {
		
		String productName = "Macbook";
		
		Assert.assertTrue(accountsPage.doSearch(productName));
		
		productInfoPage = accountsPage.selectProductFromResults("MacBook Pro");
		
		Assert.assertTrue(productInfoPage.getProductImages() == 4);
		
		Map<String,String> productInfoMap = productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		
		// {Brand=Apple, Availability=In Stock,
				// price=$2,000.00, name=MacBook Pro,
				// Product Code=Product 18, Reward Points=800,
				// exTaxPrice=$2,000.00}
		
		Assert.assertEquals(productInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInfoMap.get("Reward Points"), "800");
	}
	
	@Description("Getting Product info page for iMac")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void verifyProductInfoTest_iMac() {
		
		String productName = "iMac";
		
		Assert.assertTrue(accountsPage.doSearch(productName));
		
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.getProductImages() == 3);
		
		Map<String,String> productInfoMap = productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		
		//{Brand=Apple, Availability=In Stock, 
//		price=$100.00, name=iMac, 
//				Product Code=Product 14, 
//				exTaxPrice=$100.00}
		
		Assert.assertEquals(productInfoMap.get("name"), "iMac");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("price"), "$100.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
	}
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	

}
