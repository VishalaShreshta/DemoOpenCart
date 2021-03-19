package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By Locators
	By productPageTitle = By.cssSelector("head title");
//	By productPageTitle = By.xpath("//title[contains(text(),'Search -')]");
	By productPageHeader = By.cssSelector("#content h1");
//	By productPageHeader = By.xpath("//a[contains(text(),'Your Store')]");
	By productPageSearch = By.cssSelector("div #input-search");
//	By productCategoryDDL = By.xpath("//select[@name='category_id']");
	By productCategoryDDL = By.cssSelector(".col-sm-3 select[name='category_id']");
	By productSubCategory = By.cssSelector(".checkbox-inline [name='sub_category']");
	By productDescriptionSearch = By.cssSelector(".checkbox-inline #description");
	By productSearchButton = By.cssSelector("input#button-search");
	By productSearchResults = By.cssSelector(".product-layout .product-thumb");
	By productMetaData = By.cssSelector(".col-sm-4 .list-unstyled:nth-of-type(1) li");
	By productPrice = By.cssSelector(".col-sm-4 .list-unstyled:nth-of-type(2) li");
	By productQuantity = By.id("input-quantity");
	By addToCartButton = By.id("button-cart");
	By successMsgAddToCart = By.cssSelector("div.alert-success");
	By productImages = By.cssSelector(".thumbnails li img");
	By cartTotal = By.cssSelector("span#cart-total");
	By cartImages = By.cssSelector(".table-striped tr");
	

	
	
	//2. Constructor of the page class:
	public ProductInfoPage(WebDriver driver) {
		
		this.driver=driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions:
	@Step("getting product page title...")
	public String getProductPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.PRODUCT_PAGE_TITLE, 10);
	}
	
	@Step("getting product page header...")
	public String getProductPageHeader() {
		if(elementUtil.doIsDisplayed(productPageHeader)) {
			return elementUtil.doGetText(productPageHeader);
		}
		
		return null;
	}
	
	@Step("search for product...")
	public boolean productSearch() {
		
		elementUtil.doSendKeys(productPageSearch, "Mac");
		elementUtil.doSelectDropDownByVisibleText(productCategoryDDL,"Laptops & Notebooks");
		elementUtil.doClick(productSubCategory);
		elementUtil.doClick(productDescriptionSearch);
		elementUtil.doClick(productSearchButton);
		
		if(elementUtil.getElementsCount(productSearchResults) > 0) {
			return true;
		}
		return false;
		
	}
	
	@Step("get product details...")
	public Map<String, String> getProductInformation(){
		
		Map<String, String> productInfoMap = new HashMap<>();
		productInfoMap.put("name", elementUtil.doGetText(productPageHeader).trim());
		
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		for(WebElement e : productMetaDataList) {
			
			productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		
		return productInfoMap;
		
	}
	
	@Step("select product quantity...")
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(productQuantity, qty);
	}
	
	@Step("Add product to cart....")
	public String addToCart() {
		elementUtil.doClick(addToCartButton);
		return elementUtil.doGetText(successMsgAddToCart);
	}
	
	@Step("count number of product images")
	public int getProductImages() {
		int imagesCount = elementUtil.getElements(productImages).size();
		System.out.println("total product images : "+imagesCount);
		return imagesCount;
	}
		
	@Step("getting product info page title...")
	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForTitlePresent(productName, 5);	
	}
	

}
