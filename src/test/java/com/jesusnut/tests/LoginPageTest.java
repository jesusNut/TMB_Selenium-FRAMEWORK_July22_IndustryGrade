package com.jesusnut.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.AUTPageConstants;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.MyAccountsPage;

public class LoginPageTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify title of Login Page", groups = { "Regression" })
	public void verifyLoginPageTitleTest() {

		LoginPage loginpage = new LoginPage();

		String actualLoginPageTitle = loginpage.getLoginPageTitle();

		Assertions.assertThat(actualLoginPageTitle).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.LOGIN_PAGE_TITLE);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify url of Login Page", groups = { "Regression" })
	public void verifyLoginPageURLTest() {

		LoginPage loginpage = new LoginPage();

		String actualLoginPageURL = loginpage.getLoginPageURL();

		Assertions.assertThat(actualLoginPageURL).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.LOGIN_PAGE_URL);

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "To verify 'new customer' section exists on Login Page", groups = { "Regression" })
	public void verifyNewCustomerSectionTest() {

		LoginPage loginpage = new LoginPage();

		boolean isNewCutsomerSectionExist = loginpage.isNewCustomerSectionExistsOnLoginPage();

		Assertions.assertThat(isNewCutsomerSectionExist).isTrue();

	}

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "Verify user is able to login successfully and lands up on MyAccountsPage", groups = {
			"Regression" })

	public void verifySuccessfulLoginTest() {

		LoginPage loginpage = new LoginPage();

		MyAccountsPage myAccountsPage = loginpage.loginToApplication(ConfigFactory.getUserName(),
				ConfigFactory.getPassword());

		String myAccountsPageTitle = myAccountsPage.getMyAccountsPageTitle();

		Assertions.assertThat(myAccountsPageTitle).isNotBlank().isNotNull().isNotEmpty()
				.contains(AUTPageConstants.MYACCOUNTS_PAGE_TITLE);

		// checking that after user logins, he/she should land on MyAccounts page. So
		// verifying title of MyAccountPage. We can also check existance of certain
		// unique
		// elements on MyAccounts page as well.
	}

}
