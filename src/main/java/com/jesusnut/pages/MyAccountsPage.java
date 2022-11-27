package com.jesusnut.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.jesusnut.pages.pagecomponents.myaccountspagecomponents.FooterMenuComponent;
import com.jesusnut.pages.pagecomponents.myaccountspagecomponents.TopMenuComponent;
import com.jesusnut.reports.extentreports.ExtentLogger;
import com.jesusnut.utils.Ignitor;

public class MyAccountsPage {

	private static final By HEADER_FIRST_LEFTMENU = By.xpath("//div[@id='content']/h2[1]");
	private static final By SEARCHBOX = By.xpath("//div[@id='search']/input");
	private static final By SEARCH_BUTTON = By.xpath("//span[@class='input-group-btn']/button");
	private TopMenuComponent topMenuComponent;
	private FooterMenuComponent footerMenuComponent;

	// MyAccountsPage can be divided to multiple subclasses because of large no.
	// locators
	// on MyAcccountsPage and composition can be used to
	// establish relation between MyAccountsPage and its subclasses-
	// 'TopMenuComponent'
	// & 'FooterMenuComponent'

	// constructor

	public MyAccountsPage() {
		this.topMenuComponent = new TopMenuComponent();
		this.footerMenuComponent = new FooterMenuComponent();
	}

	// MyAccountsPage specific method -1

	public String getTextOfFirstHeaderOfLeftMenu() {

		return Ignitor.getElementText(HEADER_FIRST_LEFTMENU);

	}

	// method using TopMenuComponent class

	public String getTitleFromLogoOnTopMenu() {

		return topMenuComponent.getLogoTitle();

	}

	// method using TopMenuComponent class

	public List<String> getCategoryNamesTopMenuNavBarUnderLogo() {

		return topMenuComponent.getCategoryNamesTopMenuNavBar();

	}

	// method using FooterMenuComponent class

	public String getCompanyNameOnPoweredByLinkAtFooter() {

		return footerMenuComponent.getCompanyNameFromLinkPoweredBy();

	}

	// MyAccountsPage specific method -2

	public String getMyAccountsPageTitle() {

		return Ignitor.getTitle();

	}

	// MyAccountsPage specific method -3

	/**
	 * This method will search for the product given as Param.
	 * 
	 * @param productname name of the product to search
	 * @return Search Page instance
	 */

	public SearchPage searchProduct(String productname) {

		Ignitor.doSendKeys(SEARCHBOX, productname, "Search box");
		Ignitor.doClick(SEARCH_BUTTON, "Search button");
		ExtentLogger.pass("Searched product name [ " + productname + " ]");

		return new SearchPage();

	}

}
