package com.jesusnut.pages;

import org.openqa.selenium.By;

import com.jesusnut.utils.Ignitor;

public class AccountSuccessPage {

	private static final By TXTHOLDER_YOURACCOUNTHASBEENCREATED = By.xpath("//div[@id='content']/h1");

	public String getConfirmationTextForSuccessfulAccountCreation() {

		return Ignitor.getElementText(TXTHOLDER_YOURACCOUNTHASBEENCREATED);
	}

}
