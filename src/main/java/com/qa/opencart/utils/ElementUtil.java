package com.qa.opencart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.base.BasePage;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);	
		
	}
	
	public By getLocator(String value) {
		return By.id(value);
	}
	
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);		
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(BasePage.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		
		return element;
	}	
	
	
	//*************************** Elements Action ********************
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);		
	}
	
	public void doClick(By locator) {
		getElement(locator).click();		
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public int getElementsCount(String tagName) {
		
		return driver.findElements(By.tagName(tagName)).size();
	}
	
	public int getElementsCount(By locator) {
		return driver.findElements(locator).size();
	}

	
	public List<String> getAttributesList(String tagName, String attributeName){
		List<String> attrList = new ArrayList<String>();
		
		List<WebElement> elementList = driver.findElements(By.tagName(tagName));
		for(WebElement e: elementList) {
			String text = e.getAttribute(attributeName);
			attrList.add(text);
		}
		return attrList;
	}
	
	public void doClickFromList(By locator, String linkText) {
		List<WebElement> footerList = getElements(locator);
		
		for (int i = 0; i < footerList.size(); i++) {
			String text = footerList.get(i).getText();
			if (text.equals(linkText)) {
				footerList.get(i).click();
				break;
			}
		}
	}
	
	// *********************** Use Action Keys when normal sendKeys and click doesnot work ********************
	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	
	public void doSendKeysWithMoveToElement(By locator, String value) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).sendKeys(value).build().perform();
	}
	
	public void doClickWithMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	// *********************** Elements Displayed ********************
	public boolean doIsDisplayed(By locator) {		
		return getElement(locator).isDisplayed();
	}
	
	
	// ***************************Drop Down Utils
		// ***********************************
	
	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void selectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList =getElements(locator);
		
		for(WebElement e : optionsList) {			
			
			String text = e.getText();
			
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	// ***************************** wait utils ************************
	
	public String waitForTitlePresent(String loginPageTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(loginPageTitle));
		return driver.getTitle();
	}

	

	

	

	




}
