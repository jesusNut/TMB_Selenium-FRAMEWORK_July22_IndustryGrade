package com.jesusnut.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.reports.extentreports.ExtentLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Ulility Class having methods utilizing
 * {@link org.openqa.selenium.JavascriptExecutor}
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JSUtils {

	public static WebElement convertByToWebElement(By locator) {

		return TLDriverManager.getDriver().findElement(locator);

	}

	private static void changeColorUsingJS(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	static void flashUsingJS(WebElement element) {

		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 20; i++) {
			changeColorUsingJS("rgb(0,200,0)", element);
			changeColorUsingJS(bgcolor, element);
		}
	}

	static void drawBorderUsingJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("arguments[0].style.border='3px solid red'", element);

	}

	public static void clickElementUsingJS(WebElement element, String webElementName) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("arguments[0].click();", element);
		ExtentLogger.pass("clicked [ " + webElementName + " ] using JSExecutor");

	}

	public static void refreshBrowserUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("history.go(0)");
		ExtentLogger.info("Refreshed browser using JSExecutor");
	}

	public static void navigateBackUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.history.back()");
		ExtentLogger.info("Navigated back using JSExecutor");
	}

	public static void navigateForwardUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.history.forward()");
		ExtentLogger.info("Navigated forward using JSExecutor");
	}

	public static void navigateToURLUsingJS(String url) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		String script = "window.location = \'" + url + "\'";
		js.executeScript(script);
		ExtentLogger.info("Navigated to URL [" + url + "] using JSExecutor");
	}

	public static String getTitleUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		String title = js.executeScript("return document.title;").toString();
		ExtentLogger.pass("Fetched title [" + title + " ] using JSExecutor");
		return title;
	}

	public static String getURLUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		String url = js.executeScript("return document.URL;").toString();
		ExtentLogger.pass("Fetched URL [" + url + " ] using JSExecutor");
		return url;
	}

	public static String getPageInnerTextUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		ExtentLogger.pass("Got inner text using JSExecutor");
		return pageText;
	}

	public static void scrollDownUsingJSBy300Px() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(0,300)");
		ExtentLogger.info("Scrolled page down vertically by 300 pixels");
	}

	public static void scrollUpUsingJSBy300Px() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(0,-300)");
		ExtentLogger.info("Scrolled page up vertically by 300 pixels");
	}

	public static void scrollRightUsingJSBy300Px() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(300,0)");
		ExtentLogger.info("Scrolled page  horizaontally right side by 300 pixels");
	}

	public static void scrollLeftUsingJSBy300Px() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(-300,0)");
		ExtentLogger.info("Scrolled page  horizaontally left side by 300 pixels");
	}

	public static void scrollUsingJSToEndOfPage() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		ExtentLogger.info("Scrolled page down");
	}

	public static void scrollUsingJSToTopOfPage() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
		ExtentLogger.info("Scrolled page up");
	}

	public static void scrollIntoViewUsingJS(WebElement element, String webElementName) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		ExtentLogger.info("Scrolled page into view for [ " + webElementName + " ]");
	}

	public static String getBrowserInfoUsingJS() {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		return js.executeScript("return navigator.userAgent;").toString();

	}

	public static void sendKeysUsingJSWithId(String id, String value, String webElementName) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
		ExtentLogger.pass("Entered [ " + value + " ] in  [ " + webElementName + " ]");
	}

	public static void sendKeysUsingJSWithName(String name, String value, String webElementName) {
		JavascriptExecutor js = ((JavascriptExecutor) TLDriverManager.getDriver());
		js.executeScript("document.getElementByName('" + name + "').value='" + value + "'");
		ExtentLogger.pass("Entered [ " + value + " ] in  [ " + webElementName + " ]");
	}

	public static void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) TLDriverManager.getDriver();
		// Initially below given if condition will check ready state of page.
		if (("complete").equals(js.executeScript("return document.readyState").toString())) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (("complete").equals(js.executeScript("return document.readyState").toString())) {
				break;
			}
		}

	}

}
