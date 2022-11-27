package com.jesusnut.pages;

import org.openqa.selenium.By;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.reports.extentreports.ExtentLogger;
import com.jesusnut.utils.Ignitor;

public class LoginPage {

	private static final By TXTBOX_EMAIL = By.xpath("//input[@id='input-email']");
	private static final By TXTBOX_PASSWORD = By.xpath("//input[@id='input-password']");
	private static final By BTN_LOGIN = By.xpath("//input[@type='submit']");
	private static final By SECTION_NEWCUSTOMER = By.xpath("//h2[text()='New Customer']/parent::div");
	private static final By REGISTER_LINK = By.xpath("//a[text()='Register' and ./parent::div[@class='list-group']]");

	private LoginPage setUsername(String username) {

		Ignitor.doSendKeys(TXTBOX_EMAIL, username, "Email InputBox");

		return this;

	}

	private LoginPage setPassword(String password) {

		Ignitor.doSendKeys(TXTBOX_PASSWORD, password, "Password InputBox");

		return this;

	}

	private MyAccountsPage doLogin() {

		Ignitor.doClick(BTN_LOGIN, "Login Button");

		return new MyAccountsPage();

	}

	public MyAccountsPage loginToApplication(String username, String password) {

		MyAccountsPage myAccountsPage = setUsername(username).setPassword(password).doLogin();

		ExtentLogger.pass("Logged in to the application with Username [ " + username + " ] and Password [ "
				+ ConfigFactory.getEncryptedPasswordFromConfig() + " ]");

		return myAccountsPage;

	}

	public String getLoginPageTitle() {

		return Ignitor.getTitle();
	}

	public String getLoginPageURL() {

		return Ignitor.getURL();
	}

	public boolean isNewCustomerSectionExistsOnLoginPage() {

		return Ignitor.verifyWebElementDisplayed(SECTION_NEWCUSTOMER, "New Cutsomer Section");
	}

	public RegisterAccountPage clickRegisterButton() {

		Ignitor.doClick(REGISTER_LINK, "Register Link on Right Menu List");

		return new RegisterAccountPage();

	}

}