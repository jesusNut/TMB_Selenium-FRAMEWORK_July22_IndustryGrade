package com.jesusnut.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jesusnut.config.ConfigFactory;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.constants.SynchronizationConstants;
import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.enums.WaitStrategy;
import com.jesusnut.exception.FrameworkException;
import com.jesusnut.reports.extentreports.ExtentLogger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class having methods which can be applied on WebDriver, WebElements
 * and Other Browser Components- e.g. Alerts, Windows etc.<br>
 * Also, manages the WebDriver Wait time.
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

//TODO Write custom function to upload file as per requirement.
//TODO Write custom function to upload multiple files as per requirement.
//TODO Write custom function to download files as per requirement and its related methods, e.g. to check if download successful, folders for downloaded files etc.

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Ignitor {

	private static final long MILLIS_DURATION_200 = 200;
	private static final long MILLIS_DURATION_300 = 300;
	private static final long MILLIS_DURATION_500 = 500;

	// ************* WebDriverWait instance supplier *****************//

	/**
	 * Method to return WebDriverWait instance with timeout specified for
	 * WebElements in
	 * {@link com.jesusnut.constants.SynchronizationConstants#getDefaultElementTimeOut()}
	 * 
	 * @return WebDriverWait wait
	 */

	private static WebDriverWait webDriverWaitSupplierForElement() {

		return new WebDriverWait(TLDriverManager.getDriver(),
				Duration.ofSeconds(SynchronizationConstants.getDefaultElementTimeOut()));

	}

	/**
	 * Method to return WebDriverWait instance with timeout specified for non
	 * WebElements in
	 * {@link com.jesusnut.constants.SynchronizationConstants#getDefaultTimeOut()}
	 * 
	 * @return WebDriverWait wait
	 */

	private static WebDriverWait webDriverWaitSupplierForNonElement() {

		return new WebDriverWait(TLDriverManager.getDriver(),
				Duration.ofSeconds(SynchronizationConstants.getDefaultTimeOut()));

	}

	// ********************* Helper Methods **************************//

	/**
	 * Waits for a WebElement and then returns it as per the wait strategy defined
	 * in {@link com.jesusnut.enums.WaitStrategy}
	 * 
	 * @param locator      locator of Webelement on WebPage
	 * @param waitStrategy waitStrategy
	 * @return WebElement
	 * @throws com.jesusnut.exception.FrameworkException If the Wait Startegy
	 *                                                   provided is not supported
	 *                                                   by JesusNut Framework.
	 */

	private static WebElement waitForElement(By locator, WaitStrategy waitStrategy) {

		WebDriverWait wait = webDriverWaitSupplierForElement();

		if (waitStrategy == (WaitStrategy.PRESENCE)) {
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} else if (waitStrategy == (WaitStrategy.VISIBLE)) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Wrong Wait Strategy provided.");
		}

	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page || An expectation for checking that all elements present on the web
	 * page that match the locator are visible.
	 * 
	 * @param locator      locator of Webelement on WebPage
	 * @param waitStrategy waitStrategy
	 * @return List list of WebElement
	 * @throws com.jesusnut.exception.FrameworkException If the Wait Startegy
	 *                                                   provided is not supported
	 *                                                   by JesusNut Framework.
	 */

	private static List<WebElement> waitForAllElements(By locator, WaitStrategy waitStrategy) {

		WebDriverWait wait = webDriverWaitSupplierForElement();

		if (waitStrategy == (WaitStrategy.PRESENCE)) {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		} else if (waitStrategy == (WaitStrategy.VISIBLE)) {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} else {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Wrong Wait Strategy provided.");
		}

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible and Clickable.
	 * 
	 * @param locator locator of Webelement on WebPage
	 * @return boolean - status whether the WebElement is visible AND clickable or
	 *         not.
	 */

	private static boolean isElementVisiblePresentAndClickable(By locator) {

		WebDriverWait wait = webDriverWaitSupplierForElement();

		try {

			return wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(locator),
					ExpectedConditions.elementToBeClickable(locator)));
		} catch (org.openqa.selenium.TimeoutException e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Expected condition failed: Element located by By.xpath: //a[text()='Running Windows Network Diagnostics'] either not visible  && present OR Element not clickable.",
					e);
		}

	}

	/**
	 * Provides a WebElement based on locator to Other methods after waiting as per
	 * Wait Strategy defined in {@link com.jesusnut.enums.WaitStrategy}
	 * 
	 * @param locator      locator of Webelement on WebPage
	 * @param waitStrategy waitStrategy
	 * @return WebElement
	 * @throws com.jesusnut.exception.FrameworkException If webelement specified by
	 *                                                   locator could not be found
	 * 
	 */

	private static WebElement getElement(By locator, WaitStrategy waitStrategy) {

		try {

			WebElement webElement = waitForElement(locator, waitStrategy);

			if (ConfigFactory.hasHighlightElements()) {
				JSUtils.drawBorderUsingJS(webElement);
			}
			return webElement;
		}

		catch (Exception e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Could not find the webelement specified by locator : ["
							+ locator.toString() + " ] using Wait Strategy : [ " + waitStrategy.toString() + " ].",
					e);
		}
	}

	/**
	 * Provides a LIST of WebElements based on locator to Other methods after
	 * waiting as per Wait Strategy defined in
	 * {@link com.jesusnut.enums.WaitStrategy}
	 * 
	 * @param locator      locator of Webelement on WebPage
	 * @param waitStrategy waitStrategy
	 * @return List list of WebElement
	 * @throws com.jesusnut.exception.FrameworkException If webelement(s) specified
	 *                                                   by locator could not be
	 *                                                   found
	 */
	private static List<WebElement> getElements(By locator, WaitStrategy waitStrategy) {

		try {
			return waitForAllElements(locator, waitStrategy);
		}

		catch (Exception e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Could not find the webelement(s) specified by locator : ["
							+ locator.toString() + " ] using Wait Strategy : [ " + waitStrategy.toString() + " ].",
					e);
		}
	}

	// ************ WebDriver Action Methods *************//

	public static String getURL() {
		SmartWaitUtils.waitForPageLoaded();
		String url = TLDriverManager.getDriver().getCurrentUrl();
		ExtentLogger.pass("Fetched title : " + FrameworkConstants.BOLD_START + " [" + url + "] "
				+ FrameworkConstants.BOLD_END + " for the webpage");
		return url;
	}

	public static boolean verifyURLToBe(String expectedUrl) {

		SmartWaitUtils.waitForPageLoaded();
		return TLDriverManager.getDriver().getCurrentUrl().equals(expectedUrl);

	}

	public static boolean verifyURLToContain(String fraction) {

		SmartWaitUtils.waitForPageLoaded();
		return TLDriverManager.getDriver().getCurrentUrl().contains(fraction);

	}

	public static String getTitle() {
		SmartWaitUtils.waitForPageLoaded();
		String title = TLDriverManager.getDriver().getTitle();
		ExtentLogger.pass("Fetched title :" + FrameworkConstants.BOLD_START + " [" + title + "] "
				+ FrameworkConstants.BOLD_END + " for the webpage");
		return title;
	}

	public static boolean verifyTitleToBe(String expectedTitle) {

		SmartWaitUtils.waitForPageLoaded();
		return TLDriverManager.getDriver().getTitle().equals(expectedTitle);

	}

	public static boolean verifyTitleToContain(String fraction) {

		SmartWaitUtils.waitForPageLoaded();
		return TLDriverManager.getDriver().getTitle().contains(fraction);

	}

	public static void navigateToURL(String url) {

		ExtentLogger.info("Navigating to URL [ " + url + " ]");
		TLDriverManager.getDriver().navigate().to(url);

	}

	public static void launchURL(String url) {

		TLDriverManager.getDriver().get(url);

	}

	public static void closeCurrentFocusedWindow() {

		ExtentLogger.info("Closing window having title : [ " + Ignitor.getTitle() + " ]");
		TLDriverManager.getDriver().close();

	}

	public static void quitDriver() {

		ExtentLogger.info("Quitting driver session and all associated windows");
		TLDriverManager.getDriver().quit();

	}

	public static void navigateBack() {

		ExtentLogger.info("Navigating back");
		TLDriverManager.getDriver().navigate().back();

	}

	public static void navigateForward() {

		ExtentLogger.info("Navigating forward");
		TLDriverManager.getDriver().navigate().forward();

	}

	public static void refreshPage() {

		ExtentLogger.info("Refreshing current webpage");
		TLDriverManager.getDriver().navigate().refresh();

	}

	public static void maximizeWindow() {

		TLDriverManager.getDriver().manage().window().maximize();

	}

	public static void minimizeWindow() {

		ExtentLogger.info("Minimizing browser window");
		TLDriverManager.getDriver().manage().window().minimize();

	}

	public static int[] getWindowCoordinates() {

		Point point = TLDriverManager.getDriver().manage().window().getPosition();

		int xcord = point.getX();
		int ycord = point.getY();
		ExtentLogger.info("(X,Y) coodinate of window is (" + xcord + " , " + ycord + ")");
		return new int[] { xcord, ycord };

	}

	public static int[] getWindowSize() {

		Dimension dim = TLDriverManager.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		ExtentLogger.info("Height of window is : [" + height + "] and width is : [" + width + "]");
		return new int[] { height, width };

	}

	public static void setWindowCoordinates(int xcord, int ycord) {

		Point point = new Point(xcord, ycord);
		TLDriverManager.getDriver().manage().window().setPosition(point);
		ExtentLogger.info("Set window coordinates at : (" + xcord + "," + ycord + ")");

	}

	public static void setWindowSize(int width, int height) {

		Dimension dim = new Dimension(width, height);
		TLDriverManager.getDriver().manage().window().setSize(dim);
		ExtentLogger.info("Set window size at : (width, height) as : (" + width + "," + height + ")");

	}

	public static void setFullScreenWindow() {

		TLDriverManager.getDriver().manage().window().fullscreen();
		ExtentLogger.info("Setting window to Fullscreen");

	}

	// ************* WebElement Related Methods************************//

	public static void doClick(By locator, String webElementName) {

		if (isElementVisiblePresentAndClickable(locator)) {

			WebElement element = getElement(locator, WaitStrategy.PRESENCE);
			element.click();
			ExtentLogger.pass("[" + webElementName + "] is clicked.");
		}

	}

	public static void doClickAlertButton(By locator, String webElementName) {

		if (isElementVisiblePresentAndClickable(locator)) {

			WebElement element = getElement(locator, WaitStrategy.PRESENCE);
			element.click();
			ExtentLogger.info("[" + webElementName + "] is clicked which might open an alert");
		}

	}

	public static void doSendKeys(By locator, String value, String webElementName) {

		WebElement element = getElement(locator, WaitStrategy.VISIBLE);
		element.sendKeys(value);

		String valueFeededToLogger = (webElementName.toLowerCase().contains("password")
				|| webElementName.toLowerCase().contains("key")) ? EncodeDecodeUtils.encrypt(value) : value;
		ExtentLogger.pass(" [ " + valueFeededToLogger + " ] is entered in " + webElementName);

	}

	public static void doSendModifierKeys(By locator, String webElementName, CharSequence... value) {

		WebElement element = getElement(locator, WaitStrategy.VISIBLE);
		element.sendKeys(value);
		ExtentLogger.pass(" Sequence of Modified Keys is entered in " + webElementName);

	}

	public static void doPressKeyOnActiveElement(Keys key, String keyName) {

		WebElement webElement = TLDriverManager.getDriver().switchTo().activeElement();
		webElement.sendKeys(key);
		ExtentLogger.pass("[" + keyName + "] is pressed");

	}

	public static void doActionsSendKeys(By locator, String value, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.sendKeys(getElement(locator, WaitStrategy.PRESENCE), value).perform();
		ExtentLogger.pass(" [ " + value + " ] is entered in [" + webElementName + "] using Actions Class");
	}

	public static void doActionsClick(By locator, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.click(getElement(locator, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.pass("[" + webElementName + "] is clicked using Actions Class");
	}

	public static void doActionsRightClick(By locator, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.contextClick(getElement(locator, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.pass("[" + webElementName + "] is context-clicked using Actions Class");
	}

	public static void doActionsDoubleClick(By locator, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.doubleClick(getElement(locator, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.pass("[" + webElementName + "] is double-clicked using Actions Class");
	}

	public static void doActionsDoubleClickAlertButton(By locator, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.doubleClick(getElement(locator, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.info("[" + webElementName + "] is double-clicked using Actions Class which might open an alert");
	}

	public static void doActionsMouseHover(By locator, String webElementName) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.moveToElement(getElement(locator, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.pass("Hovered mouse on - [" + webElementName + "]");
	}

	public static void doActionsClickHoldMoveRelease(By source, By target) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.clickAndHold(getElement(source, WaitStrategy.PRESENCE)).pause(Duration.ofMillis(MILLIS_DURATION_300))
				.moveToElement(getElement(target, WaitStrategy.PRESENCE)).release().perform();
		ExtentLogger.pass("Performed Click, Hold and Released");

	}

	public static void doActionsDragAndDrop(By source, By target) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.dragAndDrop(getElement(source, WaitStrategy.PRESENCE), getElement(target, WaitStrategy.PRESENCE)).perform();
		ExtentLogger.pass("Performed Drag and Drop");

	}

	public static void doActionsSlideLeftByOffset(By sliderButton, int offset) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.clickAndHold(getElement(sliderButton, WaitStrategy.PRESENCE)).moveByOffset(-(offset), 0)
				.pause(Duration.ofMillis(MILLIS_DURATION_200)).release(getElement(sliderButton, WaitStrategy.PRESENCE))
				.perform();
		ExtentLogger.pass("Performed Sliding operation on left side");

	}

	public static void doActionsSlideRightByOffset(By sliderButton, int offset) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.clickAndHold(getElement(sliderButton, WaitStrategy.PRESENCE)).moveByOffset(offset, 0)
				.pause(Duration.ofMillis(MILLIS_DURATION_200)).release(getElement(sliderButton, WaitStrategy.PRESENCE))
				.perform();
		ExtentLogger.pass("Performed Sliding operation on right side");

	}

	public static void doActionsResizeByOffset(By sliderButton, int xoffset, int yoffset) {

		Actions act = new Actions(TLDriverManager.getDriver());
		act.dragAndDropBy(getElement(sliderButton, WaitStrategy.PRESENCE), xoffset, yoffset).perform();
		ExtentLogger.pass("Resized by offset");

	}

	public static void doActionsScrollToElement(By locator, String webElementName) {

		if (TLDriverManager.getDriver() instanceof ChromiumDriver) {

			Actions act = new Actions(TLDriverManager.getDriver());
			act.scrollToElement(getElement(locator, WaitStrategy.PRESENCE)).perform();
			ExtentLogger.pass("Scrolled to WebElement - [" + webElementName + "]");

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> 'doActionsScrollToElement(By locator, String webElementName)' is applicable ONLY for driver instances that extends ChromiumDriver class.\nReview if you are running tests on Firefox or Safari.");
		}

	}

	public static void doActionsScrollDownByAmount(int amount) {

		if (TLDriverManager.getDriver() instanceof ChromiumDriver) {
			Actions act = new Actions(TLDriverManager.getDriver());
			act.scrollByAmount(0, amount).perform();
			ExtentLogger.pass("Scrolled down by " + amount + " pixels.");

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> 'doActionsScrollDownByAmount(int amount)' is applicable ONLY for driver instances that extends ChromiumDriver class.\nReview if you are running tests on Firefox or Safari.");
		}

	}

	public static void doActionsScrollUpByAmount(int amount) {

		if (TLDriverManager.getDriver() instanceof ChromiumDriver) {

			Actions act = new Actions(TLDriverManager.getDriver());
			act.scrollByAmount(0, -(amount)).perform();
			ExtentLogger.pass("Scrolled up by " + amount + " pixels.");

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> 'doActionsScrollUpByAmount(int amount)' is applicable ONLY for driver instances that extends ChromiumDriver class.\nReview if you are running tests on Firefox or Safari.");
		}

	}

	public static void doActionsScrollRightByAmount(int amount) {

		if (TLDriverManager.getDriver() instanceof ChromiumDriver) {

			Actions act = new Actions(TLDriverManager.getDriver());
			act.scrollByAmount(amount, 0).perform();
			ExtentLogger.pass("Scrolled right by " + amount + " pixels.");

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> 'doActionsScrollRightByAmount(int amount)' is applicable ONLY for driver instances that extends ChromiumDriver class.\nReview if you are running tests on Firefox or Safari.");
		}

	}

	public static void doActionsScrollLeftByAmount(int amount) {

		if (TLDriverManager.getDriver() instanceof ChromiumDriver) {

			Actions act = new Actions(TLDriverManager.getDriver());
			act.scrollByAmount(-(amount), 0).perform();
			ExtentLogger.pass("Scrolled left by " + amount + " pixels.");

		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> 'doActionsScrollLeftByAmount(int amount)' is applicable ONLY for driver instances that extends ChromiumDriver class.\nReview if you are running tests on Firefox or Safari.");
		}

	}

	// ** Recommended usage of Robot Class methods is in non-headless mode only.**

	public static void doRobotPressKey(int keyId, String keyName) {

		try {
			Robot robot = new Robot();
			robot.keyPress(keyId);

		} catch (AWTException e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue with Robot class method - 'doRobotPressKey(int keyId, String keyName)'.",
					e);
		}

		ExtentLogger.pass("Pressed " + keyName + " key using Robot Class.");

	}

	public static void doRobotReleaseKey(int keyId, String keyName) {

		try {
			Robot robot = new Robot();
			robot.keyRelease(keyId);

		} catch (AWTException e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue with Robot class method - 'doRobotReleaseKey(int keyId, String keyName)'.",
					e);
		}

		ExtentLogger.pass("Released " + keyName + " key using Robot Class.");

	}

	public static void doRobotPressAndReleaseKey(int keyId, String keyName) {

		try {
			Robot robot = new Robot();
			robot.keyPress(keyId);
			robot.delay(1000);
			robot.keyRelease(keyId);
		} catch (AWTException e) {
			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Issue with Robot class method - 'doRobotPressAndReleaseKey(int keyId, String keyName)'.",
					e);
		}

		ExtentLogger.pass("Pressed and then released " + keyName + " key using Robot Class.");

	}

	// ** Recommended usage of Robot Class methods is in non-headless mode only.**

	public static int getElementsCount(By locator) {

		int webElementsCount = getElements(locator, WaitStrategy.PRESENCE).size();
		ExtentLogger.pass("Getting count of all elements specified by the locator");
		return webElementsCount;

	}

	public static String getElementText(By locator) {

		ExtentLogger.pass("Getting text from the element specified by the locator.");
		String text = getElement(locator, WaitStrategy.PRESENCE).getText();
		ExtentLogger.pass("text : \"" + text + "\" derived from WebElement");
		return text;
	}

	public static String getAttributeValue(By locator, String attributeName) {

		String attributeValue = getElement(locator, WaitStrategy.PRESENCE).getAttribute(attributeName);
		ExtentLogger.pass(
				" [ " + attributeValue + " ] derived from the WebElement for attribute - \'" + attributeName + "\'");
		return attributeValue;
	}

	public static String getCSSValue(By locator, String css) {

		String cssValue = getElement(locator, WaitStrategy.PRESENCE).getCssValue(css);
		ExtentLogger.pass("Getting CSS value : [" + cssValue
				+ "] - from the element specified by the locator which is : [ " + css + " ]");
		return cssValue;
	}

	public static boolean verifyWebElementDisplayed(By locator, String webElementName) {

		WebElement webElement = getElement(locator, WaitStrategy.PRESENCE);
		Rectangle rect = webElement.getRect();
		int x_cord = rect.getX();
		int y_cord = rect.getY();
		boolean coordinatesNonZero = true;
		if (x_cord == 0 && y_cord == 0) {
			coordinatesNonZero = false;
		}
		boolean displayed = getElement(locator, WaitStrategy.PRESENCE).isDisplayed();
		String status = (displayed && coordinatesNonZero) ? "displayed" : "not displayed";
		ExtentLogger.pass(
				"[" + webElementName + "] is " + FrameworkConstants.BOLD_START + status + FrameworkConstants.BOLD_END
						+ " on the webPage with coordinates (x , y) as (" + x_cord + " , " + y_cord + ")");
		return displayed;
	}

	public static boolean verifyWebElementEnabled(By locator, String webElementName) {

		boolean enabled = getElement(locator, WaitStrategy.PRESENCE).isEnabled();
		String status = enabled ? "enabled" : "disabled";
		ExtentLogger.pass("[" + webElementName + "] is " + FrameworkConstants.BOLD_START + status
				+ FrameworkConstants.BOLD_END + " on the webPage");
		return enabled;
	}

	public static boolean verifyWebElementSelected(By locator, String webElementName) {

		boolean selected = getElement(locator, WaitStrategy.PRESENCE).isSelected();
		String status = selected ? "already selected" : "not selected";
		ExtentLogger.pass("[" + webElementName + "] is " + FrameworkConstants.BOLD_START + status
				+ FrameworkConstants.BOLD_END + " on the webPage");
		return selected;
	}

	public static int[] getElementCoordinates(By locator, String webElementName) {

		WebElement webElement = getElement(locator, WaitStrategy.PRESENCE);
		Rectangle rect = webElement.getRect();
		int x_cord = rect.getX();
		int y_cord = rect.getY();
		ExtentLogger.info("(X,Y) coodinate of weblement [" + webElementName + "] is (" + x_cord + " , " + y_cord + ")");
		return new int[] { x_cord, y_cord };

	}

	public static int[] getElementSize(By locator, String webElementName) {

		WebElement webElement = getElement(locator, WaitStrategy.PRESENCE);
		Rectangle rect = webElement.getRect();
		int height = rect.getHeight();
		int width = rect.getWidth();
		ExtentLogger.info(
				"Height of  weblement [" + webElementName + "] is [" + height + "] and width is : [" + width + "]");
		return new int[] { height, width };

	}

	public static List<WebElement> getListOfElementsPresent(By locator, String webElementName) {

		ExtentLogger.pass("Getting list of WebElements specified by the locator : [ " + webElementName + " ]");
		return getElements(locator, WaitStrategy.PRESENCE);

	}

	public static List<String> getElementsTextListPresent(By locator, String webElementName) {

		List<String> eleTextList = new ArrayList<>();
		List<WebElement> eleList = getElements(locator, WaitStrategy.PRESENCE);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		ExtentLogger.pass("Getting texts from all elements specified by the locator : [ " + webElementName + " ]");
		return eleTextList;
	}

	public static List<String> getElementsTextListPresentAndVisible(By locator, String webElementName) {

		List<String> eleTextList = new ArrayList<>();
		List<WebElement> eleList = getElements(locator, WaitStrategy.VISIBLE);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		ExtentLogger.pass("Getting texts from all elements specified by the locator : [ " + webElementName + " ]");
		return eleTextList;
	}

	public static void printAllElementsTextWhenPresent(By locator, String webElementName) {

		List<WebElement> eleList = getElements(locator, WaitStrategy.PRESENCE);
		ExtentLogger.pass("Printing text for all elements specified by the locator : [ " + webElementName + " ]");
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println("===============" + text + "===============");
		}

	}

	public static void printAllElementsTextWhenPresentAndVisible(By locator, String webElementName) {

		List<WebElement> eleList = getElements(locator, WaitStrategy.VISIBLE);
		ExtentLogger.pass("Printing text for all elements specified by the locator : [ " + webElementName + " ]");
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println("===============" + text + "===============");
		}

	}

	// ********************* DropDown Utility Methods ***********************//

	public static void selectDropDownByIndex(By locator, int index) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		ExtentLogger.pass("Selected option using index from the dropdown.");
		select.selectByIndex(index);

	}

	public static void selectDropDownByVisibleText(By locator, String visibleText) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		ExtentLogger.pass("Selected option by visible text from the dropdown.");
		select.selectByVisibleText(visibleText);

	}

	public static void selectDropDownByValue(By locator, String value) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		ExtentLogger.pass("Selected option by value from the dropdown.");
		select.selectByValue(value);

	}

	public static boolean isDropDownSupportMultipleSelection(By locator) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		ExtentLogger.pass("Checking if the dropdown supports multiple selections or not.");
		return select.isMultiple();

	}

	public static List<String> getSelectDropDownValuesList(By locator) {

		List<String> valuesList = new ArrayList<>();
		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			valuesList.add(text);
		}
		ExtentLogger.pass("Getting all options of the dropdown.");
		return valuesList;
	}

	public static boolean verifySelectDropDownOptionsTotal(By locator, int expectedTotal) {

		int actualTotal = new Select(getElement(locator, WaitStrategy.PRESENCE)).getOptions().size();
		if (actualTotal == expectedTotal)
			return true;
		else
			return false;
	}

	public static void selectValueFromSelectDropDown(By locator, String valueToBeSelected) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.contains(valueToBeSelected)) {
				e.click();
				break;
			}
		}
		ExtentLogger.pass(valueToBeSelected + " selected from the 'select' type dropdown.");
	}

	public static void printSelectDropDownValues(By locator) {

		Select select = new Select(getElement(locator, WaitStrategy.PRESENCE));
		ExtentLogger.pass("Printing values/text for all options of the dropdown.");
		List<WebElement> optionsList = select.getOptions();
		System.out.println("<<<< Total options in the Select dropdown is : " + optionsList.size()
				+ " ; Printing options below >>>>");

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println("================" + text + "==============");
		}
	}

	public static void selectValueFromBootstrapDropDown(By locator, String valueToBeSelected) {

		boolean present = false;
		List<WebElement> tempOptions = getElements(locator, WaitStrategy.PRESENCE);
		System.out.println("<<<< Total options in the dropdown is : " + tempOptions.size() + "  >>>>");

		for (int i = 0; i < tempOptions.size(); i++) {
			String text = tempOptions.get(i).getText();
			if (text.contains(valueToBeSelected)) {

				present = true;
				tempOptions.get(i).click();
				break;
			}
		}

		if (!present) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> value to be selected not present in dropdown");

		}

		else {

			ExtentLogger.pass("[ " + valueToBeSelected + " ] selected successfully from 'bootstrap' dropdown.");
		}
	}

	public static void selectValueFromListOfWebElementsPresent(By locator, String valueToBeSelected) {

		boolean present = false;
		List<WebElement> tempOptions = getElements(locator, WaitStrategy.PRESENCE);

		for (int i = 0; i < tempOptions.size(); i++) {
			String text = tempOptions.get(i).getText().trim();
			if (text.equalsIgnoreCase(valueToBeSelected)) {

				present = true;
				tempOptions.get(i).click();
				break;
			}
		}

		if (!present) {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> value to be selected not present in the list of webelements specified by locator");

		}

		else {

			ExtentLogger.pass("[ " + valueToBeSelected
					+ " ] selected successfully from the list of webelements specified by locator");
		}
	}

	public static boolean waitForTitleToBe(String title) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		boolean flag = wait.until(ExpectedConditions.titleIs(title));
		ExtentLogger.pass("Waited for title to be equal to : [ " + title + " ]");
		return flag;
	}

	public static boolean waitForTitleToContain(String fraction) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		boolean flag = wait.until(ExpectedConditions.titleContains(fraction));
		ExtentLogger.pass("Waited for title to contain string fraction : [ " + fraction + " ]");
		return flag;
	}

	public static boolean waitForURLToBe(String url) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		boolean flag = wait.until(ExpectedConditions.urlToBe(url));
		ExtentLogger.pass("Waited for url to be equal to : [ " + url + " ]");
		return flag;
	}

	public static boolean waitForURLToContain(String fraction) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		boolean flag = wait.until(ExpectedConditions.urlContains(fraction));
		ExtentLogger.pass("Waited for url to contain string fraction : [ " + fraction + " ]");
		return flag;
	}

	public static boolean waitForElementAttributeToContain(By locator, String webElementName, String attribute,
			String fraction) {

		WebDriverWait wait = webDriverWaitSupplierForElement();
		boolean flag = wait.until(ExpectedConditions.attributeContains(locator, attribute, fraction));
		ExtentLogger.pass("Waited for [ " + webElementName + " ] to contain string fraction : [ " + fraction
				+ " ] in attribute [ " + attribute + " ]");
		return flag;
	}

	public static boolean waitForElementAttributeToBe(By locator, String webElementName, String attribute,
			String value) {

		WebDriverWait wait = webDriverWaitSupplierForElement();
		boolean flag = wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
		ExtentLogger.pass(
				"Waited for [ " + webElementName + " ]'s attribute [ " + attribute + " ] to be [ " + value + " ]");
		return flag;
	}

	public static boolean waitForElementTextToBe(By locator, String webElementName, String value) {

		WebDriverWait wait = webDriverWaitSupplierForElement();
		boolean flag = wait.until(ExpectedConditions.textToBe(locator, value));
		ExtentLogger.pass("Waited for [ " + webElementName + " ]'s text to be [ " + value + " ]");
		return flag;
	}

	public static boolean waitForElementTextToContain(By locator, String webElementName, String fraction) {

		WebDriverWait wait = webDriverWaitSupplierForElement();
		boolean flag = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, fraction));
		ExtentLogger.pass("Waited for [ " + webElementName + " ]'s text to contain [ " + fraction + " ]");
		return flag;
	}

	public static boolean waitForElementValueAttrToContain(By locator, String webElementName, String fraction) {

		WebDriverWait wait = webDriverWaitSupplierForElement();
		boolean flag = wait.until(ExpectedConditions.textToBePresentInElementValue(locator, fraction));
		ExtentLogger.pass("Waited for [ " + webElementName + " ]'s 'value' attribute to be [ " + fraction + " ]");
		return flag;
	}

	// ************* ALERT utility Methods************************//

	public static Alert switchToAlert() {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		ExtentLogger.info("Switching successfully to alert");
		return alert;

	}

	public static void acceptAlert(Alert alert) {

		alert.accept();
		ExtentLogger.info("Accepetd Alert");
	}

	public static void dismissAlert(Alert alert) {

		alert.dismiss();
		ExtentLogger.info("Dismissed Alert");
	}

	public static void sendKeysToAlert(Alert alert, String keysToSend) {

		alert.sendKeys(keysToSend);
		ExtentLogger.info("Sent [" + keysToSend + "] to Alert");
	}

	public static String getTextFromAlert(Alert alert) {

		String str = alert.getText();
		ExtentLogger.info("Got text : [" + str + "] from Alert");

		return str;
	}

	// ************* IFRAME utility Methods************************//

	public static void switchToFrame(By by) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
		ExtentLogger.info("Switched to Iframe successfully");

	}

	public static void switchToParentFrame() {

		TLDriverManager.getDriver().switchTo().parentFrame();
		ExtentLogger.info("Switched to Parent Frame successfully");

	}

	public static void switchToDefaultContent() {

		TLDriverManager.getDriver().switchTo().defaultContent();
		ExtentLogger.info("Switched to default content successfully");

	}

	// ************* TAB/WINDOW Utility Methods************************//

	public static String getWindowHandle() {

		String windowHandle = TLDriverManager.getDriver().getWindowHandle();
		ExtentLogger.info("Window handle for the current focused window is : [ " + windowHandle + " ]");
		return windowHandle;

	}

	public static void closeAllChildPopUpWindows(String parentWindowHandle) {

		Set<String> windowHandles = TLDriverManager.getDriver().getWindowHandles();
		for (String str : windowHandles) {

			if (!str.equals(parentWindowHandle)) {
				TLDriverManager.getDriver().switchTo().window(str);
				ExtentLogger.info("Closing window with title : [ " + TLDriverManager.getDriver().getTitle() + " ]");
				TLDriverManager.getDriver().close();
				try {
					Thread.sleep(MILLIS_DURATION_500);
				} catch (InterruptedException e) {

					throw new FrameworkException(
							"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Thread.sleep() Interrupted.", e);
				}
			}
		}

		TLDriverManager.getDriver().switchTo().window(parentWindowHandle);

	}

	public static void createNewWindowAndSwitch() {

		TLDriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
		String windowHandleOfNewWindow = TLDriverManager.getDriver().getWindowHandle();
		ExtentLogger.info(
				"Created a New Window with window handle - [" + windowHandleOfNewWindow + "] and switched to it.");

	}

	public static void createNewTabAndSwitch() {

		TLDriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
		String windowHandleOfNewWindow = TLDriverManager.getDriver().getWindowHandle();
		ExtentLogger
				.info("Created a New Tab with window handle - [" + windowHandleOfNewWindow + "] and switched to it.");
	}

	public static void switchToWindowUsingTitle(String parentWindowHandle, String titleFraction) {

		Set<String> windowHandles = TLDriverManager.getDriver().getWindowHandles();
		Iterator<String> winIterator = windowHandles.iterator();
		boolean flag = false;

		while (winIterator.hasNext()) {
			String temp = winIterator.next();

			if (!(temp.equals(parentWindowHandle))) {
				TLDriverManager.getDriver().switchTo().window(temp);

				if (TLDriverManager.getDriver().getTitle().contains(titleFraction)) {
					flag = true;
					break;
				}
			}
		}

		if (flag) {
			ExtentLogger.info("Switched to window containing title fraction: [" + titleFraction + "]");
		}

		else {

			throw new FrameworkException(
					"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Could not find any window/tab containing title fraction : ["
							+ titleFraction + "]");

		}

	}

	public static void switchToWindowUsingHandle(String windowHandle) {

		TLDriverManager.getDriver().switchTo().window(windowHandle);
		ExtentLogger.info("Switched to window with window handle - [" + windowHandle + "]");
	}

	public static int getTotalWindowCount() {

		int totalWindows = TLDriverManager.getDriver().getWindowHandles().size();
		ExtentLogger.info("Total windows opened till this step by 'this' driver session is : [" + totalWindows + "]");
		return totalWindows;
	}

	public static boolean waitForWindowCountToBe(int expectedWindowCount) {

		WebDriverWait wait = webDriverWaitSupplierForNonElement();
		boolean flag = wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindowCount));
		ExtentLogger.pass("Waited for total number of windows opened to be - [" + expectedWindowCount + "]");
		return flag;

	}

}
