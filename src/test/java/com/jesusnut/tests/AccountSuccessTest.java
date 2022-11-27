 package com.jesusnut.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.jesusnut.annotations.FrameworkAnnotation;
import com.jesusnut.base.BaseTest;
import com.jesusnut.constants.AUTPageConstants;
import com.jesusnut.enums.Category;
import com.jesusnut.pages.AccountSuccessPage;
import com.jesusnut.pages.LoginPage;
import com.jesusnut.pages.RegisterAccountPage;

public class AccountSuccessTest extends BaseTest {

	@FrameworkAnnotation(category = {Category.REGRESSION})
	@Test(description = "Verify \"Your account has been created \" text is displayed on AccountSuccess Page when an account is created on RegisterAccount Page", groups = {"Regression"})

	public void verifySuccessTextIsDisplayed() {

		LoginPage loginpage = new LoginPage();
		RegisterAccountPage registerAccount = loginpage.clickRegisterButton();
		AccountSuccessPage accountSuccess = registerAccount.createAccount();
		String actualText = accountSuccess.getConfirmationTextForSuccessfulAccountCreation();
		Assertions.assertThat(actualText).isNotBlank().isNotEmpty().isNotNull()
				.contains(AUTPageConstants.ACCOUNTSUCCESS_PAGE_CONFIRMATIONTEXT);

	}

}
