package com.jesusnut.pages;

import org.openqa.selenium.By;

import com.jesusnut.reports.extentreports.ExtentLogger;
import com.jesusnut.utils.Ignitor;

public class SearchPage {

	private static final By TOTALPRODUCTSANDPAGESROW = By.xpath("//div[@class='col-sm-6 text-right']");

	private static final By LIST_SEARCHRESULTS = By.xpath("//div[@id='content']//div[@class='product-thumb']//h4/a");

	private String extractTotalProductsDisplayedOnSearch() {

		String tempData = Ignitor.getElementText(TOTALPRODUCTSANDPAGESROW).split("of")[1].trim();

		return tempData.split("\\(")[0].trim();

	}

	/*
	 * This methdod will extract total number of results for product searched on
	 * MyAccounts Page.
	 * 
	 * e.g. 2 will be extracted [from String "Showing 1 to 2 of 2 (1 Pages)" on
	 * Search Page] and returned on seraching "samsung" product on MyAccounts Page.
	 * 
	 * @return int : number of results for product searched on MyAccounts Page.
	 */

	public int totalNumberOfProductsOnSerach() {

		int temp = Integer.parseInt(extractTotalProductsDisplayedOnSearch());

		ExtentLogger.pass("Total no. of products found are [" + temp + " ] ");

		return temp;
	}

	public ProductInfoPage selectSpecificProductFromSearchResult(String productName) {

		Ignitor.selectValueFromListOfWebElementsPresent(LIST_SEARCHRESULTS, productName);

		ExtentLogger.pass("Specific product [ " + productName + " ]  selected and clicked from the Search Results. ");

		return new ProductInfoPage();

	}

}
