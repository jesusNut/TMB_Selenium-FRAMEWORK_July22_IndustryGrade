package com.jesusnut.pages.pagecomponents.myaccountspagecomponents;

import org.openqa.selenium.By;

import com.jesusnut.utils.Ignitor;

public class FooterMenuComponent {

	private static final By LINK_POWEREDBY_FOOTER = By.xpath("//footer//p/a");

	public String getCompanyNameFromLinkPoweredBy() {

		return Ignitor.getElementText(LINK_POWEREDBY_FOOTER).trim();

	}

}
