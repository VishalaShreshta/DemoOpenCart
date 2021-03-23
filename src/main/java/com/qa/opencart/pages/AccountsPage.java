package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By locators
	
	private By accntPageHeader = By.cssSelector("div#logo a");
	private By accntPageSubHeaders = By.cssSelector("div#content h2");
	private By accntPageSearchText = By.cssSelector("div#search input[name='search']");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemsResult = By.cssSelector(".product-layout .product-thumb");
	private By resultItems = By.cssSelector(".product-thumb h4 a");	
	private By accntPageSubHeaderLink = By.cssSelector("div#content ul.list-unstyled li");
	
	//2. Constructor of the page class:
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions:
	
	@Step("getting the accounts page title...")
	public String getAccountPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 10);
	}
	
	@Step("getting accounts page Header...")
	public String getAccountPageHeader() {
		if(elementUtil.doIsDisplayed(accntPageHeader)) {
			return elementUtil.doGetText(accntPageHeader);
		}		
		return null;
		
	}
	
	@Step("getting total number of account sections....")
	public int getAccountSectionsCount() {
		return elementUtil.getElements(accntPageSubHeaders).size();
	}
	
	@Step("getting account sections list from my account page.....")
	public List<String> getAccountSectionsList(){
		
		List<String> accountsList = new ArrayList<>();
		List<WebElement> accSectionList = elementUtil.getElements(accntPageSubHeaders);
		
		for(WebElement e: accSectionList) {
			System.out.println(e.getText());
			accountsList.add(e.getText());
		}
		
		return accountsList;		
	}
	
	@Step("searching product with name from the results section : {0}")
	public boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if(elementUtil.getElements(searchItemsResult).size() > 0) {
			return true;
		}
		return false;
	}
	
	@Step("selecting a product with name from the results section : {0}")
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemList = elementUtil.getElements(resultItems);
		System.out.println("Total number of items displayed: " + resultItemList.size());
		
		for(WebElement e : resultItemList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
	
	@Step("Getting AccountsDetailsPage....")
	public void getAccountsPageDetails() {
		System.out.println("getAccountDetailsPage");
	}
	
	
	
	
	

}
