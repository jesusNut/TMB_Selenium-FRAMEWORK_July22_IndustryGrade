package com.jesusnut.constants;

import org.openqa.selenium.WebElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SynchronizationConstants class contains constants used in Selenium
 * Synchronization technique i.e. WebDriver Wait.<br>
 * 
 * @author Abhishek Bhardwaj-JesusNut
 * @version 1.0
 * @since 1.0
 * @see com.jesusnut.utils.Ignitor
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SynchronizationConstants {

	private static final long DEFAULT_ELEMENT_WAIT_TIME_OUT = 20;
	private static final long DEFAULT_WAIT_TIME_OUT = 8;
	private static final int DEFAULT_SMARTWAITUTILS_TIMEOUT = 15;

	/**
	 * Returns dafault wait time for a <b>{@link WebElement}.</b>
	 * 
	 * @return dafault wait time for a <b>{@link WebElement}</b> in long.
	 */

	public static long getDefaultElementTimeOut() {

		return DEFAULT_ELEMENT_WAIT_TIME_OUT;

	}

	/**
	 * Returns dafault wait time for a <b>Non-WebElements</b> e.g. Alerts etc.
	 * 
	 * @return default wait time for a <b>Non-WebElement</b> in long.
	 */

	public static long getDefaultTimeOut() {

		return DEFAULT_WAIT_TIME_OUT;

	}

	/**
	 * Returns dafault wait time for <b>a Webpage to load, JQuery load, Angular
	 * load</b>
	 * 
	 * @return default wait time for a <b>a webpage to load</b> in int.
	 */

	public static int getDefaultSmartWaitUtilsTimeOut() {

		return DEFAULT_SMARTWAITUTILS_TIMEOUT;

	}

}
