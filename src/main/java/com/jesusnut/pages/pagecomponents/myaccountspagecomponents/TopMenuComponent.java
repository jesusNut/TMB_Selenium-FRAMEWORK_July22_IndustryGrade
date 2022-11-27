package com.jesusnut.pages.pagecomponents.myaccountspagecomponents;

import java.util.List;

import org.openqa.selenium.By;

import com.jesusnut.utils.Ignitor;

public class TopMenuComponent {

	private static final By LOGO_MAIN = By.xpath("//div[@id='logo']/a/img");
	private static final By TOPMENU_NAVBAR_CATEGORY_ELEMENTS = By
			.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li/a[1]");

	public String getLogoTitle() {

		return Ignitor.getAttributeValue(LOGO_MAIN, "title").trim();

	}

	// The menu just under logo having names as : desktop, Laptop and Notebooks,
	// Components etc.
	public List<String> getCategoryNamesTopMenuNavBar() {

		return Ignitor.getElementsTextListPresentAndVisible(TOPMENU_NAVBAR_CATEGORY_ELEMENTS,
				"Top Menu - Naviagtion Bar with Categories List like desktop etc.");
	}

}
