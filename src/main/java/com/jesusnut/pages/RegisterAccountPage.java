package com.jesusnut.pages;

import org.openqa.selenium.By;

import com.github.javafaker.Faker;
import com.jesusnut.utils.Ignitor;

public class RegisterAccountPage {

	private static final By TXTBOX_FIRSTNAME = By.xpath("//input[@id='input-firstname']");
	private static final By TXTBOX_LASTNAME = By.xpath("//input[@id='input-lastname']");
	private static final By TXTBOX_EMAIL = By.xpath("//input[@id='input-email']");
	private static final By TXTBOX_TELEPHONE = By.xpath("//input[@id='input-telephone']");
	private static final By TXTBOX_PASSWORD = By.xpath("//input[@id='input-password']");
	private static final By TXTBOX_CONFIRMPASSWORD = By.xpath("//input[@id='input-confirm']");
	private static final By RADIOBTN_SUBSCRIBE_OPTN__NO = By.xpath("//input[@type='radio' and @value='0']");
	private static final By CHCKBOX_PRIVACYPOLICY = By.xpath("//input[@type='checkbox']");
	private static final By BTN_CONTINUE = By.xpath(" //input[@type='submit']");

	public AccountSuccessPage createAccount() {

		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = firstName + "." + lastName + "@" + faker.internet().domainName();
		String fakerExperessionForPassword = faker.internet().password(7, 10, true);
		Ignitor.doSendKeys(TXTBOX_FIRSTNAME, firstName, "FirstName");
		Ignitor.doSendKeys(TXTBOX_LASTNAME, lastName, "LastName");
		Ignitor.doSendKeys(TXTBOX_EMAIL, email, "Email");
		Ignitor.doSendKeys(TXTBOX_TELEPHONE, faker.numerify("#########"), "Telephone");
		Ignitor.doSendKeys(TXTBOX_PASSWORD, fakerExperessionForPassword, "Password");
		Ignitor.doSendKeys(TXTBOX_CONFIRMPASSWORD, fakerExperessionForPassword, "Confirm Password");
		Ignitor.doClick(RADIOBTN_SUBSCRIBE_OPTN__NO, "Radio button for Subscription-no");
		Ignitor.doClick(CHCKBOX_PRIVACYPOLICY, "Privacy policy checkbox");
		Ignitor.doClick(BTN_CONTINUE, "Continue");

		return new AccountSuccessPage();

	}

}
