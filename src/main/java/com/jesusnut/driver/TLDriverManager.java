package com.jesusnut.driver;

import org.openqa.selenium.WebDriver;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * TLDriverManager class helps to achieve thread safety for the WebDriver
 * instance.
 * 
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TLDriverManager {

	private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

	/**
	 * Returns the thread safe WebDriver instance fetched from ThreadLocal variable.
	 * 
	 * @return {@link org.openqa.selenium.WebDriver} instance.
	 */

	public static WebDriver getDriver() {
		return threadLocal.get();
	}

	/**
	 * Sets the WebDriver instance to ThreadLocal variable.
	 * 
	 * @param driver{@link org.openqa.selenium.WebDriver} instance that needs to
	 *                     saved from Thread safety issues.
	 */
	public static void setDriver(WebDriver driver) {
		threadLocal.set(driver);
	}

	/**
	 * Sets the ThreadLocal variable having WebDriver instance with default value.
	 */

	public static void resetDriver() {
		threadLocal.remove();
	}

}
