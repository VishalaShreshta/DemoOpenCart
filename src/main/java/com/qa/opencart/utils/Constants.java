package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";

	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";

	public static final int ACCOUNTS_SECTIONS = 4;
	
	public static final String ACCOUNT_SUCCESS_MESSG = "Your Account Has Been Created";
	
	public static final String PRODUCT_PAGE_TITLE = "Search - Mac";
	public static final String PRODUCT_PAGE_HEADER = "Your Store";
	
	public static List<String> getAccountSectionsList(){
		
		List<String> accntSecList = new ArrayList<>();
		accntSecList.add("My Account");
		accntSecList.add("My Orders");
		accntSecList.add("My Affiliate Account");
		accntSecList.add("Newsletter");
		return accntSecList;
		
	}

}
