package com.jesusnut.tests;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.AUTPageConstants;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.MyAccountsPage;

public class MyAccountsPageTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To check whether text of FirstHeader Of LeftMenu on MyAccountsPage after login is correct or not", groups = {
			"Regression" })

	public void verifyTextOfFirstHeaderOfLeftMenuTest() {

		LoginPage loginpage = new LoginPage();

		MyAccountsPage myAccountspage = loginpage.loginToApplication(ConfigFactory.getUserName(),
				ConfigFactory.getPassword());

		String actualTextOfFirstHeaderOfLeftMenu = myAccountspage.getTextOfFirstHeaderOfLeftMenu();

		Assertions.assertThat(actualTextOfFirstHeaderOfLeftMenu).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.MYACCOUNTS_PAGE_LEFTMENU_FIRSTHEADER);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify if title of logo on Top Menu on MyAccountsPage after login is correct or not", groups = {
			"Regression" })

	public void verifyTitleOfLogOnTopMenuOnMyAccountsPageTest() {

		LoginPage loginpage = new LoginPage();

		String actualTitleOfLogOnTopMenu = loginpage
				.loginToApplication(ConfigFactory.getUserName(), ConfigFactory.getPassword())
				.getTitleFromLogoOnTopMenu();

		Assertions.assertThat(actualTitleOfLogOnTopMenu).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.MYACCOUNTS_PAGE_LOGO_TITLE);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify if company name of Powered By link at footer on MyAccounts Page after login is correct or not", groups = {
			"Regression" })

	public void verifyNameOfCompanyOnPoweredByLinkAtFooterTest() {

		LoginPage loginpage = new LoginPage();

		String actualNameOfCompanyNameLinkAtFooter = loginpage
				.loginToApplication(ConfigFactory.getUserName(), ConfigFactory.getPassword())
				.getCompanyNameOnPoweredByLinkAtFooter();

		Assertions.assertThat(actualNameOfCompanyNameLinkAtFooter).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.MYACCOUNTS_PAGE_COMPANYNAME_POWEREDBYLINK_FOOTER);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify title of MyAccounts Page", groups = { "Regression" })

	public void verifyMyAccountsPageTitleTest() {

		LoginPage loginpage = new LoginPage();

		String actualMyAccountsPageTitle = loginpage
				.loginToApplication(ConfigFactory.getUserName(), ConfigFactory.getPassword()).getMyAccountsPageTitle();

		Assertions.assertThat(actualMyAccountsPageTitle).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.MYACCOUNTS_PAGE_TITLE);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify category names e.g -desktop, laptop and Notebooks etc. on Top Menu Nav Bar under logo on MyAccountsPage", groups = {
			"Regression" })

	public void verifyCatgeoryNamesTopMenuNavBar() {

		LoginPage loginpage = new LoginPage();

		MyAccountsPage myAccountsPage = loginpage.loginToApplication(ConfigFactory.getUserName(),
				ConfigFactory.getPassword());

		List<String> listOfCategoriesOnTopMenuNavBarUnderLogo = myAccountsPage.getCategoryNamesTopMenuNavBarUnderLogo();

		List<String> expectedListOfCategoriesOnTopMenuNavBarUnderLogo = AUTPageConstants.MYACCOUNTS_PAGE_CATEGORYNAMES_TOPMENU_NAVBAR;

		Collections.sort(listOfCategoriesOnTopMenuNavBarUnderLogo);
		Collections.sort(expectedListOfCategoriesOnTopMenuNavBarUnderLogo);

		Assertions.assertThat(listOfCategoriesOnTopMenuNavBarUnderLogo)
				.hasSize(AUTPageConstants.MYACCOUNTS_PAGE_CATEGORYNAMES_TOPMENU_NAVBAR_COUNT)
				.isEqualTo(expectedListOfCategoriesOnTopMenuNavBarUnderLogo);

	}

}
