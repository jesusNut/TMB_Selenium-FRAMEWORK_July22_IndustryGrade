package com.jesusnut.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jesusnut.reports.extentreports.ExtentLogger;
import com.jesusnut.utils.Ignitor;

public class ProductInfoPage {

	private static final By SECTION_PRODUCTINFO = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");

	private static final By SECTION_PRICEINFO = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");

	public Map<String, String> getProductDetails() {

		Map<String, String> productDetails = new HashMap<>();

		List<WebElement> webElement = Ignitor.getListOfElementsPresent(SECTION_PRODUCTINFO, "Product info");

		for (WebElement webTemp : webElement) {

			String temp = webTemp.getText();

			productDetails.put(temp.split(":")[0].trim(), temp.split(":")[1].trim());

		}

		List<WebElement> webElement1 = Ignitor.getListOfElementsPresent(SECTION_PRICEINFO, "Price info");

		String temp = webElement1.get(0).getText().trim();

		productDetails.put("Price", temp);

		temp = webElement1.get(1).getText().trim();

		productDetails.put(temp.split(":")[0].trim(), temp.split(":")[1].trim());

		ExtentLogger.pass("Extracted product details of specific product in a map.");

		return productDetails;

	}

}
