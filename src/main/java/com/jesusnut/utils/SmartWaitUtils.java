package com.jesusnut.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jesusnut.constants.SynchronizationConstants;
import com.jesusnut.driver.TLDriverManager;
import com.jesusnut.exception.FrameworkException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class having methods which contains smart waits implemented using
 * JavascriptExecutor interface.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SmartWaitUtils {

	private static final int SMARTWAITUTILS_TIMEOUT = SynchronizationConstants.getDefaultSmartWaitUtilsTimeOut();

	public static void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(TLDriverManager.getDriver(), Duration.ofSeconds(SMARTWAITUTILS_TIMEOUT));
		JavascriptExecutor js = (JavascriptExecutor) TLDriverManager.getDriver();

		ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").toString().equals("complete");

		boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

		if (!jsReady) {
			try {
				wait.until(jsLoad);
			} catch (Throwable error) {
				throw new FrameworkException(
						"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Timeout waiting for page load (Javascript). ("
								+ SMARTWAITUTILS_TIMEOUT + "seconds)",
						error);
			}
		}
	}

	public static void waitForJQueryLoad() {
		WebDriverWait wait = new WebDriverWait(TLDriverManager.getDriver(), Duration.ofSeconds(SMARTWAITUTILS_TIMEOUT),
				Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) TLDriverManager.getDriver();

		ExpectedCondition<Boolean> jQueryLoad = driver -> {
			assert driver != null;
			return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
		};

		boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

		if (!jqueryReady) {
			try {
				wait.until(jQueryLoad);
			} catch (Throwable error) {
				throw new FrameworkException(
						"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Timeout waiting for JQuery load (Javascript). ("
								+ SMARTWAITUTILS_TIMEOUT + "seconds)",
						error);
			}
		}
	}

	public static void waitForAngularLoad() {
		WebDriverWait wait = new WebDriverWait(TLDriverManager.getDriver(), Duration.ofSeconds(SMARTWAITUTILS_TIMEOUT),
				Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) TLDriverManager.getDriver();
		final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

		ExpectedCondition<Boolean> angularLoad = driver -> {
			assert driver != null;
			return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
		};

		boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

		if (!angularReady) {
			try {
				wait.until(angularLoad);
			} catch (Throwable error) {
				throw new FrameworkException(
						"JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Timeout waiting for Angular load. ("
								+ SMARTWAITUTILS_TIMEOUT + "seconds)",
						error);
			}
		}

	}

	public static void sleep(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {

			throw new FrameworkException("JESUSNUT SELENIUM FRAMEWORK [Powered by TMB ©] >>> Sleep Interrupted. ", e);
		}

	}

}
