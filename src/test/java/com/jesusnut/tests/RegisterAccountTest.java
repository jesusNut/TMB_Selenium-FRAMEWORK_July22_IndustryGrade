package com.jesusnut.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.constants.AUTPageConstants;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.RegisterAccountPage;
import com.jesusnut.utils.Ignitor;

public class RegisterAccountTest extends BaseTest {

	@FrameworkAnnotation(category = { Category.REGRESSION })
	@Test(description = "Registering a new cutsomer and verifying if user lands on AccountSuccess Page", groups = {
			"Regression" })

	public void verifyNewCustomerRegistrationTest() {

		LoginPage loginpage = new LoginPage();
		RegisterAccountPage registerAccountPage = loginpage.clickRegisterButton();
		registerAccountPage.createAccount();
		String accountSuccessPageTitle = Ignitor.getTitle();

		Assertions.assertThat(accountSuccessPageTitle).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.ACCOUNTSUCCESS_PAGE_TITLE);

	}

}
