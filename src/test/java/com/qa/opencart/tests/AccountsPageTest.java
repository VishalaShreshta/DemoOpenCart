package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-200: Design Accounts Page")
@Story("US - 201: designing the accounts page with title, header, account and product results..")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void AccountsPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Accounts page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountPageTitle();
		System.out.println("accounts page title is :" + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("accounts page header test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void accountPageHeaderTest() {
		String header = accountsPage.getAccountPageHeader();
		System.out.println("Accounts page header value is: " + header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Description("accounts page section count")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void verifyMyAccountSectionsCountTest() {
		int sectionsCount = accountsPage.getAccountSectionsCount();
		System.out.println("Number of sections in accounts page: "+sectionsCount);
		Assert.assertEquals(sectionsCount, Constants.ACCOUNTS_SECTIONS);
	}
	
	@Description("accounts Page sections list Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void verifyMyAccountsSectionsListTest() {
		Assert.assertEquals(accountsPage.getAccountSectionsList(), Constants.getAccountSectionsList());
	}
	
	@Description("accounts page search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void searchTest() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}
	
	@Description("Navigating to account details page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	public AccountDetailsPage navigateAccountDetailsPageTest() {
		accountsPage.getAccountsPageDetails();
		return new AccountDetailsPage(driver);
	}
}
